import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int K = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        hanoi(N, 1, 2, 3);
        System.out.println(K);
        System.out.println(sb.toString());
    }

    static void hanoi(int num, int start, int mid, int end) {
        K+=1;

        if(num == 1) {
            sb.append(String.valueOf(start) + " " + String.valueOf(end) + "\n");
            return;
        }

        hanoi(num-1, start, end, mid);
        sb.append(String.valueOf(start) + " " + String.valueOf(end) + "\n");
        hanoi(num-1, mid, start, end);
    }
}