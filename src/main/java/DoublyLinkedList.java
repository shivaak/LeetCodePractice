import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterator<T> {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }

    class Node {
        T data;
        Node next;
        Node prev;

        Node(T data){
            this.data = data;
        }
    }

    Node head;
    Node tail;
    int size;

    public DoublyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head==null && tail==null;
    }

    public Node getHeadNode() {
        return head;
    }

    public Node getTailNode() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    public void insertAtHead(T data) {
        Node current = new Node(data);
        if(head==null){
            head=current;
            tail=current;
            return;
        }else{
            head.prev=current;
            current.next = head;
            head = current;
        }
        size++;
    }

    public void insertAtTail(T data) {
        if(isEmpty()){
            insertAtHead(data);
            return;
        }
        Node current = new Node(data);
        tail.next=current;
        current.prev = tail;
        tail = current;
        size++;
    }

    public void printList() {
        if(isEmpty()){
            System.out.println("List is Empty");
            return;
        }
        Node temp = head;
        System.out.print("[ null <- ");
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null ]");
    }

    public void printListReverse() {
        if(isEmpty()){
            System.out.println("List is Empty");
            return;
        }
        Node temp = tail;
        System.out.print("[ null <- ");
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.prev;
        }
        System.out.println("null ]");

    }

    public boolean deleteAtHead() {
        if(isEmpty()){
            return false;
        }
        head=head.next;
        if(head==null){
            tail=null;
        }else{
            head.prev=null;
        }
        size--;
        return true;
    }

    public boolean deleteAtTail() {
        if(isEmpty()){
            return false;
        }
        tail=tail.prev;
        if(tail==null){
            head=null;
        }else{
            tail.next=null;
        }
        size--;
        return true;
    }
}
