import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int num;
        int count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    static int N;
    static String[] info;
    static int[] arr;
    static int[] visited;
    static int[][] graph;
    static int RELATION = Integer.MAX_VALUE;
    static int TEMP_RELATION;
    static Queue<Node> queue;
    static int answerCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        graph = new int[N+1][N+1];

        while(true) {
            info = br.readLine().split(" ");
            if(info[0].equals("-1") && info[1].equals("-1")) {
                break;
            }
            int v1 = Integer.parseInt(info[0]);
            int v2 = Integer.parseInt(info[1]);
            graph[v1][v2] = 1;
            graph[v2][v1] = 1;
        }

        for(int i=1; i<N+1; i++) {
            visited = new int[N+1];
            visited[i] = 1;
            TEMP_RELATION = 0;
            bfs(i);
            arr[i] = TEMP_RELATION;
            RELATION = Math.min(RELATION, TEMP_RELATION);
        }

        for(int i=1; i<N+1; i++) {
            if(arr[i] == RELATION) {
                answerCount+=1;
            }
        }

        System.out.println(RELATION + " " + answerCount);

        for(int i=1; i<N+1; i++) {
            if(arr[i] == RELATION) {
                System.out.print(i + " ");
            }
        }
    }

    static void bfs(int current) {
        queue = new LinkedList<>();
        queue.offer(new Node(current, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            TEMP_RELATION = Math.max(TEMP_RELATION, node.count);
            for(int i=1; i<N+1; i++) {
                if(graph[node.num][i]==1 && visited[i]==0) {
                    visited[i] = 1;
                    queue.offer(new Node(i, node.count+1));
                }
            }
        }
    }
}