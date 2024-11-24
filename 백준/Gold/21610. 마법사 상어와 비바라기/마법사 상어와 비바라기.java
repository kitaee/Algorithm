import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int answer = 0;
    static int N,M;
    static String[] info;
    static int[][] arr;
    static int[][] visited;
    static List<Node> cloudList = new ArrayList<>();
    static int[] dx = {0,-1,-1,-1,0,1,1,1};
    static int[] dy = {-1,-1,0,1,1,1,0,-1};
    static int[] diagonalDx = {-1,-1,1,1};
    static int[] diagonalDy = {-1,1,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        arr = new int[N][N];
        visited = new int[N][N];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(info[j]);
            }
        }

        cloudList.add(new Node(N-1,0));
        cloudList.add(new Node(N-1,1));
        cloudList.add(new Node(N-2,0));
        cloudList.add(new Node(N-2,1));

        for(int i=0; i<M; i++) {
            info = br.readLine().split(" ");
            int direction = Integer.parseInt(info[0])-1;
            int distance = Integer.parseInt(info[1]);
            for(Node node : cloudList) {
                int nx = node.x + dx[direction]*distance;
                int ny = node.y + dy[direction]*distance;

                while(isInRange(nx) != 0) {
                    if(isInRange(nx) == -1) {
                        nx += N;
                    } else {
                        nx -= N;
                    }
                }
                while(isInRange(ny) != 0) {
                    if(isInRange(ny) == -1) {
                        ny += N;
                    } else {
                        ny -= N;
                    }
                }

                node.x = nx;
                node.y = ny;
                arr[node.x][node.y] += 1;
                visited[node.x][node.y] = 1;
            }

            for(Node node : cloudList) {
                int count = 0;

                for(int j=0; j<4; j++) {
                    int nx = node.x + diagonalDx[j];
                    int ny = node.y + diagonalDy[j];

                    if(0<=nx && nx<N && 0<=ny && ny<N && arr[nx][ny]>0) {
                        count += 1;
                    }
                }

                arr[node.x][node.y] += count;
            }

            cloudList = new ArrayList<>();
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    if(arr[j][k]>=2 && visited[j][k]==0) {
                        cloudList.add(new Node(j, k));
                        arr[j][k] -= 2;
                    }
                }
            }
            visited = new int[N][N];
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                answer += arr[i][j];
            }
        }

        System.out.println(answer);
    }

    static int isInRange(int point) {
        if(point < 0) {
            return -1;
        } else if(point<N) {
            return 0;
        } else {
            return 1;
        }
    }
}