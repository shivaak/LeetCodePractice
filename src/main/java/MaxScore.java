import java.util.PriorityQueue;

public class MaxScore {

    public static void main(String[] args) {
        MaxScore ms = new MaxScore();
        System.out.println(ms.maxScore(new int[]{2,-1,0,1,-3,3,-3}));
    }

    public int maxScore(int[] nums) {

        int sum=0, countOfPositivesInPrefix=0;
        PriorityQueue<Integer> q = new PriorityQueue<>((n1,n2) -> {
            return n2-n1;
        });

        for(int i=0;i<nums.length;i++){
            if(nums[i]==0 && sum>0) {
                countOfPositivesInPrefix++;
                continue;
            }

            if(nums[i]>0){
                sum = sum + nums[i];
                countOfPositivesInPrefix++;
            }else{
                q.offer(nums[i]);
            }
        }

        if(sum>0){
            while(!q.isEmpty()){
                sum = sum + q.poll();
                if(sum>0) countOfPositivesInPrefix++;
                if(sum<=0) break;
            }
        }
        return countOfPositivesInPrefix;

    }
}
