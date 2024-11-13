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
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map.put(arr[i]+arr[j], 1);
            }
        }

        for(int i=N-1; i>=0; i--) {
            for(int j=N-1; j>=0; j--) {
                int target = arr[i]-arr[j];
                if(map.containsKey(target)) {
                    System.out.println(arr[i]);
                    System.exit(0);
                }
            }
        }
    }
}