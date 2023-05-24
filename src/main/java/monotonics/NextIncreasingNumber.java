package monotonics;

import java.util.Arrays;
import java.util.Stack;

public class NextIncreasingNumber {

    public static void main(String[] args) {
        NextIncreasingNumber n = new NextIncreasingNumber();
        System.out.println(Arrays.toString(n.solve(new int[]{2,10,12,1,11})));
        System.out.println(Arrays.toString(n.solveRotated(new int[]{2,10,12,1,11})));
        System.out.println(n.nextGreaterElement(12));
        System.out.println(n.nextGreaterElement(230241));
    }

    public int nextGreaterElement(int n) {
        String number = String.valueOf(n);

        int nums[] = new int[number.length()];
        int i,j;
        for(i=0;i<number.length();i++){
            nums[i]=Character.getNumericValue(number.charAt(i));
        }

        //Find out the index of a number which is lesser than its next number from the last
        for(i=nums.length-2;i>=0;i--){
            if(nums[i]<nums[i+1])break;
        }

        //Edge case no such element found
        if(i<0)return -1;

        //Find smallest number which is greater than nums[i]
        int nextGreaterIndex=-1;
        for(j=nums.length-1;j>i;j--){
            if(nums[j]>nums[i] && (nextGreaterIndex==-1 || nums[j]<nums[nextGreaterIndex])){
                nextGreaterIndex=j;
            }
        }

        int t=nums[nextGreaterIndex];
        nums[nextGreaterIndex]=nums[i];
        nums[i]=t;

        Arrays.sort(nums,i+1,nums.length);

        int nge=0, multiply=1;
        for(i=nums.length-1;i>=0;i--){
            nge+=nums[i]*multiply;
            multiply*=10;
        }
        return nge;
    }

    private int[] solve(int[] nums) {
        int[] nge = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        for(int i=nums.length-1;i>=0;i--){
            int n= nums[i];

            while(!stack.isEmpty() && stack.peek()<=n){
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

            while(!stack.isEmpty() && stack.peek()<=n){
                stack.pop();
            }
            nge[i%len] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(n);
        }
        return nge;
    }


}
