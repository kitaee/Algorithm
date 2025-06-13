import java.util.*;

class Solution {
    
    static int answer = 0;
    static int[] arr;
    static int[] result = new int[5];
    static Set<Integer> set;

    public int solution(int n, int[][] q, int[] ans) {
        arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = i+1;
        }
        
        dfs(0,0,q,ans);
        return answer;
    }
    
    private void dfs(int depth, int start, int[][] q, int[] ans) {
        if(depth == 5) {
            if(validate(q, ans)) {
                answer+=1;
            }
            return;
        }
        
        for(int i=start; i<arr.length; i++) {
            result[depth] = arr[i];
            dfs(depth+1, i+1, q, ans);
        }
    }
    
    private boolean validate(int[][] q, int[] ans) {
        set = new HashSet<>();
        for(int i=0; i<5; i++) {
            set.add(result[i]);
        }
        
        for(int i=0; i<q.length; i++) {
            int count = 0;
            for(int j=0; j<5; j++) {
                if(set.contains(q[i][j])) {
                    count+=1;
                }
            }
            if(ans[i] != count) {
                return false;
            }
        }
        return true;
    }
}







