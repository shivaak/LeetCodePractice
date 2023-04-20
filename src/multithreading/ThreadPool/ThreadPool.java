package multithreading.ThreadPool;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private int noOfThreads;
    private LinkedBlockingQueue<Runnable> taskQueue;

    private Worker[] workers;
    private volatile boolean isShutDown;

    public ThreadPool(int noOfThreads){
        this.noOfThreads = noOfThreads;
        taskQueue = new LinkedBlockingQueue<>();
        workers = new Worker[noOfThreads];
        isShutDown=false;

        for(int i=0;i<noOfThreads;i++){
            workers[i] = new Worker();
            workers[i].start();
        }
    }

    public void submitJob(Runnable task){
        synchronized (taskQueue){
            if (isShutDown) {
                throw new IllegalStateException("Thread pool is already shutdown");
            }
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public void shutdown(){
        synchronized (taskQueue) {
            isShutDown = true;
            taskQueue.notifyAll(); // wake up all worker threads waiting on the task queue
        }

        for (Worker worker : workers) {
            try {
                worker.join(); // wait for each worker thread to terminate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    class Worker extends Thread {
        @Override
        public void run(){
            Runnable task;
            while(true){
                synchronized (taskQueue){
                    while(taskQueue.isEmpty() && !isShutDown){
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    task = taskQueue.poll();
                    if (isShutDown) {
                        return;
                    }
                }
                if(task!=null)task.run();
                task=null;
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool(10);
        final int sleepTime = 100;
        pool.submitJob(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=100;i++){
                    System.out.println(Thread.currentThread().getName() + " - " + i);
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        pool.submitJob(new Runnable() {
            @Override
            public void run() {
                for(int i=101;i<=200;i++){
                    System.out.println(Thread.currentThread().getName() + " - " + i);
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        pool.submitJob(new Runnable() {
            @Override
            public void run() {
                for(int i=201;i<=300;i++){
                    System.out.println(Thread.currentThread().getName() + " - " + i);
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread.sleep(1000);
        pool.shutdown();
    }


}
