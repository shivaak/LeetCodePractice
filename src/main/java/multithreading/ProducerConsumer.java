package multithreading;


import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

interface MessageQueue {
    public void addMessage(String message) throws InterruptedException;
    public String readMessage() throws InterruptedException;

}

class BlockingMessageQueue implements MessageQueue {

    private ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(5);
    private int capacity;

    private ReentrantLock l = new ReentrantLock();

    // using Condition inplace of wait,notify, notifyAll
    private final Condition condition = l.newCondition();

    public BlockingMessageQueue(int capacity){
        this.capacity = capacity;
    }


    @Override
    public void addMessage(String message) throws InterruptedException {
        l.lock();
        if(q.size()==capacity){
            System.out.println("\nQueue is full. Waiting for the consumer to read some");
            condition.await();
        }
        q.add(message);
        condition.signal();
        l.unlock();
    }

    @Override
    public String readMessage() throws InterruptedException {
        l.lock();
        if(q.isEmpty()){
            System.out.println("\nQueue is Empty. Waiting for the consumer to produce some message");
            condition.await();
        }
        String message = q.poll();
        condition.signal();
        l.unlock();
        return message;
    }
}

class Producer implements Runnable {

    private MessageQueue queue;

    public Producer(MessageQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        String[] messages = {
                "apple", "banana", "cherry", "orange", "pear", "grape", "kiwi", "mango", "pineapple", "watermelon",
                "blueberry", "raspberry", "strawberry", "blackberry", "peach", "plum", "apricot", "pomegranate", "lemon", "lime"
        };

        for(int i=0;i<messages.length;i++) {
            System.out.print("Sending message : " + messages[i]);
            try {
                queue.addMessage(messages[i]);
                System.out.println(" - Delivered");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            queue.addMessage("EXIT");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Producer Exited");

    }
}

class Consumer implements Runnable {

    private MessageQueue queue;

    public Consumer(MessageQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        String message = "";
        while(!message.equals("EXIT")){
            try {
                Thread.sleep(2000);
                message = queue.readMessage();
                System.out.println("Last read message from queue : " + message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Read Exited");
    }
}
public class ProducerConsumer {

    public static void main(String[] args) {

        MessageQueue queue = new BlockingMessageQueue(5);

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();
        /*Map<Integer, String> score = new LinkedHashMap<Integer, String>()
        {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest)
            {
                System.out.println(eldest);
                return this.size() > 2;
            }
        }; */
    }

}
