package BinarySearch;

public class FindElementInInfiniteArray {

    public static void main(String[] args) {
        FindElementInInfiniteArray f = new FindElementInInfiniteArray();
        System.out.println(f.findElement(new int[]{1,3,5,7,8, 18, 19, 24, 26, 29, 31, 38, 44, 89, 94, 104, 111, 167, 203, 299, 343, 456, 789, 1000, 1099}, 39));
    }

    private int findElement(int[] arr, int target) {

        int start=0, end=1;
        while(arr[end]< target){
            start=end+1;
            end=end*2;
            System.out.println(end);
        }
        return binarySearch(arr, target, start, end);

    }

    private int binarySearch(int[] arr, int target, int low, int high) {
        while(low<=high){
            int mid=low+(high-low)/2;
            if(target<arr[mid]){
                high=mid-1;
            }else if(target>arr[mid]){
                low=mid+1;
            }else{
                return arr[mid];
            }
        }
        return -1;
    }
}
