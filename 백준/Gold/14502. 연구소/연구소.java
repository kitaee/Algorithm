import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;
    static int N,M;
    static int[][] graph;
    static List<int[]> targetList = new ArrayList<>();
    static int[] arr;
    static int[] used;
    static int[] result = new int[3];
    static int[][] fakeGraph;
    static int[][] visited;
    static String[] info;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        graph = new int[N][M];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
                if(graph[i][j] == 0) {
                    targetList.add(new int[]{i,j});
                }
            }
        }

        arr = new int[targetList.size()];
        used = new int[targetList.size()];
        for(int i=0; i<arr.length; i++) {
            arr[i] = i;
        }

        combination(0, 0);
        System.out.println(answer);
    }

    static void combination(int depth, int start) {
        if(depth == 3) {
            simulation();
            return;
        }
        for(int i=start; i<arr.length; i++) {
            if(used[i] == 0) {
                used[i] = 1;
                result[depth] = arr[i];
                combination(depth+1, i+1);
                used[i] = 0;
            }
        }
    }

    static void simulation() {
        makeFakeGraph();
        visited = new int[N][M];
        queue = new LinkedList<>();

        for(int i=0; i<3; i++) {
            int[] target = targetList.get(result[i]);
            fakeGraph[target[0]][target[1]] = 1;
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(fakeGraph[i][j] == 2 && visited[i][j]==0) {
                    visited[i][j] = 1;
                    queue.offer(new int[]{i,j});
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if(0<=nx && nx<N && 0<=ny && ny<M && fakeGraph[nx][ny]==0 && visited[nx][ny]==0) {
                    visited[nx][ny] = 1;
                    fakeGraph[nx][ny] = 2;
                    queue.offer(new int[]{nx,ny});
                }
            }
        }

        updateAnswer();
    }

    static void makeFakeGraph() {
        fakeGraph = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                fakeGraph[i][j] = graph[i][j];
            }
        }
    }

    static void updateAnswer() {
        int count = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(fakeGraph[i][j] == 0) {
                    count+=1;
                }
            }
        }
        answer = Math.max(answer, count);
    }
}