import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int value;
        int count;

        public Node(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    static int N;
    static int[] visited;
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new int[N+1];
        visited[N] = 1;
        queue.offer(new Node(N, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.value == 1) {
                System.out.println(node.count);
                return;
            }
            if(node.value%3==0 && visited[node.value/3]==0) {
                visited[node.value/3] = 1;
                queue.offer(new Node(node.value/3, node.count+1));
            }
            if(node.value%2==0 && visited[node.value/2]==0) {
                visited[node.value/2] = 1;
                queue.offer(new Node(node.value/2, node.count+1));
            }
            if(node.value-1 >=0 && visited[node.value-1]==0) {
                visited[node.value-1] = 1;
                queue.offer(new Node(node.value-1, node.count+1));
            }
        }
    }
}