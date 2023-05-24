package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class PrintOneSubSequenceWhoseSumIsK {

    /* Watch TakeUForward Recursion playlist */

    public static void main(String[] args) {
        PrintOneSubSequenceWhoseSumIsK p = new PrintOneSubSequenceWhoseSumIsK();
        p.print(new int[]{1,2,1}, 3);
    }

    public void print(int[] nums, int k) {

        print(nums, k, 0, new ArrayList<>(), 0);
    }

    private boolean print(int[] nums, int k, int sum, List<Integer> output, int index) {

        if(sum==k){
            //print
            if(output.size()==0){
                System.out.print("[]");
            }
            for(int o : output){
                System.out.print(o + " ");
            }
            System.out.println();
            return true;
        }

        if(sum>k || index>=nums.length) return false;

        output.add(nums[index]);
        sum = sum + nums[index];
        if(print(nums, k, sum, output, index+1)){
            return true;
        }
        output.remove(output.size()-1);
        sum = sum - nums[index];
        if(print(nums, k, sum, output, index+1)){
            return true;
        }
        return false;
    }
}
