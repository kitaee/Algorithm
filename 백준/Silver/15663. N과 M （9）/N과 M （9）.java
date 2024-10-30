import java.io.*;
import java.time.*;
import java.util.*;

public class Main {

    static int N,M;
    static int[] arr;
    static int[] visited;
    static int[] result;
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);

        arr = new int[N];
        result = new int[M];
        visited = new int[N];

        info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }
        Arrays.sort(arr);

        dfs(0);
    }

    static void dfs(int depth) {
        if(depth == M) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<M; i++) {
                sb.append(result[i] + " ");
            }
            if(!map.containsKey(sb.toString())) {
                System.out.println(sb);
            }
            map.put(sb.toString(), 1);
            return;
        }
        for(int i=0; i<N; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                result[depth] = arr[i];
                dfs(depth+1);
                visited[i] = 0;
            }
        }
    }
}