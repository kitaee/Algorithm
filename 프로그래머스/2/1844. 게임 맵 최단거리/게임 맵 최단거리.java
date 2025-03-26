import java.util.*;

class Solution {
    
    static class Node {
        int x;
        int y;
        int count;
        
        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    static int N,M;
    static int[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Node> queue = new LinkedList<>();
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        visited = new int[N][M];
        
        queue.offer(new Node(0, 0, 1));
        visited[0][0] = 1;
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            if(current.x == N-1 && current.y == M-1) {
                return current.count;
            }
            
            for(int i=0; i<4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if(0<=nx && nx<N && 0<=ny && ny<M && visited[nx][ny]==0 && maps[nx][ny]==1) {
                    queue.offer(new Node(nx, ny, current.count+1));
                    visited[nx][ny] = 1;
                }
            }
        }
        
        return -1;
    }
}