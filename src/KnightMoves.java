public class KnightMoves {
    int[][] moves = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};

    public static void main(String[] args) {
        KnightMoves k = new KnightMoves();
        System.out.println(k.checkValidGrid(new int[][]{{0,3,6},{5,8,1},{2,7,4}}));
        System.out.println(k.checkValidGrid(new int[][]{{0,11,16,5,20},{17,4,19,10,15},{12,1,8,21,6},{3,18,23,14,9},{24,13,2,7,22}}));
    }
    public boolean checkValidGrid(int[][] grid) {
        int total = grid[0].length * grid[0].length;

        int m=0,n=0;
        for(int i=1;i<total;i++) {
            int[] next = move(grid, grid[m][n], m, n);
            if(next[0]==-1) return false;
            m=next[0];
            n=next[1];
        }
        return true;
    }

    public int[] move(int[][] grid, int current, int m, int n) {
        for(int i=0;i<moves.length;i++){
            if(isValid(m+moves[i][0],n+moves[i][1], grid[0].length) && grid[m+moves[i][0]][n+moves[i][1]]==current+1)
                return new int[]{m+moves[i][0], n+moves[i][1]};
        }
        return new int[]{-1, -1};
    }

    public boolean isValid(int m, int n, int size) {
        if(n<0 || m<0 || n>=size || m>=size) return false;
        return true;
    }
}
