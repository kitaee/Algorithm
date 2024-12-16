import java.util.*;

class Solution {
    
    static int[] answer;
    static Map<String, Integer> map = new HashMap<>();
    
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        answer = new int[photo.length];
        for(int i=0; i<name.length; i++) {
            map.put(name[i], yearning[i]);
        }
        
        for(int i=0; i<photo.length; i++) {
            int count = 0;
            for(int j=0; j<photo[i].length; j++) {
                if(map.containsKey(photo[i][j])) {
                    count += map.get(photo[i][j]);
                }
            }
            answer[i] = count;
        }
        
        return answer;
    }
}