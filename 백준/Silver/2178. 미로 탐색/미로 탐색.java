import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int x;
        int y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    static int N,M;
    static char[][] graph;
    static int[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        graph = new char[N][M];
        visited = new int[N][M];

        for(int i=0; i<N; i++) {
            String graphInfo = br.readLine();
            for(int j=0; j<M; j++) {
                graph[i][j] = graphInfo.charAt(j);
            }
        }

        visited[0][0] = 1;
        queue.offer(new Node(0, 0, 1));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.x == N-1 && node.y == M-1) {
                System.out.println(node.count);
                return;
            }

            for(int i=0; i<4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if(0<=nx && nx<N && 0<=ny && ny<M && visited[nx][ny]==0 && graph[nx][ny]=='1') {
                    visited[nx][ny]=1;
                    queue.offer(new Node(nx, ny, node.count+1));
                }
            }
        }
    }
}