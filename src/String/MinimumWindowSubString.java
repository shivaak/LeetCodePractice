package String;

class MinimumWindowSubString {

    public static void main(String[] args) {
        MinimumWindowSubString m = new MinimumWindowSubString();
        System.out.println(m.minWindow("ADOBECODEBANC", "ABC"));

    }
    public String minWindow(String s, String t) {
        
        int[] map = new int[128];
        for(char c : t.toCharArray()){
            map[c]++;
        }
        
        int start=0,end=0, counter=t.length();
        int minWindowLength = Integer.MAX_VALUE, minWindowStart=0;
        while(end<s.length()){
            final char c = s.charAt(end);            
            if(map[c]>0){
                counter--;
            }
            map[c]--;
            end++;
            while(counter==0){
                if(minWindowLength > end-start){
                    minWindowLength=end-start;
                    minWindowStart=start;
                }                
                final char c1 = s.charAt(start);     
                map[c1]++;
                if(map[c1]>0){
                    counter++;
                }                
                start++;
            }            
        }
        
        if(minWindowLength < Integer.MAX_VALUE){
            return s.substring(minWindowStart, minWindowStart+minWindowLength);
        }
        
        return "";
        
    }
}