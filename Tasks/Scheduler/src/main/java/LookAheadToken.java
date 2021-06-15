
import java.util.concurrent.*;

class LookAheadToken<T> implements Future<T> {
    private final SingleTask<T> singleTask;

    LookAheadToken(SingleTask<T> singleTask) {
        this.singleTask = singleTask;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return singleTask.cancel();
    }

    @Override
    public boolean isCancelled() {
        return singleTask.getState() == State.CANCELLED;
    }

    @Override
    public boolean isDone() {
        return isCancelled() || singleTask.getState() == State.SUCCESS || singleTask.getState() == State.ERROR;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        State state = singleTask.waitFinalState();
        switch (state) {
            case CANCELLED:
            case ERROR:
            case SUCCESS:
                return getResultAccordingFinalState(state);
            default:
                throw new IllegalStateException("Unexpected behaviour: state " + state + " must not occur here", null);
        }
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        State state = singleTask.waitFinalState(timeout, unit);
        switch (state) {
            case CANCELLED:
            case ERROR:
            case SUCCESS:
                return getResultAccordingFinalState(state);
            default:
                throw new TimeoutException("Wait timeout");
        }
    }

    private T getResultAccordingFinalState(State finalState) throws ExecutionException {
        switch (finalState) {
            case CANCELLED:
                throw new CancellationException("Task was cancelled");
            case ERROR:
                throw new ExecutionException("Error while executing task", singleTask.getError());
            case SUCCESS:
                return singleTask.getResult();
            default:
                throw new IllegalStateException("Unexpected state " + finalState + ". Expected one of final states");
        }
    }
}
