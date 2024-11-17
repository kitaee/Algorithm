import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long left = 1;
        long right = (long) n*times[times.length-1];
        long answer = right;
        
        while(left <= right) {
            long mid = (left+right)/2;
            long count = countTarget(times, mid);
            
            if(count >= n) {
                answer = Math.min(answer, mid);
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        
        return answer;
    }
    
    static long countTarget(int[] times, long target) {
        long count = 0;
        for(int i=0; i<times.length; i++) {
            count += (target/times[i]);
        }
        return count;
    }
}