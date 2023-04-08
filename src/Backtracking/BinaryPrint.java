package Backtracking;

public class BinaryPrint {

    /*
       digit : 1
       0
       1

       digit  2 :
       00
       01
       10
       11

       digit 3 :
       000
       001
       010
       011
       100
       101
       110
       111

     */
    public void printBinary(int digit){
        /*
         if digit==0:
            print nothing
         else:
            print 0
            print (n-1) of print binary
            print 1
            print (n-1) of print binary
         */

        helper(digit,"");

    }

    public void helper(int digit, String output){

        if(digit==0){
            System.out.println(output);
            //output="";
            return;
        }

        helper(digit-1, output+"0");
        helper(digit-1, output + "1");

    }

    public static void main(String[] args) {
        BinaryPrint m = new BinaryPrint();
        m.printBinary(100);

    }
}
