
class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int data){
        this.data = data;
    }
}
/*
        1
       /  \
      2    3
     / \  / \
    4  5  6  7
*/
public class BinarySearchTree {

    public static void main(String[] args) {
        Node root = constructBinaryTree();
        
        breadthFirst(root);
        DepthFirst(root);
    }

    private static void DepthFirst(Node root) {


    }

    private static void breadthFirst(Node root) {
        
    }

    private static Node constructBinaryTree() {

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        return root;
    }
}
