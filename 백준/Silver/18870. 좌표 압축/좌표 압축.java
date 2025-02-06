import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static Map<Integer, Integer> map = new HashMap<>();
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        String[] info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(info[i]);
            if(map.containsKey(arr[i])) {
                continue;
            } else {
                map.put(arr[i], 0);
                list.add(arr[i]);
            }
        }

        Collections.sort(list);
        for(int i=0; i<arr.length; i++) {
            if(map.get(arr[i]) == 0) {
                int index = binarySearch(arr[i]);
                bw.write(index + " ");
            } else {
                bw.write(map.get(arr[i]) + " ");
            }
        }

        bw.flush();
        bw.close();
    }

    static int binarySearch(int target) {
        int start = 0;
        int end = list.size();

        while(start <= end) {
            int mid = (start+end)/2;
            if(list.get(mid) == target) {
                return mid;
            } else if(list.get(mid) < target) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }

        return -1;
    }
}

// -10 -9 2 4