package Substring;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagrams {
    public static void main(String[] args) {
        FindAllAnagrams f = new FindAllAnagrams();
        System.out.println(f.findAnagrams("abaacbabc", "abc"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        int[] map = new int[128];

        for(char c : p.toCharArray()){
            map[c]++;
        }

        int left=0, right=0, counter=p.length();
        List<Integer> result = new ArrayList<>();

        while(right<s.length()){
            char c = s.charAt(right);
            map[c]--;
            if(map[c]>=0) counter--;
            right++;
            while(counter==0){
                char lc = s.charAt(left);
                if(map[lc]==0){
                    counter++;
                    if(right-left==p.length()){
                        result.add(left);
                    }
                }
                map[lc]++;
                left++;
            }
        }

        return result;
    }
}
