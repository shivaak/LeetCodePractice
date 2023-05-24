package DesignPatterns.iterator;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

class MyCollection<T> implements Iterable<T> {
    T[] items;
    int count;

    public MyCollection() {
        items = (T[])new Object[10];
        count =0;
    }

    public void addItem(T item) {
        if(items.length==count){
            T[] newItems = (T[])new Object[count * 2];
            System.arraycopy(items,0, newItems, 0, count);
            items = newItems;
        }
        items[count]=item;
        count++;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyCollectionIterator<>(this);
    }
}

class MyCollectionIterator<T> implements Iterator<T> {

    T[] items;
    int count;

    int current;

    public MyCollectionIterator(MyCollection<T> ts) {
        items = ts.items;
        count = ts.items.length - 1;
        current=0;
    }

    @Override
    public boolean hasNext() {
        return current<=count && items[current]!=null;
    }

    @Override
    public T next() {
        T item = items[current];
        current++;
        return item;
    }
}

public class Main {

    public static void main(String[] args) {

        MyCollection<Integer> collection = new MyCollection<>();
        collection.addItem(1);
        collection.addItem(10);
        collection.addItem(1000);
        collection.addItem(2000);
        collection.addItem(3000);

        for(Integer i : collection){
            System.out.println(i);
        }

    }
}
