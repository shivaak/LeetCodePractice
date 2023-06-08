package multithreading.taskscheduler;

import java.time.Duration;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class InMemoryTaskScheduler {

    private ReentrantLock lock;
    private final Condition condition;

    private final PriorityQueue<ScheduledTask> taskQueue;

    private final ExecutorService executorService;

    public InMemoryTaskScheduler(int numberOfWorkers){
        lock = new ReentrantLock();
        this.condition= lock.newCondition();
        this.taskQueue = new PriorityQueue<>(Comparator.comparingLong(ScheduledTask::getScheduledTime));
        this.executorService = Executors.newFixedThreadPool(numberOfWorkers);
    }

    public void schedule(Runnable runnable, int delay, TimeUnit unit) {
        lock.lock();
        try{
            long scheduledTime = System.currentTimeMillis() + unit.toMillis(delay);
            ScheduledTask task = new ScheduledTask(runnable, delay, unit, scheduledTime);
            taskQueue.add(task);
            condition.signalAll();
        }catch (Exception e){
            System.out.println("some thing wrong in scheduling task type 1");
        }finally {
            lock.unlock();
        }
    }

    public void start(){
        long timeToSleep = 0;
        while (true){
            lock.lock();
            try{
                while(taskQueue.isEmpty()){
                    condition.await();
                }
                while (!taskQueue.isEmpty()){
                    timeToSleep = taskQueue.peek().getScheduledTime() - System.currentTimeMillis();
                    if(timeToSleep <= 0){
                        break;
                    }
                    condition.await(timeToSleep, TimeUnit.MILLISECONDS);
                }
                ScheduledTask task = taskQueue.poll();
                executorService.submit(task.getRunnable());
            }catch (Exception e){
                System.out.println("some thing wrong in start");
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }


    public static void main(String[] args) {
        InMemoryTaskScheduler scheduler = new InMemoryTaskScheduler(10);
        scheduler.schedule(getRunnable("Task 1"), 3, TimeUnit.SECONDS);
        scheduler.start();
    }



    private static Runnable getRunnable(String str){
        return () -> {
            System.out.println(String.format("%s started at %d seconds", str, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(String.format("%s ended at %d seconds", str, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())));
        };
    }
}
