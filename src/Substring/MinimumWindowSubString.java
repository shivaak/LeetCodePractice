package Substring;

class MinimumWindowSubString {

    public static void main(String[] args) {
        MinimumWindowSubString m = new MinimumWindowSubString();
        System.out.println(m.minWindow("ADOBECODEBANC", "ABC"));

    }
    public String minWindow(String s, String t) {

        int[] map = new int[128];
        for(int i=0;i<t.length();i++){
            map[t.charAt(i)]++;
        }

        int left=0, right=0, counter=t.length();
        int minLength = Integer.MAX_VALUE, minStart = 0, minEnd=0;
        while(right<s.length()){
            char c = s.charAt(right);
            if(map[c]>0){
                counter--;
            }
            right++;
            map[c]--;

            while(counter==0){
                if(minLength>(right-left)+1){
                    minLength=(right-left)+1;
                    minStart=left;
                    minEnd=right;
                }

                char lc = s.charAt(left);
                map[lc]++;
                if(map[lc]>0){
                    counter++;
                }
                left++;
            }
        }

        if(minLength<Integer.MAX_VALUE){
            return s.substring(minStart,minEnd);
        }
        return "";
    }
}