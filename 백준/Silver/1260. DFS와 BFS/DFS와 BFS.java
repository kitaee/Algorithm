import java.io.*;
import java.util.*;

public class Main {

    static int N,M,V;
    static int[] visited;
    static int[][] graph;
    static String[] info;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        V = Integer.parseInt(info[2]);
        graph = new int[N+1][N+1];

        for(int i=0; i<M; i++) {
            info = br.readLine().split(" ");
            int v1 = Integer.parseInt(info[0]);
            int v2 = Integer.parseInt(info[1]);
            graph[v1][v2] = 1;
            graph[v2][v1] = 1;
        }

        visited = new int[N+1];
        System.out.print(V + " ");
        visited[V] = 1;
        dfs(0, V);
        System.out.println();
        visited = new int[N+1];
        bfs(V);
    }

    static void dfs(int depth, int start) {
        if(depth == N) {
            return;
        }
        for(int i=1; i<N+1; i++) {
            if(graph[start][i]==1 && visited[i]==0) {
                visited[i] = 1;
                System.out.print(i + " ");
                dfs(depth+1, i);
            }
        }
    }

    static void bfs(int start) {
        queue.offer(start);
        visited[start] = 1;

        while(!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");
            for(int i=1; i<N+1; i++) {
                if(graph[vertex][i]==1 && visited[i]==0) {
                    visited[i] = 1;
                    queue.offer(i);
                }
            }
        }
    }
}