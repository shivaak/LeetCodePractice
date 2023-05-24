package dynamicprogramming;

import java.util.Arrays;

public class HouseRobber2 {
    public static void main(String[] args) {

        HouseRobber2 hr = new HouseRobber2();
        System.out.println(hr.rob(new int[]{2,1,1,2}));

    }

    public int rob(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];

        int leftArr = solve(Arrays.copyOfRange(nums, 0, nums.length-1));
        int rightArr = solve(Arrays.copyOfRange(nums, 1, nums.length));
        return Math.max(leftArr, rightArr);
    }

    private int solve(int[] nums){
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];

        int[] dp = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            if(i==0) {dp[i]=nums[i]; continue;}
            if(i==1) {dp[i]=Math.max(nums[i], nums[i-1]); continue;}

            dp[i]=Math.max(dp[i-1], nums[i]+dp[i-2]);
        }
        return dp[nums.length-1];
    }
}
