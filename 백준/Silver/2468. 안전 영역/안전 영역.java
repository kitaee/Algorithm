import java.io.*;

public class Main {

    static int N;
    static int[][] graph;
    static int[][] visited;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int safeArea;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
                min = Math.min(min, graph[i][j]);
                max = Math.max(max, graph[i][j]);
            }
        }

        for(int depth=min-1; depth<=max; depth++) {
            safeArea = 0;
            visited = new int[N][N];

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(graph[i][j]>depth && visited[i][j]==0) {
                        safeArea+=1;
                        visited[i][j] = 1;
                        dfs(i, j, depth);
                    }
                }
            }

            answer = Math.max(answer, safeArea);
        }

        System.out.println(answer);
    }

    static void dfs(int x, int y, int depth) {
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(0<=nx && nx<N && 0<=ny && ny<N && visited[nx][ny]==0 && graph[nx][ny]>depth) {
                visited[nx][ny] = 1;
                dfs(nx, ny, depth);
            }
        }
    }
}