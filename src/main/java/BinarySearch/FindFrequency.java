package BinarySearch;

public class FindFrequency {

    public static void main(String[] args) {
        FindFrequency f = new FindFrequency();
        System.out.println(f.findFrequency(new int[]{0,1,2,2,4,4,4,4,4,4,4,6}, 4));

    }

    private int findFrequency(int[] nums, int target) {
        int n= nums.length;
        int low=0, high=n-1;

        int left=-1;
        while(low<=high){
            int mid=low+(high-low)/2;

            if(target==nums[mid]){
                left=mid;
                high=mid-1;
            } else if(target>nums[mid]){
                low=mid+1;
            } else{
                high=mid-1;
            }
        }

        int right=-1;
        low=0; high=n-1;
        while(low<=high){
            int mid=low+((high-low)/2);

            if(target==nums[mid]){
                right=mid;
                low=mid+1;
            } else if(target>nums[mid]){
                low=mid+1;
            } else{
                high=mid-1;
            }
        }
        if(left==-1 || right==-1) return -1;
        return right-left+1;
    }


}
