import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int emptyCount;
        int preferenceCount;

        public Node(int x, int y, int emptyCount, int preferenceCount) {
            this.x = x;
            this.y = y;
            this.emptyCount = emptyCount;
            this.preferenceCount = preferenceCount;
        }

        @Override
        public int compareTo(Node node) {
            if(node.preferenceCount == this.preferenceCount) {
                if(node.emptyCount == this.emptyCount) {
                    if(this.x == node.x) {
                        return this.y - node.y;
                    }
                    return this.x - node.x;
                }
                return node.emptyCount - this.emptyCount;
            }
            return node.preferenceCount - this.preferenceCount;
        }
    }

    static int answer = 0;
    static int N;
    static int[][] seat;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static Map<Integer, List<Integer>> preferences = new HashMap<>();
    static Queue<Node> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seat = new int[N][N];
        for(int i=0; i<N*N; i++) {
            String[] info = br.readLine().split(" ");
            int student = Integer.parseInt(info[0]);
            int target1 = Integer.parseInt(info[1]);
            int target2 = Integer.parseInt(info[2]);
            int target3 = Integer.parseInt(info[3]);
            int target4 = Integer.parseInt(info[4]);
            List<Integer> preference = new ArrayList<>();
            preference.add(target1);
            preference.add(target2);
            preference.add(target3);
            preference.add(target4);
            preferences.put(student, preference);
            queue = new PriorityQueue<>();
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    if(seat[j][k] != 0) {
                        continue;
                    }
                    int emptyCount = 0;
                    int preferenceCount = 0;
                    for(int direction=0; direction<4; direction++) {
                        int nx = j + dx[direction];
                        int ny = k + dy[direction];
                        if(isInRange(nx, ny)) {
                            if(seat[nx][ny] == 0) {
                                emptyCount+=1;
                            } else if(preferences.get(student).contains(seat[nx][ny])) {
                                preferenceCount+=1;
                            }
                        }
                    }
                    queue.offer(new Node(j, k, emptyCount, preferenceCount));
                }
            }
            if(!queue.isEmpty()) {
                Node node = queue.poll();
                seat[node.x][node.y] = student;
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                int preferenceCount = 0;
                for(int direction=0; direction<4; direction++) {
                    int nx = i + dx[direction];
                    int ny = j + dy[direction];
                    if(isInRange(nx, ny) && preferences.get(seat[i][j]).contains(seat[nx][ny])) {
                        preferenceCount+=1;
                    }
                }
                if(preferenceCount == 1) {
                    answer+=1;
                } else if(preferenceCount == 2) {
                    answer+=10;
                } else if(preferenceCount == 3) {
                    answer+=100;
                } else if(preferenceCount == 4) {
                    answer+=1000;
                }
            }
        }

        System.out.println(answer);
    }

    static boolean isInRange(int x, int y) {
        if(0<=x && x<N && 0<=y && y<N) {
            return true;
        }
        return false;
    }
}