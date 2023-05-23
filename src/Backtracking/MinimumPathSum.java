package Backtracking;

public class MinimumPathSum {

    public static void main(String[] args) {
        MinimumPathSum m = new MinimumPathSum();
        System.out.println(m.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }

    public int minPathSum(int[][] grid) {

        //Bottom up
       /* int[][] dp = new int[grid.length][grid[0].length];
        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[0].length;c++){
                if(r==0 && c==0) {
                    dp[r][c]=grid[r][c];
                    continue;
                }
                int up = grid[r][c];
                if(r>0) up += minPathSum(grid, r-1, c, dp);
                else up += (int)Math.pow(10,9);

                int down = grid[r][c];
                if(c>0) down += minPathSum(grid, r, c-1, dp);
                else down += (int)Math.pow(10,9);

                dp[r][c]=Math.min(up,down);
            }
        }
        return dp[grid.length-1][grid[0].length-1]; */


        // Top down
        int[][] dp = new int[grid.length][grid[0].length];

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                dp[i][j]=-1;
            }
        }

        return minPathSum(grid, grid.length-1, grid[0].length-1, dp);
    }

    public int minPathSum(int[][] grid, int r, int c, int[][] dp) {
        if(r==0 && c==0) return grid[r][c];
        if(r<0 || c<0) return Integer.MAX_VALUE;

        if(dp[r][c]!=-1) return dp[r][c];

        int up = grid[r][c] + minPathSum(grid, r-1, c, dp);
        int left = grid[r][c] + minPathSum(grid, r, c-1, dp);

        return dp[r][c]=Math.min(up, left);

    }
}
