import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        String value;
        Node left;
        Node right;

        public Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    static int N;
    static Node head = new Node("A", null, null);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            insertNode(head, info[0], info[1], info[2]);
        }

        preOrder(head);
        System.out.println();
        inOrder(head);
        System.out.println();
        postOrder(head);
        System.out.println();
    }

    static void insertNode(Node temp, String root, String left, String right) {
        if(temp.value.equals(root)) {
            if(left.equals(".")) {
                temp.left = null;
            } else {
                temp.left = new Node(left, null, null);
            }
            if(right.equals(".")) {
                temp.right = null;
            } else {
                temp.right = new Node(right, null, null);
            }
        } else {
            if(temp.left != null) {
                insertNode(temp.left, root, left, right);
            }
            if(temp.right != null) {
                insertNode(temp.right, root, left, right);
            }
        }
    }

    static void preOrder(Node node) {
        if(node == null) {
            return;
        }
        System.out.print(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    static void inOrder(Node node) {
        if(node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.value);
        inOrder(node.right);
    }

    static void postOrder(Node node) {
        if(node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value);
    }
}