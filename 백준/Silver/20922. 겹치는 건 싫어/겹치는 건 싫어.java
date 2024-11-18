import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;
    static int N,K;
    static String[] arr;
    static String[] info;
    static int head = 0;
    static int tail = 0;
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        K = Integer.parseInt(info[1]);
        arr = br.readLine().split(" ");
        map.put(arr[0], 1);

        while(true) {
            head+=1;
            if(head >= N) {
                break;
            }
            map.put(arr[head], map.getOrDefault(arr[head], 0) + 1);
            if(map.get(arr[head]) > K) {
                while(map.get(arr[head]) > K) {
                    map.put(arr[tail], map.get(arr[tail])-1);
                    tail+=1;
                }
            }
            answer = Math.max(answer, head-tail+1);
        }

        System.out.println(answer);
    }
}