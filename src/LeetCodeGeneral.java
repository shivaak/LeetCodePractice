import java.util.*;

public class LeetCodeGeneral {

    public static void main(String[] args) {
        LeetCodeGeneral l = new LeetCodeGeneral();
        /*System.out.println(l.findDuplicate(new int[]{1,3,4,2,2}));
        System.out.println(l.minOperations(new int[]{3,1,6,8}, new int[] {1,5}));
        System.out.println(l.minOperations(new int[]{2,9,6,3}, new int[] {5}));
        System.out.println(l.minOperations(new int[]{47,50,97,58,87,72,41,63,41,51,17,21,7,100,69,66,79,92,84,9,57,26,26,28,83,38}, new int[] {3}));
*/
        //System.out.println(l.removeDuplicates(new int[]{1,1}));
        int[][] result = l.outerTrees(new int[][]{{1,1},{2,2},{2,0},{2,4},{3,3},{4,2}});
        for(int i=0;i<result.length;i++){
            System.out.println(result[i][0] + "," + result[i][1]);
        }

    }

    private int removeDuplicates(int[] nums) {
        if(nums.length<=1) return nums.length;

        for(int i=0;i<nums.length;i++) {
            int j=i+1;

            while(j<nums.length && nums[j]<=nums[i]){
                j++;
            }

            if(j<nums.length){
                int t=nums[i+1];
                nums[i+1]=nums[j];
                nums[j]=t;
            }
        }


        int c=1;
        while(c<nums.length){
            if(nums[c]<=nums[c-1])break;
            c++;
        }

        System.out.println(c);

        return c;
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


    public int[][] outerTrees(int[][] trees) {

        Map<Integer, PriorityQueue<Integer[]>> map = new HashMap<>();

        int n = trees.length;

        for(int i=0;i<n;i++){
            Integer[] c = Arrays.stream(trees[i]).boxed().toArray(Integer[]::new);
            map.computeIfAbsent(c[0], k -> new PriorityQueue<>((a,b) -> {
                return a[1]-b[1];
            })).add(c);
        }

        List<Integer[]> list = new ArrayList<>();
        for(int key : map.keySet()){
            Integer[][] t = map.get(key).toArray(new Integer[0][]);
            list.add(t[0]);
            if(t.length>1) list.add(t[t.length-1]);
        }

        int[][] result = new int[list.size()][];

        for(int i=0;i<list.size();i++){
            result[i] = new int[2];
            result[i][0]=list.get(i)[0];
            result[i][1]=list.get(i)[1];
        }

        return result;

    }
}
