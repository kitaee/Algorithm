import java.util.*;

class Solution {
    
    static class MaxHeap implements Comparable<MaxHeap> {
        int value;
        
        public MaxHeap(int value) {
            this.value = value;
        }
        
        @Override
        public int compareTo(MaxHeap maxHeap) {
            return maxHeap.value - this.value;
        }
    }
    
    static class MinHeap implements Comparable<MinHeap> {
        int value;
        
        public MinHeap(int value) {
            this.value = value;
        }
        
        @Override
        public int compareTo(MinHeap minHeap) {
            return this.value - minHeap.value;
        }
    }
    
    static Queue<MaxHeap> maxQueue = new PriorityQueue<>();
    static Queue<MinHeap> minQueue = new PriorityQueue<>();
    static Map<Integer, Integer> map = new HashMap<>();
    static int[] answer = new int[2];
    
    public int[] solution(String[] operations) {
        for(String operation : operations) {
            String[] command = operation.split(" ");
            if(command[0].equals("I")) {
                int value = Integer.parseInt(command[1]);
                maxQueue.offer(new MaxHeap(value));
                minQueue.offer(new MinHeap(value));
                map.put(value, map.getOrDefault(value, 0) +1);
            } else {
                if(minQueue.isEmpty() || maxQueue.isEmpty()) {
                    continue;
                }
                if(command[1].equals("1")) {
                    while(!maxQueue.isEmpty()) {
                        int value = maxQueue.peek().value;
                        if(map.get(value) > 0) {
                            map.put(value, map.get(value)-1);
                            break;
                        } else {
                            maxQueue.poll();
                        }
                    }
                } else {
                    while(!minQueue.isEmpty()) {
                        int value = minQueue.peek().value;
                        if(map.get(value) > 0) {
                            map.put(value, map.get(value)-1);
                            break;
                        } else {
                            minQueue.poll();
                        }
                    }
                }
            }
        }
        while(!maxQueue.isEmpty()) {
            int value = maxQueue.peek().value;
            if(map.get(value) > 0) {
                answer[0] = value;
                break;
            } else {
                maxQueue.poll();
            }
        }
        while(!minQueue.isEmpty()) {
            int value = minQueue.peek().value;
            if(map.get(value) > 0) {
                answer[1] = value;
                break;
            } else {
                minQueue.poll();
            }
        }
        return answer;
    }
}