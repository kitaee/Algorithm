import java.util.*;

class Solution {
    
    static int DEPTH;
    static int[] visited;
    static List<String> answer = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        DEPTH = tickets.length;
        visited = new int[tickets.length];
        dfs(0, "ICN", "ICN", tickets);
        Collections.sort(answer);
        return answer.get(0).split(" ");
    }
    
    static void dfs(int depth, String path, String start, String[][] tickets) {
        if(depth == DEPTH) {
            answer.add(path);
            return;
        }
        for(int i=0; i<tickets.length; i++) {
            if(visited[i] == 0 && tickets[i][0].equals(start)) {
                visited[i] = 1;
                dfs(depth+1, path+" "+tickets[i][1], tickets[i][1], tickets);
                visited[i] = 0;
            }
        }
    }
}