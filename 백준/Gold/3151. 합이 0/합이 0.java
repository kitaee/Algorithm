import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        String[] info = br.readLine().split(" ");
        for(int i=0; i<info.length; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }
        Arrays.sort(arr);

        for(int i=0; i<N-2; i++) {
            if(arr[i] > 0) {
                break;
            }
            int left = i+1;
            int right = N-1;

            while(left < right) {
                int target = arr[i] + arr[left] + arr[right];
                long leftCount = 1;
                long rightCount = 1;
                if(target == 0) {
                    if(arr[left] == arr[right]) {
                        long sum = right-left+1;
                        answer += (sum*(sum-1))/2;
                        break;
                    }
                    while(left+1<right && arr[left]==arr[left+1]) {
                        left+=1;
                        leftCount+=1;
                    }
                    while(right-1>left && arr[right]==arr[right-1]) {
                        right-=1;
                        rightCount+=1;
                    }
                    answer += (leftCount*rightCount);
                }
                if(target<0) {
                    left+=1;
                } else {
                    right-=1;
                }
            }
        }
        System.out.println(answer);
    }
}