package Substring;

public class SubarrayWithKDifferentInteger {

    public static void main(String[] args) {
        SubarrayWithKDifferentInteger s = new SubarrayWithKDifferentInteger();
        System.out.println(s.subarraysWithKDistinct(new int[]{1,2,1,2,3}, 2));
    }

    public int subarraysWithKDistinct(int[] nums, int k) {
        return solve(nums, k) - solve(nums, k-1);

    }

    public int solve(int[] nums, int k) {

        int[] arr = new int[20002];
        int total=0;

        int left=0, right=0, counter=0;
        while(right<nums.length){
            int n = nums[right];
            if(arr[n]==0){
                counter++;
            }
            arr[n]++;
            while(counter>k){
                int rn = nums[left];
                arr[rn]--;
                if(arr[rn]==0){
                    counter--;
                }
                left++;
            }
            total += right - left + 1;
            right++;
        }

        return total;

    }
}
