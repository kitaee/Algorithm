import java.util.*;

class Solution {
    
    static int answer = 1;
    static Map<String, Integer> map = new HashMap<>();
    
    public int solution(String[][] clothes) {
        for(String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 0)+1);
        }
        
        for(String cloth : map.keySet()) {
            answer *= (map.get(cloth)+1);
        }
        
        return answer-1;
    }
}