import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static int[] arr1;
    static Map<Integer, Integer> map = new HashMap<>();
    static List<Integer> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);

        arr1 = new int[N];

        info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr1[i] = Integer.parseInt(info[i]);
        }
        Arrays.sort(arr1);
        info = br.readLine().split(" ");
        for(int i=0; i<M; i++) {
            map.put(Integer.parseInt(info[i]), 1);
        }

        for(int i=0; i<N; i++) {
            if(!map.containsKey(arr1[i])) {
                answerList.add(arr1[i]);
            }
        }

        if(answerList.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(answerList.size());
            for(int i=0; i<answerList.size(); i++) {
                System.out.print(answerList.get(i) + " ");
            }
        }
    }
}