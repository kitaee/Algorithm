import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] dp;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        arr = new int[N+1];

        for(int i=0; i<N; i++) {
            arr[i+1] = Integer.parseInt(br.readLine());
        }

        dp[0] = 0;
        for(int i=1; i<=N; i++) {
            if(i == 1) {
                dp[i] = arr[i];
            }
            else if(i == 2) {
                dp[i] = arr[i-1] + arr[i];
            } else {
                dp[i] = Math.max(dp[i-1], dp[i-2]+arr[i]);
                dp[i] = Math.max(dp[i], dp[i-3]+arr[i-1]+arr[i]);
            }
        }

        System.out.println(dp[N]);
    }
}