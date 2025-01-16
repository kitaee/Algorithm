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

    static int N,M;
    static int A,B;
    static int[] distance;
    static String[] info;
    static int[] visited;
    static List<List<Node>> graph = new ArrayList<>();
    static Queue<Node> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        distance = new int[N+1];
        visited = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        for(int i=0; i<N+1; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            info = br.readLine().split(" ");
            int v1 = Integer.parseInt(info[0]);
            int v2 = Integer.parseInt(info[1]);
            int cost = Integer.parseInt(info[2]);
            graph.get(v1).add(new Node(v2, cost));
        }

        info = br.readLine().split(" ");
        A = Integer.parseInt(info[0]);
        B = Integer.parseInt(info[1]);

        distance[A] = 0;
        queue.offer(new Node(A, 0));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(visited[node.destination] == 0) {
                visited[node.destination] = 1;
                for(int i=0; i<graph.get(node.destination).size(); i++) {
                    int v2 = graph.get(node.destination).get(i).destination;
                    int cost = graph.get(node.destination).get(i).cost;
                    if(visited[v2]==0 && distance[v2] >= distance[node.destination]+cost) {
                        distance[v2] = distance[node.destination]+cost;
                        queue.offer(new Node(v2, distance[v2]));
                    }
                }
            }
        }

        System.out.println(distance[B]);
    }
}