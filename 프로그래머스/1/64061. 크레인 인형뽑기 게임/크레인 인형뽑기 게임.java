import java.util.*;

class Solution {
    
    static int answer = 0;
    static Stack<Integer> stack = new Stack<>();
    
    public int solution(int[][] board, int[] moves) {
        for(int move : moves) {
            for(int i=0; i<board.length; i++) {
                if(board[i][move-1] != 0) {
                    addBucket(board[i][move-1]);
                    board[i][move-1] = 0;
                    break;
                }
            }
        }
        return answer;
    }
    
    static void addBucket(int num) {
        if(!stack.isEmpty()) {
            if(stack.peek() == num) {
                stack.pop();
                answer+=2;
            } else {
                stack.push(num);
            }
        } else {
            stack.push(num);
        }
    }
}