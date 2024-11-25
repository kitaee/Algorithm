import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static String[] info;
    static int[] arr;
    static int[] result;
    static int[] visited;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            info = br.readLine().split(" ");
            if(info.length == 1) {
                break;
            }
            N = info.length-1;
            arr = new int[N];
            result = new int[6];
            visited = new int[N];
            for(int i=1; i<N+1; i++) {
                arr[i-1] = Integer.parseInt(info[i]);
            }
            Arrays.sort(arr);
            combination(0, 0);
            answer.append("\n");
        }

        System.out.println(answer.toString());
    }

    static void combination(int depth, int start) {
        if(depth == 6) {
            for(int i=0; i<6; i++) {
                answer.append(result[i] + " ");
            }
            answer.append("\n");
            return;
        }
        for(int i=start; i<N; i++) {
            if(visited[i]==0) {
                visited[i] = 1;
                result[depth] = arr[i];
                combination(depth+1, i+1);
                visited[i] = 0;
            }
        }
    }
}