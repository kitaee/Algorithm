import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] boxes;
    static int[] dp;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] info = br.readLine().split(" ");
        boxes = new int[N];
        dp = new int[N];
        for(int i=0; i<N; i++) {
            boxes[i] = Integer.parseInt(info[i]);
        }

        for(int i=0; i<N; i++) {
            dp[i] = 1;
        }

        for(int i=1; i<N; i++) {
            for(int j=0; j<i; j++) {
                if(boxes[j] < boxes[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}