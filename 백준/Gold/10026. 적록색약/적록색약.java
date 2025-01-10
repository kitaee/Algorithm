import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static String[][] graph;
    static int[][] visited;
    static int answer1 = 0;
    static int answer2 = 0;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new String[N][N];

        for(int i=0; i<N; i++) {
            String info = br.readLine();
            for(int j=0; j<N; j++) {
                graph[i][j] = String.valueOf(info.charAt(j));
            }
        }

        visited = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visited[i][j] == 0) {
                    answer1+=1;
                    bfs(i, j, false);
                }
            }
        }

        visited = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visited[i][j] == 0) {
                    answer2+=1;
                    if(graph[i][j].equals("B")) {
                        bfs(i, j, false);
                    } else {
                        bfs(i, j, true);
                    }
                }
            }
        }

        System.out.println(answer1 + " " + answer2);
    }

    static void bfs(int x, int y, boolean flag) {
        visited[x][y] = 1;
        queue = new LinkedList<>();
        queue.offer(new int[]{x,y});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if(!flag) {
                    if(0<=nx && nx<N && 0<=ny && ny<N && visited[nx][ny]==0 && graph[x][y].equals(graph[nx][ny])) {
                        visited[nx][ny] = 1;
                        queue.offer(new int[]{nx,ny});
                    }
                } else {
                    if(0<=nx && nx<N && 0<=ny && ny<N && visited[nx][ny]==0 && (graph[nx][ny].equals("R") || graph[nx][ny].equals("G"))) {
                        visited[nx][ny] = 1;
                        queue.offer(new int[]{nx,ny});
                    }
                }
            }
        }
    }
}