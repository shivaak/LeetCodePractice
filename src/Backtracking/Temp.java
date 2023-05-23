package Backtracking;


import trees.BurnBinaryTree;

import java.util.*;
import java.util.stream.Collectors;

public class Temp {

    public static void main(String[] args) {
        Temp t = new Temp();
        System.out.println(t.suggestedProducts(new String[]{"mobile", "moneypot", "monitor", "mouse", "mousepad"}, "moc"));
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


