import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static int[] arr;
    static int left;
    static int right;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        M = Integer.parseInt(info[0]);
        N = Integer.parseInt(info[1]);
        arr = new int[N];
        info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }
        Arrays.sort(arr);

        left = 1;
        right = arr[N-1];

        while(left <= right) {
            int mid = (left+right)/2;
            int count = shareSnack(mid);
            if(count >= M) {
                answer = Math.max(answer, mid);
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        System.out.println(answer);
    }

    static int shareSnack(int target) {
        int count = 0;
        for(int i=0; i<N; i++) {
            if(arr[i] >= target) {
                count+=(arr[i]/target);
            }
        }
        return count;
    }
}