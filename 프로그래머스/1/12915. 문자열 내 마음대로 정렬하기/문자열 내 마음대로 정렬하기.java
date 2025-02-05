import java.util.*;

class Solution {
    
    static class Node implements Comparable<Node> {
        String value;
        int n;
        
        public Node(String value, int n) {
            this.value = value;
            this.n = n;
        }
        
        @Override
        public int compareTo(Node node) {
            if(this.value.charAt(n) == node.value.charAt(n)) {
                return this.value.compareTo(node.value);
            }
            return Character.compare(this.value.charAt(n), node.value.charAt(n));
        }
    }
    
    static Queue<Node> queue = new PriorityQueue<>();
    static String[] answer;
    static int index = 0;
    
    public String[] solution(String[] strings, int n) {
        
        answer = new String[strings.length];
        
        for(int i=0; i<strings.length; i++) {
            queue.offer(new Node(strings[i], n));
        }
        
        while(!queue.isEmpty()) {
            answer[index] = queue.poll().value;
            index+=1;
        }
        
        return answer;
    }
}