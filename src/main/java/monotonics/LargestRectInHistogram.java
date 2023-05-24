package monotonics;

import java.util.Stack;

public class LargestRectInHistogram {
    public static void main(String[] args) {
        LargestRectInHistogram l = new LargestRectInHistogram();
        System.out.println(l.largestRectangleArea(new int[]{1,1}));

    }

    class Pair{
        int ind, val;
        Pair(int ind, int val){
            this.ind=ind;
            this.val=val;
        }
    }

    public int largestRectangleArea(int[] heights) {
        int h=heights.length;

        int[] leftSmall = new int[h];
        int[] rightSmall = new int[h];

        Stack<Pair> stack = new Stack<>();

        for(int i=0;i<h;i++){
            while(!stack.isEmpty() && stack.peek().val>=heights[i]) stack.pop();

            if(stack.isEmpty()){
                leftSmall[i]=0;
            }else{
                leftSmall[i]=stack.peek().ind+1;
            }
            stack.push(new Pair(i, heights[i]));
        }

        stack.clear();

        for(int i=h-1;i>=0;i--){
            while(!stack.isEmpty() && stack.peek().val>=heights[i]) stack.pop();

            if(stack.isEmpty()){
                rightSmall[i]=h-1;
            }else{
                rightSmall[i]=stack.peek().ind-1;
            }
            stack.push(new Pair(i, heights[i]));
        }

        int maxArea=0;
        for(int i=0;i<h;i++){
            maxArea = Math.max(maxArea, heights[i]*(rightSmall[i]-leftSmall[i]+1));
        }
        return maxArea;

    }
}
