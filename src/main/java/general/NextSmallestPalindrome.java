package general;

public class NextSmallestPalindrome {

    public static void main(String[] args) {
        NextSmallestPalindrome n = new NextSmallestPalindrome();
        System.out.println(n.findNextPalindrome(97531));
        System.out.println(n.findNextPalindrome(23545));
        System.out.println(n.findNextPalindrome(53545));
        System.out.println(n.findNextPalindrome(13937));
        System.out.println(n.findNextPalindrome(99902));
        System.out.println(n.findNextPalindrome(9731));
        System.out.println(n.findNextPalindrome(1337));
        System.out.println(n.findNextPalindrome(2995));
        System.out.println(n.findNextPalindrome(999));
    }

    private int findNextPalindrome(int n) {

        if(isPalindrome(n)){
            n++;
        }

        if(n<=9) return n+1;

        String str = String.valueOf(n);
        int[] nums = new int[str.length()];
        for(int i=0;i<nums.length;i++){
            nums[i]=Character.getNumericValue(str.charAt(i));
        }

        if(nums.length%2==0){
            //even length
            int mid = (nums.length/2)-1;
            int left = mid;
            if(isReverseOfLeftGreater(nums, left, mid+1)){
                mirror(nums, mid, mid);
            }else{
                if(nums[mid]==9){
                    int carry=1;
                    nums[mid]=0;
                    int next=mid-1;
                    while(carry>0){
                        nums[next]=(nums[next]+1)%10;
                        carry = nums[next]/10;
                        next--;
                    }

                }else{
                    nums[mid]++;
                }
                mirror(nums, mid, mid);
            }
        }else{
            //odd
            int mid = nums.length/2;
            int left = mid-1;
            if(isReverseOfLeftGreater(nums, left, mid+1)){
                mirror(nums, mid, mid-1);
            }else{
                if(nums[mid]==9){
                    int carry=1;
                    nums[mid]=0;
                    int next=mid-1;
                    while(carry>0){
                        nums[next]=(nums[next]+1)%10;
                        carry = nums[next]/10;
                        next--;
                    }

                }else{
                    nums[mid]++;
                }
                mirror(nums, mid, mid-1);
            }
        }

        int result=0, multiply=1;
        for(int i=nums.length-1;i>=0;i--){
            result = result + nums[i]*multiply;
            multiply*=10;
        }

        return result;
    }

    private boolean isPalindrome(int n) {
        String str = String.valueOf(n);
        int left=0, right=str.length()-1;

        while(left<right){
            if(str.charAt(left)!=str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private void mirror(int[] nums, int mid, int left) {
        for(int right=mid+1;right<nums.length;right++){
            nums[right]=nums[left];
            left--;
        }

    }

    private boolean isReverseOfLeftGreater(int[] nums, int left, int right) {
        while(nums[left]==nums[right]){
            left--; right++;
        }
        if(nums[left]>nums[right]) return true;

        return false;
    }
}
