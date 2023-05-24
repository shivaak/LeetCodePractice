package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class CountSubSequenceWhoseSumIsK {

    /* Watch TakeUForward Recursion playlist */

    public static void main(String[] args) {
        CountSubSequenceWhoseSumIsK p = new CountSubSequenceWhoseSumIsK();
        System.out.println(p.count(new int[]{1,2,1,5,7,6}, 12));
        /*
        1,5,6 = 12
        5,7 = 12

         */
    }

    public int count(int[] nums, int k) {

        return count(nums, k, 0,  0);
    }

    private int count(int[] nums, int k, int sum, int index) {
        if(sum==k){
            return 1;
        }

        if(sum>k || index>=nums.length) return 0;

        sum = sum + nums[index];
        int result = 0;
        result = result + count(nums, k, sum, index+1);
        sum = sum - nums[index];
        result = result + count(nums, k, sum, index+1);
        return result;
    }
}
