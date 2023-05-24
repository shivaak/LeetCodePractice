import java.util.*;

public class ReachNumber {

    public static void main(String[] args) {

        ReachNumber rn = new ReachNumber();
        System.out.println(rn.reachNumber(3));
    }

    public int reachNumber(int target) {
        int position = 0, step=1;

        while(position<=target) {
            position = position + step;
            if(position==target) {
                return step;
            }else if(position > target) {
                int diff = position-target;
                while(diff%2!=0) {
                    step++;
                    position = position + step;
                    diff = position-target;
                }
                return step;
            }
            step++;
        }
        return 0;
        // return reachNumber(Math.abs(target), 0, 0);
    }

}
