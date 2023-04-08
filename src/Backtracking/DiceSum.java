package Backtracking;

import java.util.ArrayList;

public class DiceSum {

    ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    int calls=0;
    public static void main(String[] args) {
        DiceSum d = new DiceSum();
        //two 6 face dice, make sum
        d.diceSum(3,8);
        System.out.println(d.calls);
    }

    private void diceSum(int dice, int sum) {

        helper(dice, sum, new ArrayList<>());
        System.out.println(result);
    }

    private void helper(int dice, int sum, ArrayList<Integer> choosen) {
        calls++;
       if(sum<0)return;
        if(dice==0) {
            if(sum==0){
                result.add(new ArrayList<>(choosen));
            }
        }else{
            for(int i=1;i<=6;i++){
                //choose
                choosen.add(i);
                //explore
                helper(dice-1, sum - i, choosen);
                //unchoose
                choosen.remove(choosen.size()-1);
            }
        }
    }
}
