import java.io.*;
import java.time.*;
import java.util.*;

public class Main {

    static int N,M;
    static int[] arr;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);

        arr = new int[N];
        result = new int[M];

        info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }
        Arrays.sort(arr);
        dfs(0, 0);
        System.out.println(sb.toString());
    }

    static void dfs(int depth, int start) {
        if(depth == M) {
            for(int i=0; i<M; i++) {
                sb.append(result[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for(int i=start; i<N; i++) {
            result[depth] = arr[i];
            dfs(depth+1, i);
        }
    }
}