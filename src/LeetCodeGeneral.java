import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCodeGeneral {

    public static void main(String[] args) {
        LeetCodeGeneral l = new LeetCodeGeneral();
        System.out.println(l.findDuplicate(new int[]{1,3,4,2,2}));
        System.out.println(l.minOperations(new int[]{3,1,6,8}, new int[] {1,5}));
        System.out.println(l.minOperations(new int[]{2,9,6,3}, new int[] {5}));
        System.out.println(l.minOperations(new int[]{47,50,97,58,87,72,41,63,41,51,17,21,7,100,69,66,79,92,84,9,57,26,26,28,83,38}, new int[] {3}));


    }

    public int findDuplicate(int[] nums) {

        int len = nums.length;
        for (int num : nums) {
            int idx = Math.abs(num);
            if (nums[idx] < 0) {
                return idx;
            }
            nums[idx] = -nums[idx];
        }

        return len;

    }

    public List<Long> minOperations(int[] nums, int[] queries) {

        /* Brut force - Timed out
        List<Long> result = new ArrayList<>();

        for(int q=0;q<queries.length;q++){
            long sum=0;
            for(int n=0;n<nums.length;n++){
               sum = sum + Math.abs(nums[n]-queries[q]);
            }
            result.add(sum);
        }
        return result;

        */

        // Sort + Prefix sum + Binary Search
        //https://www.youtube.com/watch?v=5vB9ibp3K6Y&ab_channel=AryanMittal

        int n = nums.length;
        Arrays.sort(nums);

        //calculate prefix sum
        long[] prefixSum = new long[nums.length];
        prefixSum[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }

        List<Long> result = new ArrayList<>();
        for(int i=0;i<queries.length;i++){
            int query = queries[i];
            int low=0, high=n-1;
            while(low<high){
                int mid = low+(high-low)/2;
                if (query > nums[mid]) {
                    low = mid + 1;
                }else {
                    high = mid;
                }
            }
            result.add(1L * query * low - prefixSum[low] + prefixSum[n] - prefixSum[low] - 1L * (n - low) * query);
        }

        return result;

    }
}
