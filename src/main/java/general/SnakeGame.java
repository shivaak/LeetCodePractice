package general;

import java.util.LinkedHashSet;
import java.util.Objects;

public class SnakeGame {

    /*
    Given width = 3, height = 2, and food = [[1,2],[0,1]].

    |S| | |
    | | |F|
     */

    class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private int rows;
    private int cols;

    private int[][] foodPositions;

    private LinkedHashSet<Position> snake;

    private int currFoodPos;

    private int r, c;


    public SnakeGame(int width, int height, int[][] foodPositions) {
        this.cols = width;
        this.rows = height;
        this.foodPositions = foodPositions;

        snake = new LinkedHashSet<>();
        snake.add(new Position(0,0));
        currFoodPos=0; r=0; c=0;
    }

    public int move(String dir) {
        if(dir.equals("U")){
            r--;
        } else if(dir.equals("R")){
            c++;
        } else if(dir.equals("D")){
            r++;
        } else if(dir.equals("L")){
            c--;
        } else{
            System.out.println("Invalid position");
            return -1;
        }

        if(!validate(r, c)){
            return -1;
        }

        if(r==foodPositions[currFoodPos][0] && c==foodPositions[currFoodPos][1]){
            //Eat food
            snake.add(new Position(r,c));
            currFoodPos++;
        }else{
            //Just a move - no food, no bite, no boundary
            snake.add(new Position(r,c));
            snake.stream().findFirst().ifPresent(p -> snake.remove(p));
        }

        return snake.size()-1; //score
    }

    private boolean validate(int r, int c) {
        //touches boundary
        if(r<0 || r>=this.rows || c<0 || c>=this.cols) return false;

        //bites itself
        if(snake.contains(new Position(r, c))) return false;

        return true;
    }

    public static void main(String[] args) {
        int width = 3;
        int height = 2;
        int[][] foodPositions = new int[][]{{1,2}, {0,1}};
        SnakeGame snake = new SnakeGame(width, height, foodPositions);

        System.out.println(snake.move("R")); // 0
        System.out.println(snake.move("D")); // 0
        System.out.println(snake.move("R")); // 1

        System.out.println(snake.move("U")); // 1
        System.out.println(snake.move("L")); // 2
        System.out.println(snake.move("U")); // -1

    }


}
