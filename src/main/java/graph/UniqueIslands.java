package graph;

import java.util.HashSet;
import java.util.Set;

public class UniqueIslands {

    public static void main(String[] args) {
        UniqueIslands u = new UniqueIslands();

        System.out.println(u.countDistinctIslands(new int[][]{
                {1,1,0,0,0}, {1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1},
        }));

    }

    int countDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] newgrid = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                newgrid[i][j]=grid[i][j];
            }
        }

        Set<String> uniqueIslands = new HashSet<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(newgrid[i][j]!=0){
                    StringBuilder path = new StringBuilder();
                    dfs(i,j,newgrid, path, new int[]{i,j});
                    System.out.println(path);
                    uniqueIslands.add(path.toString());
                }
            }
        }
        return uniqueIslands.size();
    }

    void dfs(int row, int col, int[][] grid, StringBuilder path, int[] base){
        if(row<0 || row>=grid.length || col<0 || col>=grid[0].length) return;
        if(grid[row][col]==0)return;

        path.append(Math.abs(base[0]-row)).append(Math.abs(base[1]-col));

        grid[row][col]=0;
        dfs(row,col+1,grid, path, base);
        dfs(row+1,col,grid, path, base);
        dfs(row,col-1,grid, path, base);
        dfs(row-1,col,grid, path, base);
    }
}
