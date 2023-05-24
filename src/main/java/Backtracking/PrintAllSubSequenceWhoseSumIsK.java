package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class PrintAllSubSequenceWhoseSumIsK {

    /* Watch TakeUForward Recursion playlist */

    public static void main(String[] args) {
        PrintAllSubSequenceWhoseSumIsK p = new PrintAllSubSequenceWhoseSumIsK();
        p.print(new int[]{1,2,1,5,7,6}, 12);
    }

    public void print(int[] nums, int k) {

        print(nums, k, 0, new ArrayList<>(), 0);
    }

    private void print(int[] nums, int k, int sum, List<Integer> output, int index) {
        if(sum==k){
            //print
            if(output.size()==0){
                System.out.print("[]");
            }
            for(int o : output){
                System.out.print(o + " ");
            }
            System.out.println();
            return;
        }

        if(sum>k || index>=nums.length) return;

        output.add(nums[index]);
        sum = sum + nums[index];
        print(nums, k, sum, output, index+1);
        output.remove(output.size()-1);
        sum = sum - nums[index];
        print(nums, k, sum, output, index+1);
    }
}
