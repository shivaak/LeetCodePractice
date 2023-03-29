package BinarySearch;

public class FindElementInRotatedArray {

    public static void main(String[] args) {
        FindElementInRotatedArray f = new FindElementInRotatedArray();
        System.out.println(f.findElementInRotatedArray(new int[]{2},2));
        System.out.println(f.findElementInRotatedArray(new int[]{4,5,6,7,0,1,2},5));
        System.out.println(f.findElementInRotatedArray(new int[]{4,5,6,7,0,1,2},2));
        System.out.println(f.findElementInRotatedArray(new int[]{4,5,6,7,0,1,2},4));
        System.out.println(f.findElementInRotatedArray(new int[]{1},4));
        System.out.println(f.findElementInRotatedArray(new int[]{1,0,1,1,1},0));
        System.out.println(f.findElementInRotatedArray(new int[]{1,1,1,1,1,1,2,1,1,1,1,1},2));

        System.out.println();
        System.out.println(f.findElementInRotatedArray2(new int[]{2},2));
        System.out.println(f.findElementInRotatedArray2(new int[]{4,5,6,7,0,1,2},5));
        System.out.println(f.findElementInRotatedArray2(new int[]{4,5,6,7,0,1,2},2));
        System.out.println(f.findElementInRotatedArray2(new int[]{4,5,6,7,0,1,2},4));
        System.out.println(f.findElementInRotatedArray2(new int[]{1},4));
        System.out.println(f.findElementInRotatedArray2(new int[]{1,0,1,1,1},0));
        System.out.println(f.findElementInRotatedArray2(new int[]{1,0,1,1,1},0));


    }

    // O(log n + log n )
    private int findElementInRotatedArray(int[] nums, int target){
        int n = nums.length;
        int low=0, high=n-1;

        while(low<high){
            int mid = low +(high-low)/2;
            if(nums[mid]>=nums[high]){
                low=mid+1;
            } else {
                high=mid;
            }
        }
        int pivot = low;

        if(target>=nums[pivot] && target<=nums[n-1]){
            low=pivot;
            high=n-1;
        }else{
            low =0;
            high=pivot-1;
        }
        //System.out.println("pivot : " + pivot);

        while(low<=high){
            int mid = low + (high-low)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }

        return -1;
    }

    //best alternate approach : https://www.youtube.com/watch?v=oTfPJKGEHcc&ab_channel=TECHDOSE

    // O(log n)
    private int findElementInRotatedArray2(int[] nums, int target){
       int n = nums.length;
       int low =0, high =n-1;

       while(low<=high){
           int mid = low+(high-low)/2;
           if(target==nums[mid]){
               return mid;
           }
           // with duplicates we can have this condition, just update left & right
           //new int[]{1,0,1,1,1},0)
           else if((nums[low] == nums[mid]) && (nums[high] == nums[mid]))
           {
               low++;
               high--;
           }
           else if(nums[mid] >= nums[low]){
               if(target>=nums[low] && target<nums[mid]){
                   high=mid-1;
               }else{
                   low=mid+1;
               }
           }else{
               if(target>=nums[mid] && target<=nums[high]){
                   low=mid+1;
               }else{
                   high=mid-1;
               }
           }
       }

       return -1;
    }

    private int findElementInRotatedArray3(int[] nums, int target){
        int n = nums.length;
        int left =0, right =n-1;

        while(left<=right){
            int mid = (left+right)/2;
            if(target==nums[mid]){
                return mid;
            }
            // with duplicates we can have this contdition, just update left & right
            //new int[]{1,0,1,1,1},0)
            else if((nums[left] == nums[mid]) && (nums[right] == nums[mid]))
            {
                left++;
                right--;
            }
            else if(nums[mid] >= nums[left]){
                if(target<=nums[mid] && target>=nums[left]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else{
                if(target>=nums[mid] && target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }

        return -1;
    }
}
