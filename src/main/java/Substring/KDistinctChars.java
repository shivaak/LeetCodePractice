package Substring;

import java.util.HashMap;
import java.util.Map;

public class KDistinctChars {
    public static void main(String[] args) {
        KDistinctChars k = new KDistinctChars();

        System.out.println(k.kDistinctChars(2, "abbbbbbc"));
        System.out.println(k.kDistinctChars(3, "abcddefg"));
        System.out.println(k.kDistinctChars(3, "aaaaaaaa"));
        System.out.println(k.kDistinctChars(1, "abcefg"));

        System.out.println("------");

        System.out.println(k.kDistinctChars_template(2, "abbbbbbc"));
        System.out.println(k.kDistinctChars_template(3, "abcddefg"));
        System.out.println(k.kDistinctChars_template(3, "aaaaaaaa"));
        System.out.println(k.kDistinctChars_template(1, "abcefg"));
    }


    public int kDistinctChars_template(int k, String str) {

        int[] map = new int[128];
        int left=0, right=0, counter=0;
        int maxLength = 0;

        while(right<str.length()){
            char c = str.charAt(right);
            if(map[c]==0) counter++;
            map[c]++;
            right++;

            while(counter>k){
                char lc = str.charAt(left);
                map[lc]--;
                if(map[lc]==0) counter--;
                left++;
            }
            maxLength = Math.max(maxLength, right-left);
        }

        return maxLength;


    }

    public int kDistinctChars(int k, String str) {
        Map<Character, Integer> map = new HashMap<>();

        int left=0, maxSubStringLength=0, currLength=0;
        for(int right=0; right< str.length(); right++){
            char c = str.charAt(right);
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c)+1);

            if(map.size()<=k) currLength++;
            else maxSubStringLength = Math.max(maxSubStringLength, currLength);

            while(map.size()>k){
                char lc = str.charAt(left);
                map.put(lc, map.get(lc)-1);
                if(map.get(lc)==0){
                    map.remove(lc);
                    currLength=right-left;
                }
                left++;
            }
        }

        maxSubStringLength = Math.max(maxSubStringLength, currLength);


        return maxSubStringLength;
    }
}
