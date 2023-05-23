package multithreading;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

class ProducerConsumer2{

    ArrayList<Integer> box = new ArrayList<>();
    public void produce(int i) throws InterruptedException {
        synchronized (box){
            while(box.size()>0){
                box.wait();
            }
            box.add(i);
            System.out.println("Messsage Produced " + i);
            box.notifyAll();
        }
    }

    public void consume() throws InterruptedException {
        synchronized (box){
            while(box.size()<=0){
                box.wait();
            }

            System.out.println("Messsage Consumed " + box.get(0));
            box.remove(box.size()-1);
            box.notifyAll();
        }
    }
    
    
    public static void main(String[] str){

        ProducerConsumer2 pc = new ProducerConsumer2();
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Random random = new Random();
                    int randomNumber = random.nextInt(100) + 1;
                    try {
                        pc.produce(randomNumber);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        pc.consume();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        producer.start();
        consumer.start();
        

                
    }
    

}

class Producer1 implements Runnable {
    
    private ArrayBlockingQueue<Integer> box;
    
    Producer1(ArrayBlockingQueue<Integer> box){
        this.box = box;
    }
    
    @Override
    public void run() {
        while(true){
            synchronized(box){
                while(box.size()>0){
                    try{
                        box.wait();
                    }catch(InterruptedException ie){
                        throw new RuntimeException("Producer Exception ", ie);
                    }
                }
                box.add(10);
                System.out.println("Message produced");
                box.notify();
            }
        }
        
    }
}

class Consumer1 implements Runnable {
    
    private ArrayBlockingQueue<Integer> box;
    
    Consumer1(ArrayBlockingQueue<Integer> box){
        this.box = box;
    }
    
    @Override
    public void run() {
        while(true){
            synchronized(box){
                while(box.isEmpty()){
                    try{
                        box.wait();
                    }catch(InterruptedException ie){
                        throw new RuntimeException("Consumer Exception ", ie);
                    }
                }
                System.out.println("Message read " + box.poll());
                box.notify();
            }
        }
        
    }
}