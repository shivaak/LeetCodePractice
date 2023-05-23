package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RookLongestSubSequenceOnlyPathCount {

    public static void main(String[] args) {
        RookLongestSubSequenceOnlyPathCount r = new RookLongestSubSequenceOnlyPathCount();
        //System.out.println(r.findLongestSubSequence(new int[][]{{6,3,-4,16}, {8,0,12,7}, {37,10,-3,35}, {-1,27,13,29}}));
        System.out.println(r.findLongestSubSequence(new int[][]{{6,3}, {8,0}}));
    }


    int maxSequenceLength = 0;
    int[][] dp;

    private int findLongestSubSequence(int[][] matrix) {

        long startTime = System.currentTimeMillis();

        int rows = matrix.length;
        int cols = matrix[0].length;
        dp = new int[rows][cols];

        for(int[] row : dp){
            Arrays.fill(row, -1);
        }


       for(int i=0;i<rows;i++){
         for(int j=0;j<cols;j++){
                System.out.println("Row " + i + " Col " + j);
                System.out.println("total " + 1+dfs(i, j, matrix, 0, dp));
                //System.out.println(dp[i][j]);
            }
        }

        System.out.println("Execution Time : " + (System.currentTimeMillis() - startTime));
        return maxSequenceLength;
    }

    private int dfs(int startRow, int startCol, int[][] matrix, int path, int[][] dp) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int currentValue = matrix[startRow][startCol];
        int max=0;

       // if(dp[startRow][startCol]!=-1) return dp[startRow][startCol];
        boolean pathFound=false;

        //vertical
        for(int i=0;i<rows;i++){
            if(i!=startRow && matrix[i][startCol]>currentValue){
                pathFound=true;
                path = 1+dfs(i, startCol, matrix, path, dp);
            }
        }

        //horizontal
        for(int i=0;i<cols;i++){
            if(i!=startCol && matrix[startRow][i]>currentValue){
                pathFound=true;
                path = 1+dfs(startRow, i, matrix, path, dp);
            }
        }
        if(!pathFound){
            System.out.println(path);
            max = Math.max(max, path);
            return max;
        }

        return path;



    }
}
