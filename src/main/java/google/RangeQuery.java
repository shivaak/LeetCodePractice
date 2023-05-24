package google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Provide a class that acts, effectively, as the union of ranges.
That is to say that ranges can be inserted into an instance of this class and then the instance can be queried to check if a particular point is in any of the ranges that have been inserted.
*/
public class RangeQuery {

    private List<Range> ranges;

    class Range {
        private int start;
        private int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean contains(int point) {
            return point >= start && point <= end;
        }
    }

    public RangeQuery(){
        this.ranges = new ArrayList<>();
    }

    public void insertRange(int start, int end) {
        Range newRange = new Range(start, end);
        ranges.add(newRange);
    }

    public boolean isInRange(int point) {
        List<Range> sortedRanges = new ArrayList<>(ranges);
        sortedRanges.sort(Comparator.comparingInt(r -> r.start));

        int index = Collections.binarySearch(sortedRanges, new Range(point, point), Comparator.comparingInt(r -> r.start));
        if (index >= 0) {
            // Point is equal to the start of a range
            return true;
        } else {
            // Point is not equal to the start of a range, calculate insertion point
            int insertionPoint = -(index + 1);
            if (insertionPoint == 0) {
                // Point is smaller than all ranges
                return false;
            }
            Range prevRange = sortedRanges.get(insertionPoint - 1);
            return prevRange.contains(point);
        }
    }

    public static void main(String[] args) {

        RangeQuery rangeUnion = new RangeQuery();

        rangeUnion.insertRange(5, 10);
        rangeUnion.insertRange(15, 20);
        rangeUnion.insertRange(18, 30);

        System.out.println(rangeUnion.isInRange(6));   // true
        System.out.println(rangeUnion.isInRange(1));   // false
        System.out.println(rangeUnion.isInRange(10));  // true
        System.out.println(rangeUnion.isInRange(21));  // true
        System.out.println(rangeUnion.isInRange(35));  // false



    }
}
