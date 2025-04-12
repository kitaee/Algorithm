class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                answer+=1;
                dfs(i, visited, computers);
            }
        }
        
        return answer;
    }
    
    static void dfs(int target, boolean[] visited, int[][] computers) {
        visited[target] = true;
        for(int i=0; i<computers.length; i++) {
            if(computers[target][i]==1 && !visited[i]) {
                dfs(i, visited, computers);
            }
        }
    }
}