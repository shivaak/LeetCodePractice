public class MakeArrayToOne {

    public static void main(String[] args) {
        int arr[] = new int[]{4,7,6};

        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum=sum+(arr[i]/2);
        }
        System.out.println(sum);

    }
}
