package Backtracking;

public class DecimalPrint {

    public static void main(String[] args) {
        DecimalPrint d = new DecimalPrint();
        d.print(1);
    }

    private void print(int digits) {
        helper(digits , "");
    }

    private void helper(int digits, String s) {
        if(digits==0){
            System.out.println(s);
            return;
        }

        for(int i=0;i<10;i++){
            helper(digits-1, s+i);
        }
    }


}
