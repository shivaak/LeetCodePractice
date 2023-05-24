package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueen {

    private List<List<String>> output;

    public static void main(String[] args) {
        NQueen n = new NQueen();
        System.out.println(n.solveNQueens(4));
    }

    public List<List<String>> solveNQueens(int n) {

        int[][] board = new int[n][n];

        output = new ArrayList<>();
        helper(board, n, 0);

        return output;

    }

    public void helper(int[][] board, int n, int row) {
        if(row==n){
            List<String> possibleArrangement = new ArrayList<>();
            for(int i=0;i<n;i++){
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<n;j++){
                    if(board[i][j]==0){
                        sb.append(".");
                    }else{
                        sb.append("Q");
                    }
                }
                possibleArrangement.add(sb.toString());
            }
            output.add(possibleArrangement);
            return;
        }

        for(int col=0;col<n;col++){
            if(canPlace(board, row, col, n)){
                board[row][col]=1;
                helper(board, n, row+1);
                board[row][col]=0;
            }
        }
    }

    public boolean canPlace(int[][] board, int row, int col, int n){
        boolean canPlace = true;

        //Horizontal and Vertical
        for(int i=0;i<n;i++){
            if(i!=row && board[i][col]==1) return false;
            if(i!=col && board[row][i]==1) return false;
        }

        //Diagonal
        int tcol= col;
        for(int i=row;i<n;i++){
            if(tcol>=n)break;
            if(i==row && tcol==col){tcol++; continue;}

            if(board[i][tcol]==1) return false;
            tcol++;
        }

        tcol= col;
        for(int i=row;i<n;i++){
            if(tcol<0)break;
            if(i==row && tcol==col){tcol--; continue;}

            if(board[i][tcol]==1) return false;
            tcol--;
        }

        tcol= col;
        for(int i=row;i>=0;i--){
            if(tcol<0)break;
            if(i==row && tcol==col){tcol--; continue;};
            if(board[i][tcol]==1) return false;
            tcol--;
        }

        tcol= col;
        for(int i=row;i>=0;i--){
            if(tcol>=n)break;
            if(i==row && tcol==col){tcol++; continue;};
            if(board[i][tcol]==1) return false;
            tcol++;
        }

        return canPlace;
    }
}
