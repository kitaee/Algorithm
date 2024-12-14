class Solution {
    
    public int solution(int a, int b, int n) {
        int answer = 0;
        while(true) {
            int count = (n/a)*b;
            answer += count;
            n = n%a;
            n += count;
            if(n < a) {
                break;
            }
        }
        return answer;
    }
}