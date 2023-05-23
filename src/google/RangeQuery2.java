package google;

import java.util.*;

/*
Provide a class that acts, effectively, as the union of ranges.
That is to say that ranges can be inserted into an instance of this class and then the instance can be queried to check if a particular point is in any of the ranges that have been inserted.
*/
public class RangeQuery2 {

    private TreeMap<Integer, Integer> map;

    public RangeQuery2(){
        this.map = new TreeMap<>();
    }

    /*
    1-20
    25-30
    28-38

     */


    public void insertRange(int start, int end) {
        Map.Entry<Integer, Integer> old = map.floorEntry(start);
        if(old!=null && start>=old.getKey() && start<=old.getValue()) {
            if(end>old.getValue()){
                int startvalue = old.getKey();
                map.remove(old.getKey());
                map.put(startvalue, end);
            }
            return;
        }

        Map.Entry<Integer, Integer> next = map.ceilingEntry(start);
        if(next!=null && end>next.getKey()) {
            if(end<=next.getValue()){
                int endvalue= next.getValue();
                map.remove(next.getKey());
                map.put(start, endvalue);
            }else{
                map.remove(next.getKey());
                map.put(start, end);
            }
            return;
        }

        map.put(start, end);

    }

    public boolean isInRange(int point) {
        Map.Entry<Integer, Integer> old = map.floorEntry(point);
        if(old!=null && point>=old.getKey() && point<=old.getValue()) {
            return true;
        }

        Map.Entry<Integer, Integer> next = map.ceilingEntry(point);
        if(next!=null && point>next.getKey()) {
            return true;
        }

        return false;
    }

    public int size(){
        return  map.size();
    }

    public static void main(String[] args) {

        RangeQuery2 rangeUnion = new RangeQuery2();

        rangeUnion.insertRange(1, 100);
        rangeUnion.insertRange(5, 10);
        rangeUnion.insertRange(15, 20);
        rangeUnion.insertRange(18, 30);
        rangeUnion.insertRange(120, 375);
        rangeUnion.insertRange(150, 175);

        System.out.println(rangeUnion.isInRange(6));   // true
        System.out.println(rangeUnion.isInRange(1));   // false
        System.out.println(rangeUnion.isInRange(10));  // true
        System.out.println(rangeUnion.isInRange(21));  // true
        System.out.println(rangeUnion.isInRange(35));  // false
        System.out.println(rangeUnion.isInRange(120));  // false
        System.out.println(rangeUnion.isInRange(400));  // false

        System.out.println(rangeUnion.size());



    }
}
