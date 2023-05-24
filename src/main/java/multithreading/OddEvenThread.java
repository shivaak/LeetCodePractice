package multithreading;

public class OddEvenThread {
    int n=10;

    class Printer {
        public synchronized void print(int i){
            System.out.println(i);
            notify();
            if(i>=n)return;
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void process() {
        Printer p = new Printer();
        Thread oddThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=n;i+=2){
                    p.print(i);

                }
            }
        });

        Thread evenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=2;i<=n;i+=2){
                    p.print(i);
                }
            }
        });

        oddThread.start();
        evenThread.start();

    }

    public static void main(String[] args) {
        OddEvenThread oe = new OddEvenThread();
        oe.process();
    }

}


