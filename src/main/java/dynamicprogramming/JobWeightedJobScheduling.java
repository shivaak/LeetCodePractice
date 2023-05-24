package dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class JobWeightedJobScheduling {
    /* https://www.youtube.com/watch?v=ZOP43iB_E_8*/

    public static void main(String[] args) {
        JobWeightedJobScheduling j = new JobWeightedJobScheduling();
        System.out.println(j.jobScheduling(new int[]{4,2,4,2,8}, new int[]{5,5,5,8,10}, new int[]{1,2,8,4,10}));
    }

    class Job{
        int startTime;
        int endTime;
        int profit;
        Job(int startTime, int endTime, int profit){
            this.startTime=startTime;
            this.endTime=endTime;
            this.profit=profit;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {

        List<Job> jobs = new ArrayList<>();
        for(int i=0;i<startTime.length;i++){
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        Collections.sort(jobs, (a, b)->{
            return a.endTime-b.endTime;
        });

        TreeMap<Integer,Integer> map = new TreeMap<>();
        map.put(0,0);
        int maxProfit = Integer.MIN_VALUE;
        for(Job job : jobs){
            int profitTillNow = map.get(map.floorKey(job.startTime));
            int newProfit=job.profit+profitTillNow;
            maxProfit=Math.max(maxProfit, newProfit);
            map.put(job.endTime, maxProfit);
        }
        return maxProfit;

    }
}
