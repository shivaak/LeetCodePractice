package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RookLongestSubSequence {

    public static void main(String[] args) {
        RookLongestSubSequence r = new RookLongestSubSequence();
        System.out.println(r.findLongestSubSequence(new int[][]{{6,3,-4,16}, {8,0,12,7}, {37,10,-3,35}, {-1,27,13,29}}));
    }

    int maxSequenceLength=0;
    List<List<int[]>> lengthyPath = new ArrayList<>();

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
                List<int[]> path = new ArrayList<>();
                path.add(new int[]{i,j});
                dfs(i, j, matrix, path, dp);
                dp[i][j]=path.size();
            }
        }

        for(List<int[]> list : lengthyPath){
            for(int[] p : list){
                System.out.print(p[0] + "," + p[1] + " -> ");
            }
            System.out.println();
        }

        System.out.println("Execution Time : " + (System.currentTimeMillis() - startTime));
        return maxSequenceLength;
    }

    private void dfs(int startRow, int startCol, int[][] matrix, List<int[]> path, int[][] dp) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int currentValue = matrix[startRow][startCol];
        boolean pathFound=false;

       // if(dp[startRow][startCol]!=-1) return dp[startRow][startCol];

        int length=0;
        //vertical
        for(int i=0;i<rows;i++){
            if(i!=startRow && matrix[i][startCol]>currentValue){
                pathFound=true;
                path.add(new int[]{i, startCol});
                dfs(i, startCol, matrix, path, dp);
                path.remove(path.size()-1);
                length--;
            }
        }

        //horizontal
        for(int i=0;i<cols;i++){
            if(i!=startCol && matrix[startRow][i]>currentValue){
                pathFound=true;
                path.add(new int[]{startRow, i});
                dfs(startRow, i, matrix, path, dp);
                path.remove(path.size()-1);
            }
        }

        if(maxSequenceLength<path.size()){
            maxSequenceLength=path.size();
            lengthyPath=new ArrayList<>();
         //   if(!pathFound){
                lengthyPath.add(new ArrayList<>(path));
           // }
        }else if(maxSequenceLength==path.size()){
            lengthyPath.add(new ArrayList<>(path));
        }



    }
}
