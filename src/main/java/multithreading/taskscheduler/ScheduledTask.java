package multithreading.taskscheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

@Getter
@AllArgsConstructor
public class ScheduledTask {
    private Runnable runnable;
    private final long delay;
    private final TimeUnit unit;
    @Setter
    private long scheduledTime;
}
