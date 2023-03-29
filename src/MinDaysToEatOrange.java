import java.util.*;


public class MinDaysToEatOrange {

    public static void main(String[] args) {
        MinDaysToEatOrange m = new MinDaysToEatOrange();

        int n=3;
        System.out.println(m.minDaysBreadthFirst(n));

        /*
        for(int n=1;n<=100;n++){
           System.out.print(n + " --> " + m.minDays(n, new int[n+1]) + " : ");

            // Bottom up using array
            int arr[] = new int[n+1];
            arr[1]=1;
            for (int i=2;i<=n;i++) {
                arr[i] = arr[i-1] + 1;
                if(i%2==0){
                    arr[i] = Math.min(arr[i], arr[i- i/2] + 1) ;
                }

                if(i%3==0){
                    arr[i] = Math.min(arr[i], arr[i - (2*(i/3))] + 1) ;
                }
            }
            System.out.println(arr[n] + " : ");
        }*/

    }

    public int minDaysBreadthFirst(int n) {
        //Breadth First Approach
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        int steps=0;
        Set<Integer> set = new HashSet<>();
        while(q.size()>0){
            steps++;
            int size = q.size();
            for(int i=0;i<size;i++){
                int current = q.poll();
                if(current==0) return steps -1;
                if(set.contains(current)) continue;

                set.add(current);
                if(current%3==0) q.add(current/3);
                if(current%2==0) q.add(current/2);
                q.add(current-1);
            }
        }
        return steps;
    }

    public int minDays(int n, int[] mem) {

        if(n==0) return 0;

        if(mem[n]!=0) return mem[n];

        int days = minDays(n-1, mem);

        if (n%3==0){
            days = Math.min(days, minDays(n/3, mem));
        }

        if (n%2==0){
            days = Math.min(days, minDays(n/2, mem));
        }

        mem[n]=days+1;
        return mem[n];
    }


}
