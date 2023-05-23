package strings;

import java.util.HashMap;
import java.util.Map;

public class String180Rotate {
    public static void main(String[] args) {
        String180Rotate s = new String180Rotate();
        String[] strings = {"dop", "abcd","xoox", "temp", "moow", "moo", "doow", "xooox","xodox", "d", "o", ""};
        for(String string : strings){
            if(s.isSameAfterRotating180Degree(string))
            {
                System.out.println(string);
                System.out.println("---");
            }


        }
    }

    private boolean isSameAfterRotating180Degree(String str) {
        Map<Character, Character> map = new HashMap<>();
        map.put('d', 'p');
        map.put('p', 'd');
        map.put('g', 'b');
        map.put('b', 'g');
        map.put('h', 'y');
        map.put('y', 'h');
        map.put('m', 'w');
        map.put('w', 'm');
        map.put('o', 'o');
        map.put('x', 'x');
        map.put('z', 'z');

        int start=0, end=str.length()-1;
        while(start<end){
            Character c=str.charAt(start);
            if(!map.containsKey(c) || str.charAt(end)!=map.get(c)) {
                return false;
            }
            start++;
            end--;
        }
        if(str.length()%2==1){
            Character c = str.charAt(start);
            if(c=='o' || c=='x' || c=='z') return true;
            else return false;
        }
        return true;
    }
}
