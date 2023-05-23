package Substring;

/*
Given an integer array a, and 2 integers - c and k, where c is the favourite number and k is the maximum number of replacements we can make. We have to find the maximum number of contiguous elements which are equal to c. We can replace k number of elements with any number we want.

Example - a = [1,2,2,3,2,2,2,4], c=2, k=2.

In this case, without any replacement, maximum number of contiguous elements will be 3 (from index 4 to index 6).
We can replace element at index 3 with number 2 so now array will be a = [1,2,2,2,2,2,2,4] , maximum number of contiguous elements will be 6 (from index 1 to index 6).
For last replacement, we can replace either element at index 0 or index 7 with number 2, so maximum number of contiguous elements will be 7, which is the answer..
 */

public class MaxContigiousElement {
    public static void main(String[] args) {
        MaxContigiousElement m = new MaxContigiousElement();
        System.out.println(m.maxContigiousElement(new int[]{1,3,2,2,2,3,2}, 2, 1));
    }

    private int maxContigiousElement(int[] arr, int c, int k){

        int left=0, right=0, counter=k;
        int currWindow=0, maxWindow=Integer.MIN_VALUE;
        while(right<arr.length){
            int n = arr[right];
            if(n!=c) counter--;
            right++;
            currWindow++;

            while(counter<0){
                int ln = arr[left];
                if(ln!=c) counter++;
                currWindow--;
                left++;
            }
            maxWindow = Math.max(maxWindow, currWindow);
        }
        return maxWindow;


    }
}
