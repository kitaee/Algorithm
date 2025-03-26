class Solution {
    
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(0, numbers, target, 0);
        return answer;
    }
    
    static void dfs(int depth, int[] numbers, int target, int temp) {
        if(depth == numbers.length) {
            if(temp == target) {
                answer+=1;
            }
            return;
        }
        dfs(depth+1, numbers, target, temp+numbers[depth]);
        dfs(depth+1, numbers, target, temp-numbers[depth]);
    }
}