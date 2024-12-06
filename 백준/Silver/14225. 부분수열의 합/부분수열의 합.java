import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        String[] info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }

        combination(0, 0);

        for(int i=1; i<Integer.MAX_VALUE; i++) {
            if(!map.containsKey(i)) {
                System.out.println(i);
                break;
            }
        }
    }

    static void combination(int depth, int sum) {
        if(depth == N) {
            map.put(sum, 1);
            return;
        }
        combination(depth+1, sum);
        combination(depth+1, sum+arr[depth]);
    }
}