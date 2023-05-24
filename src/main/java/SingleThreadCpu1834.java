import java.util.*;

public class SingleThreadCpu1834 {

    public static void main(String[] args) {
        SingleThreadCpu1834 s = new SingleThreadCpu1834();
        int[] n = s.getOrder(new int[][]{{7,10},{7,12},{7,5},{7,4},{7,2}});

        for(int i=0;i<=n.length-1;i++){
            System.out.print(n[i] + " ");
        }
        System.out.println();

        n = s.getOrder(new int[][]{{1,2},{2,4},{3,2},{4,1}});
        for(int i=0;i<=n.length-1;i++){
            System.out.print(n[i] + " ");
        }

        System.out.println();
        n = s.getOrder(new int[][]{{5,2},{7,2},{9,4},{6,3},{5,10},{1,1}});
        for(int i=0;i<=n.length-1;i++){
            System.out.print(n[i] + " ");
        }
    }

    class Task {
        int index;
        int execTime;
        int enqueueTime;
        Task(int index,int enqueueTime,int execTime) {
            this.index = index;
            this.enqueueTime = enqueueTime;
            this.execTime = execTime;
        }
    }

    public int[] getOrder(int[][] tasks) {

        int[][] tasksWithIndex = new int[tasks.length][3];

        for(int i=0; i<tasks.length; i++){
            //[index, enqueueTime, processingTime]
            tasksWithIndex[i] = new int[]{i, tasks[i][0],tasks[i][1]};
        }

        Arrays.sort(tasksWithIndex,(t1,t2)-> {
            if(t1[1]==t2[1]){
                return t1[2]-t2[2];
            }else{
                return t1[1]-t2[1];
            }
        });
        //[index, processingTime]
        Queue<Task> q = new PriorityQueue<>((t1,t2)-> {
            if(t1.execTime == t2.execTime){
                return t1.index - t2.index;
            }else{
                return t1.execTime - t2.execTime;
            }
        });

        // {{5,2},{7,2},{9,4},{6,3},{5,10},{1,1}}
        // {{1,1},{5,2},{5,10},{6,3},{7,2},{9,4}}
        // My ans :  5 0 4 1 3 2
        // Ex ans : [5,0,1,3,2,4]

        List<Integer> result = new ArrayList<>();
        int totalTime = tasksWithIndex[0][1];
        int current = 0;
        while(current<tasksWithIndex.length || !q.isEmpty()){
            while(current<tasksWithIndex.length && tasksWithIndex[current][1]<=totalTime){
                q.add(new Task(tasksWithIndex[current][0], tasksWithIndex[current][1], tasksWithIndex[current][2]));
                current++;
            }

            if(!q.isEmpty()){
                Task finishedTask = q.poll();
                totalTime = totalTime + finishedTask.execTime;
                result.add(finishedTask.index);
            }else{
                totalTime = tasksWithIndex[current][1];
            }


        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
