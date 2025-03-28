import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }

    static int N,M;
    static List<List<Node>> graph = new ArrayList<>();
    static Queue<Node> queue = new PriorityQueue<>();
    static int[] distance;
    static String[] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);

        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        for(int i=0; i<N+1; i++) {
            List<Node> temp = new ArrayList<>();
            graph.add(temp);
        }

        for(int i=0; i<M; i++) {
            info = br.readLine().split(" ");
            int from =Integer.parseInt(info[0]);
            int to =Integer.parseInt(info[1]);
            int cost =Integer.parseInt(info[2]);
            graph.get(from).add(new Node(to, cost));
            graph.get(to).add(new Node(from, cost));
        }

        queue.offer(new Node(1, 0));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(distance[node.to] < node.cost) {
                continue;
            }

            for(Node next : graph.get(node.to)) {
                int newCost = next.cost + node.cost;
                if(distance[next.to] > newCost) {
                    distance[next.to] = newCost;
                    queue.offer(new Node(next.to, newCost));
                }
            }
        }

        System.out.println(distance[N]);
    }
}