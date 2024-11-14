import java.io.*;
import java.util.*;

public class Main {

    static int N,K;
    static int answer,left,right;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        K = Integer.parseInt(info[1]);
        arr = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        answer = arr[0];
        left = arr[0];
        right = arr[0]+K;

        while(left <= right) {
            int mid = (left+right)/2;
            long count = levelUp(mid);

            if(count > K) {
                right = mid-1;
            } else {
                answer = Math.max(answer, mid);
                left = mid+1;
            }
        }

        System.out.println(answer);
    }

    static long levelUp(long target) {
        long count = 0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] < target) {
                count += (target-arr[i]);
            }
        }
        return count;
    }
}