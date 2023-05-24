package general;

import java.util.Random;

class RandomGenerator {

    int[] arr;
    Random rand;
    int cur = 0;

    public RandomGenerator(int n) {
        arr = new int[n];
        rand = new Random();
        for(int i=0;i<arr.length;i++) {
            arr[i] = i;
            int next = rand.nextInt(i+1);
            if(next != i) {
                int tmp = arr[i];
                arr[i] = arr[next];
                arr[next] = tmp;
            }
        }
    }

    public int generate() {
        if(cur < arr.length)
            return arr[cur++];
        return -1;
    }

    public static void main(String[] args) {
        int n=10;
        RandomGenerator rg = new RandomGenerator(n);
        for(int i=1;i<=n+2;i++){
            System.out.println(rg.generate());
        }
    }
}