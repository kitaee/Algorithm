import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int index;
        int count;

        public Node(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }

    static int N,K;
    static int[] visited = new int[100001];
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        K = Integer.parseInt(info[1]);

        visited[N] = 1;
        queue.offer(new Node(N, 0));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.index == K) {
                System.out.println(node.count);
                return;
            }
            if(0<=node.index-1 && visited[node.index-1]==0) {
                visited[node.index-1] = 1;
                queue.offer(new Node(node.index-1, node.count+1));
            }
            if(node.index+1<=100000 && visited[node.index+1]==0) {
                visited[node.index+1] = 1;
                queue.offer(new Node(node.index+1, node.count+1));
            }
            if(node.index*2<=100000 && visited[node.index*2]==0) {
                visited[node.index*2] = 1;
                queue.offer(new Node(node.index*2, node.count+1));
            }
        }
    }
}