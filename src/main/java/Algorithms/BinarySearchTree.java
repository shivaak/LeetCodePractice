package Algorithms;

import java.util.*;

public class BinarySearchTree {

    class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    class Pair{
        Node node;
        int hd;
        int level;
        Pair(Node node, int hd, int level){
            this.node = node;
            this.hd = hd;
            this.level = level;
        }
    }

    private Node root;

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(7);
        bst.insert(20);
        bst.insert(5);
       /* bst.insert(15);
        bst.insert(10);
        bst.insert(4);
        bst.insert(4);
        bst.insert(33);
        bst.insert(2);
        bst.insert(25);
        bst.insert(6);*/

        for(int i=1;i<=26;i++){
            String result = bst.search(i)==null ? "false" : "true";
            System.out.println(String.format("%d --> %s", i , result));
        }

        bst.traverseBreadthWise();
        bst.preOrderTraversal();
        System.out.println();
        bst.postOrderTraversal();
        System.out.println();
        bst.inOrderTraversal();

        System.out.println(bst.findBottomLeftValue());

        for(List<Integer> lst: bst.verticalTraversal()){

            System.out.print(lst + " ");
        }

    }

    public List<List<Integer>> verticalTraversal() {
        return verticalTraversal(root);
    }

    public List<List<Integer>> verticalTraversal(Node root) {

        Queue<Pair> q = new LinkedList<>();
        TreeMap<Integer, Queue<Pair>> map = new TreeMap<>();

        //Level order traversal
        q.add(new Pair(root,0,0));
        q.add(null);
        PriorityQueue<Pair> pq;
        int level = 1;
        while(!q.isEmpty()){
            Pair current = q.poll();

            if(current==null){
                if(!q.isEmpty()){
                    level++;
                    q.add(null);
                    continue;
                }
            } else{
                map.putIfAbsent(current.hd, new PriorityQueue<>((a,b) -> {
                    if(a.level==b.level){
                        return a.node.data - b.node.data;
                    }else{
                        return a.level - b.level;
                    }
                }));
                map.get(current.hd).add(current);
                if(current.node.left!=null) q.add(new Pair(current.node.left, current.hd-1, level));
                if(current.node.right!=null) q.add(new Pair(current.node.right, current.hd+1, level));
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for(int key : map.keySet()){
            Queue<Pair> tempq = map.get(key);
            ArrayList<Integer> list = new ArrayList<>();
            while(!tempq.isEmpty()){
                list.add(tempq.poll().node.data);
            }
            result.add(list);

        }
        return result;
    }

    private void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node root) {
        if(root==null) return;

        inOrderTraversal(root.left);
        System.out.print(root.data + " ");
        inOrderTraversal(root.right);
    }

    private void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node root) {
        if(root==null) return;

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data + " ");
    }

    private void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node node) {
        if(node==null) return;
        System.out.print(node.data + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public int findBottomLeftValue() {
        return findBottomLeftValue(root);
    }

    private int findBottomLeftValue(Node root) {

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        int leftMost = root.data;
        while(!q.isEmpty()){
            Node currentNode = q.poll();
            if(currentNode==null){
                if(!q.isEmpty()){
                    q.add(null);
                    leftMost = q.peek().data;
                    continue;
                }
            }else{
                if(currentNode.left!=null)q.add(currentNode.left);
                if(currentNode.right!=null)q.add(currentNode.right);
            }

        }

        return leftMost;
    }

    public void traverseBreadthWise() {
        traverseBreadthWise(root);
    }

    private void traverseBreadthWise(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        System.out.print("[");
        while(!q.isEmpty()){
            Node current = q.poll();
            if(current==null) {
                if(!q.isEmpty()){
                    System.out.print(" ] [");
                    q.add(null);
                    continue;
                }
            } else{
                System.out.print(" " + current.data);
                if(current.left!=null)q.add(current.left);
                if(current.right!=null)q.add(current.right);
            }

        }
        System.out.println(" ]");
    }

    public void insert(int data){
        root = insert(root, data);
    }

    public Node search(int data){
        return search(root, data);
    }

    private Node search(Node node, int data) {
        if(node==null) return null;

        if (data == node.data){
            return node;
        } else if (data< node.data) {
            return search(node.left, data);
        } else  {
            return search(node.right, data);
        }
    }

    private Node insert(Node node, int data){
        if(node==null){
            node = new Node(data);
            return node;
        }
        if(data< node.data){
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        }
        return node;
    }

}
