package interval;

/*
    When Provided a list of intervals, return the list of intervals that occurs maximum number of times inside other intervals. The interval should be completely contained inside the other, (x,y)
    Ex: Input -> [1,12],[2,6],[3,5], [4,4],[5,6]
    Output -> [4,4] occurs in 3 times inside [1,12],[2,6],[3,5]

    Ex: Input -> [1,12],[2,6],[3,5], [4,4],[5,6], [5,6]
    Output -> [4,4] or [5,6] Both of them occurs three times inside others. There is a pair of [5,6], so each appear within the other
 */

import java.util.ArrayList;
import java.util.List;

public class FindMaxOccurence {

    public static void main(String[] args) {
        List<int[]> result = findMaxOccurrences(new int[][]{{1,12},{2,6},{3,5}, {4,4},{5,6}});
        System.out.println("Output:");
        for (int[] interval : result) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }

    public static List<int[]> findMaxOccurrences(int[][] intervals) {
        int maxIndex = (int) 1e7;
        int[] arr = new int[maxIndex + 2];

        for (int[] interval : intervals) {
            arr[interval[0]]++;
            arr[interval[1] + 1]--;
        }

        int maxCount = 0;
        for (int i = 1; i <= maxIndex; i++) {
            arr[i] += arr[i - 1];
            maxCount = Math.max(maxCount, arr[i]);
        }

        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            if (arr[interval[0]] == maxCount) {
                result.add(interval);
            }
        }

        return result;
    }
}