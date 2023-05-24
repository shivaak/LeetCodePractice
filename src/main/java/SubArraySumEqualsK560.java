public class SubArraySumEqualsK560 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -2, 2};
        int k= 4;

        int sum=0, count=0;
        for(int i=0; i<=nums.length-1;i++){
            sum = nums[i];
            if(sum==k) count++;

            for(int j=i+1;j<=nums.length-1;j++){
                sum = sum + nums[j];
                if(sum==k) count++;
            }
        }
        System.out.println(count);

    }
}
