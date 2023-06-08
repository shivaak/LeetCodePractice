package Backtracking;


import trees.BurnBinaryTree;

import java.util.*;
import java.util.stream.Collectors;

public class Temp {

    public static void main(String[] args) {
        Temp t = new Temp();
        //System.out.println(t.suggestedProducts(new String[]{"mobile", "moneypot", "monitor", "mouse", "mousepad"}, "moc"));
        System.out.println(t.nextGreaterElement(1999999999));
    }

    public int nextGreaterElement(int n) {

        String str = String.valueOf(n);
        int[] nums = new int[str.length()];
        for(int i=0;i<nums.length;i++){
            nums[i]=str.charAt(i)-'0';
        }

        //94783 -> 94837

        //find the number which is smaller than the previous number from reverse
        int i;
        for(i=nums.length-2;i>=0;i--){
            if(nums[i]<nums[i+1])break;
        }

        if(i<0) return -1;


        ////Find smallest number which is greater than nums[i]
        int nextGreaterIndex=-1;
        for(int j=nums.length-1;j>i;j--){
            if(nums[j]>nums[i] && (nextGreaterIndex==-1 || nums[j]<nums[nextGreaterIndex])){
                nextGreaterIndex=j;
            }
        }

        //swap
        int t=nums[nextGreaterIndex];
        nums[nextGreaterIndex]=nums[i];
        nums[i]=t;

        Arrays.sort(nums, i+1, nums.length);

        //987
        long nge=0;
        long multiply=1;
        for(int k=nums.length-1;k>=0;k--){
            nge = nge + nums[k]*multiply;
            multiply*=10;
        }

        return nge > Integer.MAX_VALUE ? -1 : (int)nge;
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        Arrays.sort(products);

        List<List<String>> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<searchWord.length();i++) {
            sb.append(searchWord.charAt(i));
            String prefix = sb.toString();

            int index = Arrays.binarySearch(products, prefix);

            if(index<0){
                index=(-index)-1;
            }

            List<String> suggestions = new ArrayList<>();
            for(int j=index;j<products.length && j<index+3;j++){
                if(products[j].startsWith(prefix)){
                    suggestions.add(products[j]);
                }
            }
            result.add(suggestions);
        }

        return result;

    }



}


