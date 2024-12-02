class Solution {
    
    static int answer = 0;
    static int[][] graph;
    
    public int solution(int n, int[][] results) {
        graph = new int[n+1][n+1];
        for(int[] result : results) {
            graph[result[0]][result[1]] = 1;
            graph[result[1]][result[0]] = -1;
        }
        
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<n+1; j++) {
                for(int k=1; k<n+1; k++) {
                    if(graph[i][j]==1 && graph[j][k]==1) {
                        graph[i][k] = 1;
                        graph[k][i] = -1;
                    }
                    if(graph[i][j]==-1 && graph[j][k]==-1) {
                        graph[i][k] = -1;
                        graph[k][i] = 1;
                    }
                }
            }
        }
        
        for(int i=1; i<n+1; i++) {
            int count = 0;
            for(int j=1; j<n+1; j++) {
                if(graph[i][j] != 0) {
                    count+=1;
                }
            }
            if(count >= n-1) {
                answer+=1;
            }
        }
        
        return answer;
    }
}