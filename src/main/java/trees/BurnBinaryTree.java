package trees;

import java.util.*;

public class BurnBinaryTree {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static int minTime(Node root, int target)
    {
        // Your code goes here
        Node targetNode = findTargetNode(root, target);

        Map<Node, Node> parentMap = new HashMap<>();
        fillParentMapBFS(root, parentMap);

        Queue<Node> q = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        q.add(targetNode);

        int time=-1;
        while(!q.isEmpty()){
            int size = q.size();
            time++;
            for(int i=0;i<size;i++){
                Node n = q.poll();
                visited.add(n);
                if(n.left!=null && !visited.contains(n.left)){
                    q.add(n.left);
                }
                if(n.right!=null && !visited.contains(n.right)){
                    q.add(n.right);
                }
                if(parentMap.get(n)!=null && !visited.contains(parentMap.get(n))){
                    q.add(parentMap.get(n));
                }
            }
        }

        return time;
    }

    private static Node findTargetNode(Node root, int target) {
        if(root==null) return null;
        if(root.data==target) return root;

        Node node = findTargetNode(root.left, target);
        if(node!=null)
            return node;

        return findTargetNode(root.right, target);
    }

    private static void fillParentMapBFS(Node root, Map<Node, Node> map){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                Node curr=q.poll();
                if(curr.left!=null){
                    map.put(curr.left, curr);
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    map.put(curr.right, curr);
                    q.add(curr.right);
                }
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(38);
        root.left=new Node(3);
        root.right=new Node(50);

        root.left.left = new Node(1);
        root.left.left.right = new Node(2);

        root.left.right = new Node(35);
        root.left.right.left = new Node(15);
        root.left.right.right = new Node(37);

        root.right.left=new Node(45);
        root.right.left.left=new Node(40);

        System.out.println(minTime(root, 40));

    }
}
