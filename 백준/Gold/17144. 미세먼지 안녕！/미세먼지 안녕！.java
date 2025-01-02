import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;
    static int R,C,T;
    static int[][] graph;
    static int[][] tempGraph;
    static String[] info;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static List<int[]> cleanerIndexList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        R = Integer.parseInt(info[0]);
        C = Integer.parseInt(info[1]);
        T= Integer.parseInt(info[2]);
        graph = new int[R][C];

        for(int i=0; i<R; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<C; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
                if(graph[i][j] == -1) {
                    cleanerIndexList.add(new int[]{i,j});
                }
            }
        }

        for(int time=0; time<T; time++) {
            // 미세먼지 확산
            tempGraph = new int[R][C];
            for(int i=0; i<R; i++) {
                for(int j=0; j<C; j++) {
                    if(graph[i][j] > 0) {
                        int count = 0;
                        for(int direction=0; direction<4; direction++) {
                            int nx = i + dx[direction];
                            int ny = j + dy[direction];

                            if(0<=nx && nx<R && 0<=ny && ny<C && graph[nx][ny]!=-1) {
                                count+=1;
                                tempGraph[nx][ny]+=(graph[i][j]/5);
                            }
                        }
                        graph[i][j]-=(graph[i][j]/5*count);
                    }
                }
            }

            for(int i=0; i<R; i++) {
                for(int j=0; j<C; j++) {
                    graph[i][j]+=(tempGraph[i][j]);
                }
            }

            // 반시계 방향 공기청정기 작동
            int[] reverseIndex = cleanerIndexList.get(0);
            reverseRotate(reverseIndex[0]-1, reverseIndex[1]);

            // 시계 방향 공기청정기 작동
            int[] index = cleanerIndexList.get(1);
            rotate(index[0]+1, index[1]);
        }

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(graph[i][j] > 0) {
                    answer+=graph[i][j];
                }
            }
        }

        System.out.println(answer);
    }

    static void reverseRotate(int x, int y) {
        while(true) {
            graph[x][y] = graph[x-1][y];
            x = x-1;
            if(x == 0) {
                break;
            }
        }

        while(true) {
            graph[x][y] = graph[x][y+1];
            y = y+1;
            if(y == C-1) {
                break;
            }
        }

        while(true) {
            graph[x][y] = graph[x+1][y];
            x = x+1;
            if(x == cleanerIndexList.get(0)[0]) {
                break;
            }
        }

        while(true) {
            graph[x][y] = graph[x][y-1];
            y = y-1;
            if(y == 1) {
                graph[x][1] = 0;
                break;
            }
        }
    }

    static void rotate(int x, int y) {
        while(true) {
            graph[x][y] = graph[x+1][y];
            x = x+1;
            if(x == R-1) {
                break;
            }
        }

        while(true) {
            graph[x][y] = graph[x][y+1];
            y = y+1;
            if(y == C-1) {
                break;
            }
        }

        while(true) {
            graph[x][y] = graph[x-1][y];
            x = x-1;
            if(x == cleanerIndexList.get(1)[0]) {
                break;
            }
        }

        while(true) {
            graph[x][y] = graph[x][y-1];
            y = y-1;
            if(y == 1) {
                graph[x][1] = 0;
                break;
            }
        }
    }
}