package multithreading;

public class DeadLock {

    public static void main(String[] args) {

        DeadLock d =new DeadLock();
        d.demo();
    }

    private void demo() {
        Object o1  = new Object();
        Object o2  = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + " - I have lock on o1 and waiting for o2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o2){
                        System.out.println(Thread.currentThread().getName() + " - I got o2");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " - I have lock on o2 and waiting for o1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o1){
                        System.out.println(Thread.currentThread().getName() + " - I got o1");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
