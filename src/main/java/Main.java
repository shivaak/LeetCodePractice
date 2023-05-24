public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Main m = new Main();
        int n=10;
        System.out.println(m.minStepsToOneGreedy(n));
        System.out.println(m.minStepsToOneIterative(n));
        System.out.println(m.minStepsToOneRecursive(n, new int[n+1]));
    }

    private int minStepsToOneGreedy(int n) {
        int steps =0;
        while(n > 1){
            if(n%3==0){
                n=n/3;
            }else if(n%2==0){
                n=n/2;
            }else{
                n=n-1;
            }
            steps++;
        }
        return steps;
    }

    //Top Down approach - Recursive
    private int minStepsToOneRecursive(int n, int[] memo) {
        if (n==1) return 0;

        if(memo[n]!=0) return memo[n];

        int steps = minStepsToOneRecursive(n-1, memo);

        if(n%2==0){
            steps = Math.min(steps , minStepsToOneRecursive(n/2, memo));
        }

        if(n%3==0){
            steps = Math.min(steps , minStepsToOneRecursive(n/3, memo));
        }

        memo[n] = steps + 1;
        return memo[n];
    }

    //Bottom up approach - Iterative
    private int minStepsToOneIterative(int n) {
        int arr[] = new int[n+1];

        for(int i=2;i<=n;i++){
            int val = arr[i-1]+1;
            if(i%2==0){
                val = Math.min(val, arr[i/2]+1);
            }
            if(i%3==0){
                val = Math.min(val, arr[i/3]+1);
            }
            arr[i]=val;
        }

        return arr[n];
    }
}