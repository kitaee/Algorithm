import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.cost - edge.cost;
        }
    }

    static int answer = 0;
    static int V,E;
    static int[] parent;
    static Queue<Edge> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        V = Integer.parseInt(info[0]);
        E = Integer.parseInt(info[1]);

        parent = new int[V+1];
        for(int i=1; i<V+1; i++) {
            parent[i] = i;
        }

        for(int i=0; i<E; i++) {
            info = br.readLine().split(" ");
            int from = Integer.parseInt(info[0]);
            int to = Integer.parseInt(info[1]);
            int cost = Integer.parseInt(info[2]);
            queue.offer(new Edge(from,to,cost));
        }

        while(!queue.isEmpty()) {
            Edge edge = queue.poll();
            if(findParent(edge.from) == findParent(edge.to)) {
                continue;
            } else {
                answer += edge.cost;
                unionParent(edge.from, edge.to);
            }
        }

        System.out.println(answer);
    }

    static int findParent(int node) {
        if(parent[node] == node) {
            return node;
        }
//        return findParent(parent[node]);
        return parent[node] = findParent(parent[node]);
    }

    static void unionParent(int node1, int node2) {
        node1 = findParent(node1);
        node2 = findParent(node2);

        if(node1 < node2) {
            parent[node2] = node1;
        } else {
            parent[node1] = node2;
        }
    }
}