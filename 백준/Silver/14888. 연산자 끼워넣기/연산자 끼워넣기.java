import java.io.*;
import java.util.*;

public class Main {

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int N;
    static int[] arr;
    static int[] operations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        operations = new int[4];

        String[] info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }
        info = br.readLine().split(" ");
        for(int i=0; i<4; i++) {
            operations[i] = Integer.parseInt(info[i]);
        }

        permutation(1, arr[0]);
        System.out.println(max);
        System.out.println(min);
    }

    static void permutation(int depth, int temp) {
        if(depth == N) {
            max = Math.max(max, temp);
            min = Math.min(min, temp);
            return;
        }
        for(int i=0; i<4; i++) {
            if(operations[i]>0) {
                operations[i]-=1;
                if(i == 0) {
                    permutation(depth+1, temp+arr[depth]);
                } else if(i == 1) {
                    permutation(depth+1, temp-arr[depth]);
                } else if(i == 2) {
                    permutation(depth+1, temp*arr[depth]);
                } else {
                    permutation(depth+1, temp/arr[depth]);
                }
                operations[i]+=1;
            }
        }
    }
}