import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int num;
        int count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    static int N,M;
    static int target1,target2;
    static String[] info;
    static int[][] arr;
    static int[] visited;
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        visited = new int[N+1];
        info = br.readLine().split(" ");
        target1 = Integer.parseInt(info[0]);
        target2 = Integer.parseInt(info[1]);
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            info = br.readLine().split(" ");
            int x = Integer.parseInt(info[0]);
            int y = Integer.parseInt(info[1]);
            arr[x][y] = 1;
            arr[y][x] = 1;
        }

        bfs();
        System.out.println(-1);
    }

    static void bfs() {
        visited[target1] = 1;
        queue.offer(new Node(target1, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.num == target2) {
                System.out.println(node.count);
                System.exit(0);
            }

            for(int i=1; i<N+1; i++) {
                if(visited[i]==0 && arr[node.num][i]==1) {
                    visited[i] = 1;
                    queue.offer(new Node(i, node.count+1));
                }
            }
        }
    }
}