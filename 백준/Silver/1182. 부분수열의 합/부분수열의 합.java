import java.io.*;
import java.util.*;

public class Main {

    static int N,S;
    static int[] arr;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        S = Integer.parseInt(info[1]);

        arr = new int[N];
        info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }

        dfs(0, 0);
        if(S == 0) {
            answer-=1;
        }
        System.out.println(answer);
    }

    static void dfs(int depth, int sum) {
       if(depth == N) {
           if(sum == S) {
               answer+=1;
           }
           return;
       }
       dfs(depth+1, sum);
       dfs(depth+1, sum+arr[depth]);
    }
}