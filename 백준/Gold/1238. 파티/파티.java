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

    static int N,M,X;
    static String[] info;
    static int[] totalDistance;
    static int[] distance;
    static List<List<Node>> graph = new ArrayList<>();
    static Queue<Node> queue;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        X = Integer.parseInt(info[2]);

        totalDistance = new int[N+1];
        for(int i=0; i<N+1; i++) {
            List<Node> temp = new ArrayList<>();
            graph.add(temp);
        }

        for(int i=0; i<M; i++) {
            info = br.readLine().split(" ");
            int start = Integer.parseInt(info[0]);
            int end = Integer.parseInt(info[1]);
            int cost = Integer.parseInt(info[2]);
            graph.get(start).add(new Node(end, cost));
        }

        for(int target=1; target<N+1; target++) {
            distance = new int[N+1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[target] = 0;
            queue = new PriorityQueue<>();
            queue.offer(new Node(target, 0));

            while(!queue.isEmpty()) {
                Node node = queue.poll();
                if(distance[node.destination] < node.cost) {
                    continue;
                }
                for(int i=0; i<graph.get(node.destination).size(); i++) {
                    Node next = graph.get(node.destination).get(i);
                    if(distance[next.destination] >= distance[node.destination] + next.cost) {
                        distance[next.destination] = distance[node.destination] + next.cost;
                        queue.offer(new Node(next.destination, distance[next.destination]));
                    }
                }
            }

            if(target == X) {
                for(int i=1; i<N+1; i++) {
                    totalDistance[i] += distance[i];
                }
            } else {
                totalDistance[target] += distance[X];
            }
        }

        for(int i=1; i<N+1; i++) {
            answer = Math.max(answer, totalDistance[i]);
        }

        System.out.println(answer);
    }
}