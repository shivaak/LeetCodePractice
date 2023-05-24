import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    Map<Integer, DoublyLinkedList<Integer>> adjList;

    public Graph(){
        adjList = new HashMap<>();
    }

    public void addEdge(int source, int destination){
        adjList.computeIfAbsent(source, k -> new DoublyLinkedList<Integer>()).insertAtTail(destination);
    }

    public void printGraph() {
        Set<Integer> set = adjList.keySet();
        for(int key : set){
            DoublyLinkedList<Integer> adj = adjList.get(key);
            System.out.print("|"+ key + "| => ");
            DoublyLinkedList<Integer>.Node temp = adj.getHeadNode();
            while(temp!=null){
                System.out.print("["+ temp.data +"] -> ");
                temp = temp.next;
            }
            System.out.println("null");
        }

    }

    public static void main(String[] args) {
        Graph g= new Graph();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(4, 0);
        g.addEdge(0, 5);

        g.printGraph();

        System.out.println(g.countSubarrays(new int[]{2,1,4}, 10));


    }

    public long countSubarrays(int[] nums, long k) {

        int n= nums.length;
        int sum=0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,0);
        int result=0;
        for(int i=0;i<n;i++){
            sum=sum+nums[i];

            for(int key : map.keySet()){

            }

            int t = sum*(i+1);

            map.putIfAbsent(sum, 0);
            map.putIfAbsent(sum, i+1);




           /* if(prev+(sum*(i+1))<=k){
                result++;
                prev=sum*(i+1);

            }*/
        }

        return result;
    }


}
