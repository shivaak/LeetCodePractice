package LLD;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node {
        int value;
        Node next;
        Node prev;
        Node(int value){
            this.value = value;
            this.next=null;
            this.prev=null;
        }
    }

    private int capacity;
    Map<Integer, Node> map;
    Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1);
        tail = new Node(-1);
        head.next=tail;
        tail.prev=head;

    }

    public int get(int key) {
        Node current = map.get(key);
        if(current==null) return -1;

        //get the key node and move the node to the head of linked list
        Node prev = current.prev;
        Node next = current.next;
        prev.next=next;
        next.prev=prev;

        //attach current node to the head
        Node temp = head.next;
        head.next=current;
        current.prev=head;
        temp.prev=current;
        current.next=temp;

        return current.value;
    }

    public void put(int key, int value) {

        Node current = map.get(key);
        if(current==null){
            //create the key and add it to the head of linked list
            if(map.size()==capacity){
                removeLRU();
            }
            current = new Node(value);
            map.put(key, current);
        }else{
            //get the key node and move the node to the head of linked list
            Node prev = current.prev;
            Node next = current.next;
            prev.next=next;
            next.prev=prev;
        }
        //attach current node to the head
        Node temp = head.next;
        head.next=current;
        current.prev=head;
        temp.prev=current;
        current.next=temp;

    }

    private void removeLRU(){
        Node lastNode = tail.prev;
        map.remove(lastNode.value);
        Node prev = lastNode.prev;
        prev.next=tail;
        tail.prev=prev;
    }

    public static void main(String[] args) {
        LRUCache c = new LRUCache(2);
        c.put(1,1);
        c.put(2,2);
        System.out.println(c.get(1));
        c.put(3,3);
        System.out.println(c.get(2));
    }
}
