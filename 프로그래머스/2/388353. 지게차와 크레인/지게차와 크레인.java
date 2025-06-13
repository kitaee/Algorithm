import java.util.*;

class Solution {
    
    static int N,M;
    static int answer = 0;
    static char[][] graph;
    static List<int[]> removeList;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    
    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        graph = new char[N][M];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                graph[i][j] = storage[i].charAt(j);
            }
        }
        
        for(String request : requests) {
            char target = request.charAt(0);
            if(request.length() == 1) {
               // 테두리 순회하며 dfs 시작
                boolean[][] visited = new boolean[N][M];
                removeList = new ArrayList<>();
                
                for(int i=0; i<M; i++) {
                    dfs(0, i, visited, target);
                    dfs(N-1, i, visited, target);
                }
                
                for(int i=1; i<N-1; i++) {
                    dfs(i, 0, visited, target);
                    dfs(i, M-1, visited, target);
                }
                
                for(int i=0; i<removeList.size(); i++) {
                    int[] removeTarget = removeList.get(i);
                    graph[removeTarget[0]][removeTarget[1]] = 'a';
                }
            } else {
                for(int i=0; i<N; i++) {
                    for(int j=0; j<M; j++) {
                        if(graph[i][j] == target) {
                            graph[i][j] = 'a';
                        }
                    }
                }
            }
        }
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(graph[i][j] != 'a') {
                    answer+=1;
                }
            }
        }
        
        return answer;
    }
    
    private void dfs(int x, int y, boolean[][] visited, char target) {
        if(graph[x][y] == target) {
            removeList.add(new int[]{x,y});
            return;
        }
        
        if(graph[x][y] == 'a') {
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0<=nx && nx<N && 0<=ny && ny<M && !visited[nx][ny]) {
                    if(graph[nx][ny] == 'a' || graph[nx][ny] == target) {
                        visited[nx][ny] = true;
                        dfs(nx, ny, visited, target);
                    }
                }
            }
        }
    }
}