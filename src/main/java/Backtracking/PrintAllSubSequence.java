package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class PrintAllSubSequence {

    /* Watch TakeUForward Recursion playlist */

    public static void main(String[] args) {
        PrintAllSubSequence p = new PrintAllSubSequence();
        p.print(new int[]{3,1,2});
    }

    public void print(int[] nums) {
        print(0, nums, new ArrayList<Integer>());
    }

    private void print(int i, int[] nums, List<Integer>output) {
        if(i>=nums.length){
            //print
            if(output.size()==0){
                System.out.print("[]");
            }
            for(int o : output){
                System.out.print("[" +o + "]");
            }
            System.out.println();
            return;
        }

        output.add(nums[i]); // Choose
        print(i+1, nums, output); //Explore
        output.remove(output.size()-1); //UnChoose
        print(i+1, nums, output);//Explore

    }
}
