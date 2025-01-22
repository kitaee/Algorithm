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

    static int N,M;
    static String[] info;
    static Map<Integer, Integer> ladders = new HashMap<>();
    static Map<Integer, Integer> snakes = new HashMap<>();
    static int[] visited;
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        visited = new int[101];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            ladders.put(Integer.parseInt(info[0]), Integer.parseInt(info[1]));
        }
        for(int i=0; i<M; i++) {
            info = br.readLine().split(" ");
            snakes.put(Integer.parseInt(info[0]), Integer.parseInt(info[1]));
        }

        visited[1] = 1;
        queue.offer(new Node(1, 0));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.index == 100) {
                System.out.println(node.count);
                return;
            }
            for(int dice=1; dice<=6; dice++) {
                int next = node.index + dice;
                if(next<=100 && visited[next]==0) {
                    visited[next] = 1;
                    if(ladders.containsKey(next)) {
                        visited[ladders.get(next)] = 1;
                        queue.offer(new Node(ladders.get(next), node.count+1));
                    } else if(snakes.containsKey(next)) {
                        visited[snakes.get(next)] = 1;
                        queue.offer(new Node(snakes.get(next), node.count+1));
                    } else {
                        queue.offer(new Node(next, node.count+1));
                    }
                }
            }
        }
    }
}