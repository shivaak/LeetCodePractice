package Backtracking;

public class Permutation {

    public static void main(String[] args) {
        String input = "abc";
        System.out.println("All permutations of " + input + " are: ");
        permute(input.toCharArray(), 0);
    }

    public static void permute(char[] arr, int index) {
        if (index == arr.length - 1) {
            System.out.println(String.valueOf(arr));
        } else {
            for (int i = index; i < arr.length; i++) {
                swap(arr, i, index);
                permute(arr, index + 1);
                swap(arr, i, index);
            }
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
