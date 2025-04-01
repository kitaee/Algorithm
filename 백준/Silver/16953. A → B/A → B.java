import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int value; int count;
        public Node(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    static int A,B;
    static boolean[] visited;
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        A = Integer.parseInt(info[0]);
        B = Integer.parseInt(info[1]);
        visited = new boolean[B+1];
        visited[A] = true;
        queue.offer(new Node(A, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.value == B) {
                System.out.println(node.count+1);
                return;
            } else if(node.value > B) {
                return;
            } else {
                if(plusOneInBack(node.value) <= 1000000000) {
                    int convertValue1 = (int) plusOneInBack(node.value);
                    if(convertValue1<=B && !visited[convertValue1]) {
                        visited[convertValue1] = true;
                        queue.offer(new Node(convertValue1, node.count+1));
                    }
                }

                int convertValue2 = node.value * 2;
                if(convertValue2<=B && !visited[convertValue2]) {
                    visited[convertValue2] = true;
                    queue.offer(new Node(convertValue2, node.count+1));
                }
            }
        }

        System.out.println(-1);
    }

    static long plusOneInBack(int value) {
        return Long.parseLong(value + "1");
    }
}