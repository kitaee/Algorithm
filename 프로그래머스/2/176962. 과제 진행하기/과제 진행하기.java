import java.util.*;

class Solution {
    
    static class Work implements Comparable<Work> {
        String name;
        int startTime;
        int remainCost;
        
        public Work(String name, int startTime, int remainCost) {
            this.name = name;
            this.startTime = startTime;
            this.remainCost = remainCost;
        }
        
        @Override
        public int compareTo(Work work) {
            return this.startTime - work.startTime;
        }
    }
    
    static String[] answer;
    static int now = 0;
    static int index = 0;
    static Queue<Work> readyQueue = new PriorityQueue<>();
    static Stack<Work> stopStack = new Stack<>();
    
    public String[] solution(String[][] plans) {
        answer = new String[plans.length];
        for(String[] plan : plans) {
            readyQueue.offer(new Work(plan[0],convertTime(plan[1]),Integer.parseInt(plan[2])));
        }
        
        Work current = readyQueue.poll();
        now = current.startTime;
        
        while(!readyQueue.isEmpty()) {
            if(current.remainCost + now > readyQueue.peek().startTime) {  // 미완료
                int remain = current.remainCost+now - readyQueue.peek().startTime;
                stopStack.add(new Work(current.name, current.startTime, remain));
            } else {
                now += current.remainCost;
                answer[index] = current.name;
                index+=1;
                
                while(!stopStack.isEmpty()) {
                    current = stopStack.pop();
                    if(now+current.remainCost <= readyQueue.peek().startTime) {
                        answer[index] = current.name;
                        index+=1;
                        now += current.remainCost;
                    } else {
                        int remain = current.remainCost+now - readyQueue.peek().startTime;
                        stopStack.add(new Work(current.name, current.startTime, remain));
                        break;
                    }
                }
            }
            
            current = readyQueue.poll();
            now = current.startTime;
        }
        
        answer[index] = current.name;
        index+=1;
        while(!stopStack.isEmpty()) {
            answer[index] = stopStack.pop().name;
            index+=1;
        }
        
        return answer;
    }
    
    private int convertTime(String time) {
        String[] info = time.split(":");
        int hour = Integer.parseInt(info[0]);
        int minute = Integer.parseInt(info[1]);
        return hour*60 + minute;
    }
}