
import java.time.LocalDateTime;
import java.util.concurrent.Delayed;

class StopTask extends SingleTask<Void> {

    StopTask() {
        super(LocalDateTime.now(), () -> null, 0);
    }

    @Override
    public int compareTo(Delayed o) {
        return -1;
    }
}
