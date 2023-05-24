// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class RookProblem {

    public static void main(String[] args) {
        RookProblem r = new RookProblem();
        System.out.println(r.solution(new int[][]{{15, 1, 5}, {16, 3, 8}, {2, 6, 4}}));
    }

    public int solution(int[][] A) {
        int n = A.length;
        int m = A[0].length;

        List<List<Integer>> row = new ArrayList<>();
        List<List<Integer>> col = new ArrayList<>();

        for (int i = 0 ; i < n ; i++) {
            int max = 0, maxCol = 0, secondMax = 0, secondMaxCol = 0;
            for (int j = 0 ; j < m ; j++) {
                if (A[i][j] >= max) {
                    secondMax = max;
                    secondMaxCol = maxCol;

                    max = A[i][j];
                    maxCol = j;
                } else if (A[i][j] >= secondMax) {
                    secondMax = A[i][j];
                    secondMaxCol = j;
                }
            }
            row.add(new ArrayList<>(Arrays.asList(maxCol, secondMaxCol)));
        }
        for (int j = 0 ; j < m ; j++) {
            int max = 0, maxRow = 0, secondMax = 0, secondMaxRow = 0;
            for (int i = 0 ; i < n ; i++) {
                if (A[i][j] >= max) {
                    secondMax = max;
                    secondMaxRow = maxRow;

                    max = A[i][j];
                    maxRow = i;
                } else if (A[i][j] >= secondMax) {
                    secondMax = A[i][j];
                    secondMaxRow = i;
                }
            }
            col.add(new ArrayList<>(Arrays.asList(maxRow, secondMaxRow)));
        }
        int ans = 0;
        for (int i = 0 ; i < n ; i++) {
            int r = i, c = row.get(i).get(0);
            for (int j = 0 ; j < m ; j++) {
                if (j != c) {
                    if (!col.get(j).get(0).equals(r))
                        ans = Math.max(ans, A[r][c] + A[col.get(j).get(0)][j]);
                    else
                        ans = Math.max(ans, A[r][c] + A[col.get(j).get(1)][j]);
                }
            }
        }
        for (int j = 0 ; j < m ; j++) {
            int c = j, r = col.get(j).get(0);
            for (int i = 0 ; i < n ; i++) {
                if (i != r) {
                    if (!row.get(i).get(0).equals(c))
                        ans = Math.max(ans, A[r][c] + A[i][row.get(i).get(0)]);
                    else
                        ans = Math.max(ans, A[r][c] + A[i][row.get(i).get(1)]);
                }
            }
        }
        return ans;
    }

 

}
