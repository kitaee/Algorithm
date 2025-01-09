import java.io.*;
import java.util.*;

public class Main {

    static int M,N;
    static int[][] tomato;
    static int[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int answer = 0;
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        M = Integer.parseInt(info[0]);
        N = Integer.parseInt(info[1]);

        tomato = new int[N][M];
        visited = new int[N][M];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<info.length; j++) {
                tomato[i][j] = Integer.parseInt(info[j]);
                if(tomato[i][j] == 1) {
                    queue.offer(new int[]{i,j});
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] targetTomato = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = targetTomato[0] + dx[i];
                int ny = targetTomato[1] + dy[i];
                if(0<=nx && nx<N && 0<=ny && ny<M && visited[nx][ny]==0 && tomato[nx][ny]==0) {
                    tomato[nx][ny] = tomato[targetTomato[0]][targetTomato[1]]+1;
                    visited[nx][ny] = 1;
                    queue.offer(new int[]{nx,ny});
                }
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(tomato[i][j] == 0) {
                    System.out.println(-1);
                    return;
                } else {
                    answer = Math.max(answer, tomato[i][j]);
                }
            }
        }

        System.out.println(answer-1);
    }
}