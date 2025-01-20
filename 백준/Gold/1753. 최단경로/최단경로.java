import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int destination;
        int cost;

        public Node(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }

    static int V,E,K;
    static int[] distance;
    static List<List<Node>> graph = new ArrayList<>();
    static String[] info;
    static Queue<Node> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        V = Integer.parseInt(info[0]);
        E = Integer.parseInt(info[1]);
        K = Integer.parseInt(br.readLine());

        distance = new int[V+1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        for(int i=0; i<V+1; i++) {
            List<Node> temp = new ArrayList<>();
            graph.add(temp);
        }

        for(int i=0; i<E; i++) {
            info = br.readLine().split(" ");
            int u = Integer.parseInt(info[0]);
            int v = Integer.parseInt(info[1]);
            int w = Integer.parseInt(info[2]);
            graph.get(u).add(new Node(v, w));
        }

        distance[K] = 0;
        queue.offer(new Node(K, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(distance[node.destination] < node.cost) {
                continue;
            }

            for(Node target : graph.get(node.destination)) {
                int newCost = distance[node.destination] + target.cost;
                if(newCost < distance[target.destination]) {
                    distance[target.destination] = newCost;
                    queue.offer(new Node(target.destination, newCost));
                }
            }
        }

        for(int i=1; i<V+1; i++) {
            if(distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }
}