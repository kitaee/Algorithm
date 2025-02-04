import java.io.*;
import java.util.*;

public class Main {

    static int T;
    static int N,M,K;
    static int X,Y;
    static StringBuilder answer = new StringBuilder();
    static String[] info;
    static int[][] graph;
    static int[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static Queue<int[]> queue;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int test=0; test<T; test++) {
            info = br.readLine().split(" ");
            count = 0;
            N = Integer.parseInt(info[0]);
            M = Integer.parseInt(info[1]);
            K = Integer.parseInt(info[2]);
            graph = new int[N][M];
            visited = new int[N][M];
            for(int i=0; i<K; i++) {
                info = br.readLine().split(" ");
                X = Integer.parseInt(info[0]);
                Y = Integer.parseInt(info[1]);
                graph[X][Y] = 1;
            }
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(graph[i][j]==1 && visited[i][j]==0) {
                        visited[i][j] = 1;
                        bfs(i,j);
                        count+=1;
                    }
                }
            }
            answer.append(count + "\n");
        }
        System.out.println(answer);
    }

    static void bfs(int x, int y) {
        queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if(0<=nx && nx<N && 0<=ny && ny<M && visited[nx][ny]==0 && graph[nx][ny]==1) {
                    visited[nx][ny] = 1;
                    queue.offer(new int[]{nx,ny});
                }
            }
        }
    }
}