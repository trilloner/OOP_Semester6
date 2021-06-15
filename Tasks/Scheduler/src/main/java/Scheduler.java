
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Scheduler {

    private static final AtomicLong totalTasksScheduled = new AtomicLong();
    private final DelayQueue<SingleTask> queue = new DelayQueue<>();
    private final Object lock = new Object();
    private volatile boolean running;
    private final AtomicInteger runningThreads = new AtomicInteger(0);
    private int threadsCount = 0;

    public void start(int threadsCount) {
        if (threadsCount <= 0) {
            throw new IllegalArgumentException("Threads count must be positive");
        }

        synchronized (lock) {
            if (running) {
                throw new RuntimeException("Scheduler is already running");
            }
            this.threadsCount = threadsCount;
            for (int i = 0; i < threadsCount; ++i) {
                new Thread(this::doInThread, "task-scheduler-worker-thread-" + i).start();
                runningThreads.incrementAndGet();
            }
            running = true;
        }
    }

    public void stop(boolean waitTermination) throws InterruptedException {
        synchronized (lock) {
            if (running) {
                try {
                    for (int i = 0; i < threadsCount; ++i) {
                        queue.add(new StopTask());
                    }
                    if (waitTermination) {
                        while (runningThreads.get() > 0) {
                            Thread.sleep(1);
                        }
                    }
                } finally {
                    running = false;
                }
            }
        }
    }

    public <T> Future<T> schedule(LocalDateTime time, Callable<T> callable) {
        SingleTask<T> singleTask = new SingleTask<>(time, callable, totalTasksScheduled.incrementAndGet());
        queue.add(singleTask);
        return new LookAheadToken<>(singleTask);
    }

    private void doInThread() {
        try {
            while (true) {
                try {
                    SingleTask singleTask = queue.take();
                    if (singleTask instanceof StopTask) {
                        break;
                    }
                    singleTask.execute();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        } finally {
            runningThreads.decrementAndGet();
        }
    }
}
