import java.util.*;

public class MinimumTimeToCompleteAllTasks2589 {
    public static void main(String[] args) {
        MinimumTimeToCompleteAllTasks2589 m = new MinimumTimeToCompleteAllTasks2589();
        System.out.println(m.findMinimumTime(new int[][]{{2,13,2},{6,18,5},{2,13,3}}));
    }

    public int findMinimumTime(int[][] tasks) {

        // sort based on end time
        Arrays.sort(tasks, (t1,t2) -> {
            if(t1[1]==t2[1]){
                return t1[0] - t2[0]; // if end time is equal, then sort based on start time
            } else{
                return t1[1] - t2[1];
            }
        });

        Arrays.sort(tasks,(t1,t2)->(t1[1]==t2[1])?t1[0]-t2[0]:t1[1]-t2[1]);

        int machineOnTime = 0;
        Set<Integer> alreadyExecutedTime = new HashSet<>();
        for(int i=0;i<tasks.length;i++){
            int start = tasks[i][0];
            int end = tasks[i][1];
            int duration = tasks[i][2];

            int timeAlreadyExecuted = 0;
            for(int j=start;j<=end;j++){
                if(alreadyExecutedTime.contains(j)) {
                    timeAlreadyExecuted++;
                }
            }
            int remaining_duration = duration - timeAlreadyExecuted;
            for(int j=end;remaining_duration>0;j--){
                if(alreadyExecutedTime.add(j)){
                    remaining_duration--;
                    machineOnTime++;
                }
            }
        }
        return machineOnTime;
    }
}
