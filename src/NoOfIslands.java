public class NoOfIslands {
    public static void main(String[] args) {
        NoOfIslands n = new NoOfIslands();
        int output = n.numIslands(new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        });

        System.out.println(output);
    }


    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int total = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]=='1'){
                    total++;
                    markThisIsland(grid,i,j);
                }
            }
        }
        return total;
    }

    private void markThisIsland(char[][] grid, int row, int col) {
        if(row<0 || row>=grid.length || col<0 || col>=grid[0].length || grid[row][col]!='1'){
            return;
        }
        grid[row][col]='2';
        markThisIsland(grid,row+1,col); //down
        markThisIsland(grid,row,col+1); //right
        markThisIsland(grid,row-1,col); //up
        markThisIsland(grid,row,col-1); //left
    }
}
