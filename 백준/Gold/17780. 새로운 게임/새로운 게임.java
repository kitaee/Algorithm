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

    static void simulation() {
        while(answer <= 1000) {
            move();
            answer+=1;
        }
        System.out.println(-1);
    }

    static void move() {
        for(Node node : nodeList) {
            ArrayList<Integer> indexList = stack[node.x][node.y];
            if(indexList.get(0) != node.index) {
                continue;
            }
            ArrayList<Integer> moveList = stack[node.x][node.y];
            stack[node.x][node.y] = new ArrayList<>();

            int nx = node.x + dx[node.direction];
            int ny = node.y + dy[node.direction];

            if(!isInRange(nx,ny) || map[nx][ny]==2) {
                node.direction = changeDirection(node.direction);
                nx = node.x + dx[node.direction];
                ny = node.y + dy[node.direction];
            }

            if(!isInRange(nx,ny) || map[nx][ny]==2) {
                stack[node.x][node.y].addAll(moveList);
            } else if(map[nx][ny]==1) {
                Collections.reverse(moveList);
                stack[nx][ny].addAll(moveList);
                node.x = nx;
                node.y = ny;
            } else {
                stack[nx][ny].addAll(moveList);
                node.x = nx;
                node.y = ny;
            }

            for(int i=0; i<moveList.size(); i++) {
                Node targetNode = nodeList[moveList.get(i) - 1];
                targetNode.x = node.x;
                targetNode.y = node.y;
            }

            if(stack[node.x][node.y].size() >= 4) {
                System.out.println(answer);
                System.exit(0);
            }
        }
    }

    static boolean isInRange(int nx, int ny) {
        if(0<=nx && nx<N && 0<=ny && ny<N) {
            return true;
        }
        return false;
    }

    static int changeDirection(int direction) {
        if(direction==1) return 2;
        else if(direction==2) return 1;
        else if(direction==3) return 4;
        else return 3;
    }
}