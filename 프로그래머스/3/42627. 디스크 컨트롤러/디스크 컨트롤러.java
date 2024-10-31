import java.util.*;

class Solution {
    
    static class Ready implements Comparable<Ready> {
        int request;
        int cost;
        
        public Ready(int request, int cost) {
            this.request = request;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Ready ready) {
            return this.request - ready.request;
        }
    }
    
    static class Node implements Comparable<Node> {
        int request;
        int cost;
        
        public Node(int request, int cost) {
            this.request = request;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }
    
    static Queue<Ready> readyQueue = new PriorityQueue<>();
    static Queue<Node> setQueue = new PriorityQueue<>();
    static int time = 0;
    static int answer = 0;
    
    public int solution(int[][] jobs) {
        for(int[] job : jobs) {
            readyQueue.offer(new Ready(job[0], job[1]));
        }
        
        Ready tempReady = readyQueue.poll();
        time = tempReady.request;
        setQueue.offer(new Node(tempReady.request, tempReady.cost));
        
        while(true) {
            while(!readyQueue.isEmpty()) {
                Ready ready = readyQueue.peek();
                if(ready.request <= time) {
                    ready = readyQueue.poll();
                    setQueue.offer(new Node(ready.request, ready.cost));
                } else {
                    break;
                }
            }
            
            
            if(readyQueue.isEmpty() && setQueue.isEmpty()) {
                break;
            } else if(!readyQueue.isEmpty() && setQueue.isEmpty()) {
                Ready ready = readyQueue.poll();
                time = ready.request;
                setQueue.offer(new Node(ready.request, ready.cost));
            } else {
                Node node = setQueue.poll();
                answer += (time-node.request + node.cost);
                time += node.cost;    
            }
        }
        
        return answer/jobs.length;
    }
}