import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int x,y,direction,index;

        public Node(int x, int y, int direction, int index) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.index = index;
        }
    }

    static int answer = 1;
    static int N,K;
    static String[] info;
    static int[][] map;
    static ArrayList<Integer>[][] stack;
    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};
    static Node[] nodeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        K = Integer.parseInt(info[1]);
        map = new int[N][N];
        stack = new ArrayList[N][N];
        nodeList = new Node[K];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(info[j]);
                stack[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<K; i++) {
            info = br.readLine().split(" ");
            int x = Integer.parseInt(info[0])-1;
            int y = Integer.parseInt(info[1])-1;
            int direction = Integer.parseInt(info[2]);
            nodeList[i] = new Node(x,y,direction,i+1);
            stack[x][y].add(i+1);
        }

        simulation();
    }

    static void move() {
        for(Node node : nodeList) {
            boolean isOverNode = false;
            ArrayList<Integer> indexList = stack[node.x][node.y];
            ArrayList<Integer> remainList = new ArrayList<>();
            ArrayList<Integer> moveList = new ArrayList<>();
            for(int i=0; i<indexList.size(); i++) {
                if(indexList.get(i) == node.index) {
                    isOverNode = true;
                }
                if(isOverNode) {
                    moveList.add(indexList.get(i));
                } else {
                    remainList.add(indexList.get(i));
                }
            }
            stack[node.x][node.y] = remainList;

            int nx = node.x + dx[node.direction];
            int ny = node.y + dy[node.direction];

            if(!isInRange(nx,ny) || map[nx][ny]==2) {
                node.direction = changeDirection(node.direction);
                nx = node.x + dx[node.direction];
                ny = node.y + dy[node.direction];
            }

            if(!isInRange(nx,ny) || map[nx][ny]==2) {   // 가만히 있기
                stack[node.x][node.y].addAll(moveList);
            } else if(map[nx][ny]==1) { // 말 순서 바꾸기
                node.x = nx;
                node.y = ny;
                Collections.reverse(moveList);
                stack[node.x][node.y].addAll(moveList);
            } else {    // 쌓기
                node.x = nx;
                node.y = ny;
                stack[node.x][node.y].addAll(moveList);
            }

            for(int i=0; i<moveList.size(); i++) {
                nodeList[moveList.get(i)-1].x = node.x;
                nodeList[moveList.get(i)-1].y = node.y;
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(stack[i][j].size() >= 4) {
                        System.out.println(answer);
                        System.exit(0);
                    }
                }
            }
        }
    }

    static void simulation() {
        while(answer <= 1000) {
            move();
            answer+=1;
        }
        System.out.println(-1);
    }

    static int changeDirection(int direction) {
        if(direction==1) return 2;
        else if(direction==2) return 1;
        else if(direction==3) return 4;
        else if(direction==4) return 3;
        return -1;
    }

    static boolean isInRange(int nx, int ny) {
        if(0<=nx && nx<N && 0<=ny && ny<N) {
            return true;
        }
        return false;
    }
}