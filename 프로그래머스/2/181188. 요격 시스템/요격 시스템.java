import java.util.*;

class Solution {
    
    static class Node implements Comparable<Node> {
        int start;
        int end;
        
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Node node) {
            return this.end - node.end;
        }
    }
    
    static int answer = 0;
    static int targetIndex = -1;
    static Queue<Node> queue = new PriorityQueue<>();
    
    public int solution(int[][] targets) {
        for(int[] target : targets) {
            queue.offer(new Node(target[0], target[1]));
        }
        
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.start > targetIndex) {
                targetIndex = node.end - 1;
                answer+=1;
            }
        }
        
        return answer;
    }
}