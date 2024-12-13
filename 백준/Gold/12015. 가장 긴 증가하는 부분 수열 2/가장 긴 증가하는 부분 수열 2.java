import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        String[] info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }

        list.add(arr[0]);
        for(int i=1; i<N; i++) {
            if(list.get(list.size()-1) < arr[i]) {
                list.add(arr[i]);
            } else {
                int index = lowerBound(arr[i]);
                list.set(index, arr[i]);
            }
        }

        System.out.println(list.size());
    }

    static int lowerBound(int target) {
        int start = 0;
        int end = list.size();
        int answer = 0;
        while(start <= end) {
            int mid = (start+end)/2;
            if(list.get(mid) < target) {
                start = mid+1;
            } else {
                answer = mid;
                end = mid-1;
            }
        }
        return answer;
    }
}