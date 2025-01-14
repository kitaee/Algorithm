import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int x;
        int y;
        int z;
        int day;

        public Node(int x, int y, int z, int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }

    static int N,M,H;
    static int[][][] tomato;
    static int[][][] visited;
    static int answer = 0;
    static int targetCount = 0;
    static String[] info;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[] dz = {-1,1};
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        M = Integer.parseInt(info[0]);
        N = Integer.parseInt(info[1]);
        H = Integer.parseInt(info[2]);

        tomato = new int[H][N][M];
        visited = new int[H][N][M];

        for(int i=0; i<H; i++) {
            for(int j=0; j<N; j++) {
                info = br.readLine().split(" ");
                for(int k=0; k<M; k++) {
                    tomato[i][j][k] = Integer.parseInt(info[k]);
                    if(tomato[i][j][k] == 1) {
                        visited[i][j][k]=1;
                        queue.offer(new Node(j, k, i, 1));
                    } else if(tomato[i][j][k] == 0) {
                        targetCount+=1;
                    }
                }
            }
        }

        if(targetCount==0) {
            System.out.println(0);
            return;
        }

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            for(int j=0; j<4; j++) {
                int nx = node.x + dx[j];
                int ny = node.y + dy[j];
                if(0<=nx && nx<N && 0<=ny && ny<M && visited[node.z][nx][ny]==0 && tomato[node.z][nx][ny]==0) {
                    visited[node.z][nx][ny] = 1;
                    tomato[node.z][nx][ny] = node.day+1;
                    queue.offer(new Node(nx,ny,node.z,node.day+1));
                }
            }
            for(int i=0; i<2; i++) {
                int nz = node.z + dz[i];
                if(0<=nz && nz<H && visited[nz][node.x][node.y]==0 && tomato[nz][node.x][node.y]==0) {
                    visited[nz][node.x][node.y] = 1;
                    tomato[nz][node.x][node.y] = node.day+1;
                    queue.offer(new Node(node.x,node.y,nz,node.day+1));
                }
            }
        }

        for(int i=0; i<H; i++) {
            for(int j=0; j<N; j++) {
                for(int k=0; k<M; k++) {
                    if(tomato[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    } else {
                        answer = Math.max(answer, tomato[i][j][k]);
                    }
                }
            }
        }

        System.out.println(answer-1);
    }
}