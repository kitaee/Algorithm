import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] arr;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            for(int j=0; j<info.length; j++) {
                arr[i][j] = Integer.parseInt(info[j]);
            }
        }

        for(int i=1; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(j == 0) {
                    arr[i][j] += arr[i-1][j];
                } else {
                    arr[i][j] += (Math.max(arr[i-1][j-1], arr[i-1][j]));
                }
            }
        }

        for(int i=0; i<N; i++) {
            answer = Math.max(answer, arr[N-1][i]);
        }
        System.out.println(answer);
    }
}