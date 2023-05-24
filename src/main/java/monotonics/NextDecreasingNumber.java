package monotonics;

import java.util.Arrays;
import java.util.Stack;

public class NextDecreasingNumber {

    public static void main(String[] args) {
        NextDecreasingNumber n = new NextDecreasingNumber();
        System.out.println(Arrays.toString(n.solve(new int[]{2,10,12,1,11})));
        System.out.println(Arrays.toString(n.solveRotated(new int[]{2,10,12,1,11})));
    }



    private int[] solve(int[] nums) {
        int[] nge = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        for(int i=nums.length-1;i>=0;i--){
            int n= nums[i];

            while(!stack.isEmpty() && stack.peek()>=n){
                stack.pop();
            }
            nge[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(n);
        }
        return nge;
    }

    private int[] solveRotated(int[] nums) {
        int[] nge = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        int len = nums.length;
        for(int i=2*len-1;i>=0;i--){
            int n= nums[i%len];

            while(!stack.isEmpty() && stack.peek()>=n){
                stack.pop();
            }
            nge[i%len] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(n);
        }
        return nge;
    }


}
