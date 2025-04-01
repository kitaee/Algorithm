import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int x; int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int answer = 0;
    static int tempSize = 0;
    static int N,M,K;
    static int[][] graph;
    static int[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static String[] info;
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        K = Integer.parseInt(info[2]);

        graph = new int[N][M];
        visited = new int[N][M];

        for(int i=0; i<K; i++) {
            info = br.readLine().split(" ");
            int x = Integer.parseInt(info[0])-1;
            int y = Integer.parseInt(info[1])-1;
            graph[x][y] = 1;
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(graph[i][j]==1 && visited[i][j]==0) {
                    visited[i][j] = 1;
                    tempSize+=1;
                    queue.offer(new Node(i, j));
                    bfs();
                    answer = Math.max(answer, tempSize);
                    tempSize = 0;
                }
            }
        }

        System.out.println(answer);
    }

    static void bfs() {
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if(0<=nx && nx<N && 0<=ny && ny<M && visited[nx][ny]==0 && graph[nx][ny]==1) {
                    visited[nx][ny] = 1;
                    tempSize+=1;
                    queue.offer(new Node(nx, ny));
                }
            }
        }
    }
}