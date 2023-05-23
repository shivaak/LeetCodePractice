package dynamicprogramming;

public class UniquePathsIII {

    public static void main(String[] args) {
        UniquePathsIII u = new UniquePathsIII();
        System.out.println(u.uniquePathsIII(new int[][]{
                {1,0,0,0},{0,0,0,0},{0,0,2,-1}
        }));
    }

    public int uniquePathsIII(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int start[] = new int[2];
        int end[] = new int[2];
        int noOfSpaces=0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){

                if(grid[i][j]==1){
                    start[0]=i;
                    start[1]=j;
                }
                if(grid[i][j]==2){
                    end[0]=i;
                    end[1]=j;
                }
                if(grid[i][j]==0 || grid[i][j]==1){
                    noOfSpaces++;
                }
            }
        }

        boolean[][] visited = new boolean[m][n];
        return uniquePathsIII(grid, m, n, start[0], start[1], end[0], end[1], noOfSpaces, 1, visited);
    }

    private int uniquePathsIII(int[][] grid, int m, int n, int r, int c, int endR, int endC,
                               int noOfSpaces, int totalCellsVisited, boolean[][] visited){
        if(r==endR && c==endC){
            if(totalCellsVisited-1==noOfSpaces){
                return 1;
            }
        }

        if(r<0 || r>=m) return 0;
        if(c<0 || c>=n) return 0;
        if(grid[r][c]==-1) return 0;
        if(grid[r][c]==2) return 0;
        if(visited[r][c]) return 0;

        int sum=0;
        visited[r][c]=true;
        totalCellsVisited++;
        sum += uniquePathsIII(grid, m, n, r+1, c, endR, endC, noOfSpaces, totalCellsVisited, visited);
        sum += uniquePathsIII(grid, m, n, r-1, c, endR, endC, noOfSpaces, totalCellsVisited, visited);
        sum += uniquePathsIII(grid, m, n, r, c+1, endR, endC, noOfSpaces, totalCellsVisited, visited);
        sum += uniquePathsIII(grid, m, n, r, c-1, endR, endC, noOfSpaces, totalCellsVisited, visited);

        visited[r][c]=false;
        totalCellsVisited--;

        return sum;
    }
}
