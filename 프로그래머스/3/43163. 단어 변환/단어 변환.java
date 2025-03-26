import java.util.*;

class Solution {
    
    static class Node {
        int count;
        String word;
        
        public Node(int count, String word) {
            this.count = count;
            this.word = word;
        }
    }
    
    static Queue<Node> queue = new LinkedList<>();
    static int[] visited;
    
    public int solution(String begin, String target, String[] words) {
        visited = new int[words.length];
        
        for(int i=0; i<words.length; i++) {
            if(compareWord(begin, words[i]) == 1) {
                visited[i] = 1;
                queue.offer(new Node(1, words[i]));
            }
        }
        
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.word.equals(target)) {
                return node.count;
            }
            
            for(int i=0; i<words.length; i++) {
                if(visited[i]==0 && compareWord(node.word, words[i])==1) {
                    visited[i] = 1;
                    queue.offer(new Node(node.count+1, words[i]));
                }
            }
        }
        
        return 0;
    }
    
    static int compareWord(String word, String targetWord) {
        int count = 0;
        for(int i=0; i<word.length(); i++) {
            if(word.charAt(i) != targetWord.charAt(i)) {
                count+=1;
            }
        }
        return count;
    }
}