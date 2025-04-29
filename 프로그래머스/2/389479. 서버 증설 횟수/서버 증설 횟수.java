import java.util.*;

class Solution {
    
    static int answer = 0;
    static int server = 0;
    static Map<Integer, Integer> serverDown = new HashMap<>();
    
    public int solution(int[] players, int m, int k) {
        for(int i=0; i<players.length; i++) {
            if(serverDown.containsKey(i)) {
                server -= serverDown.get(i);
            }
            
            int needServer = (players[i]/m) - server;
            if(needServer > 0) {
                answer += needServer;
                server += needServer;
                serverDown.put(i+k, needServer);
            }
        }
        
        return answer;
    }
}