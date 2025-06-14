import java.util.*;

class Solution {
    
    static int N,M;
    static int answer = 0;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static boolean[][] visited;
    static Map<Integer, Integer> map = new HashMap<>();
    static Map<Integer, Integer> tempMap;
    static Queue<int[]> queue;
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        visited = new boolean[N][M];
        
        for(int i=0; i<M; i++) {
            map.put(i, 0);
        }
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!visited[i][j] && land[i][j]==1) {
                    bfs(i,j,land);
                }
            }
        }
        
        for(int i=0; i<M; i++) {
            answer = Math.max(answer, map.get(i));
        }
        
        return answer;
    }
    
    private void bfs(int x, int y, int[][] land) {
        int size = 1;
        tempMap = new HashMap<>();
        queue = new LinkedList<>();
        tempMap.put(y, 1);
        visited[x][y] = true;
        queue.offer(new int[]{x,y});
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if(0<=nx && nx<N && 0<=ny && ny<M && !visited[nx][ny] && land[nx][ny]==1) {
                    visited[nx][ny] = true;
                    tempMap.put(ny, 1);
                    size+=1;
                    queue.offer(new int[]{nx,ny});
                }    
            }
            
        }
        
        for(Integer key : tempMap.keySet()) {
            map.put(key, map.getOrDefault(key,0)+size);
        }
    }
}