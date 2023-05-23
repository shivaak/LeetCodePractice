package BinarySearch;

import java.util.HashMap;
import java.util.Map;

public class IsConfusingNumberII {

    int count=0, N;
    public static void main(String[] args) {
        IsConfusingNumberII is = new IsConfusingNumberII();
        int n=100;
        for(int i=1;i<=n;i++){
            if(is.isConfusingNumber(i)) {
                System.out.println(i);
            }
        }


        int ans = 0;
        int[] nums = {1, 6, 8, 9};
        int length = nums.length;
        for (int i = 0; i < length; i++)
            ans += is.dfs(n, nums[i]);
        System.out.println("count : " + ans);
    }

    public int dfs(int N, int curr) {
        if(curr>N){
            return 0;
        }
        int ans=0;

        if(isConfusingNumber(curr)){
            ans++;
        }

        int[] nums = {0, 1, 6, 8, 9};
        for(int i=0;i<nums.length;i++){
            ans += dfs(N, curr*10+nums[i]);
        }

        return ans;
    }


    private boolean isConfusingNumber(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,0);
        map.put(1,1);
        map.put(6,9);
        map.put(8,8);
        map.put(9,6);

        int t=n;

        int num=0;
        while(n>0){
            int rem = n%10;
            if(!map.containsKey(rem)) return false;
            num = num*10 + map.get(rem);
            n=n/10;
        }
        return num!=t;
    }

}
