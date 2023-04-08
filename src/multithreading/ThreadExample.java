package multithreading;

public class ThreadExample extends Thread {

    public ThreadExample(String name){
        super(name);
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadExample t = new ThreadExample("MyThread1");
        t.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=10;i++){
                    System.out.println("Thread : " + Thread.currentThread().getName() + " value : " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        t2.start();
        t.join();
        t2.join();
        System.out.println("Main Completed : " + Thread.currentThread().getName());
    }

    @Override
    public void run(){
        for(int i=1;i<=10;i++){
            System.out.println("Thread : " + this.getName() + " value : " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
