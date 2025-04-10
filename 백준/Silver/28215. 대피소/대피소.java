import java.io.*;
import java.util.*;

public class Main {

    static int answer = Integer.MAX_VALUE;
    static int tempDistance = 0;
    static int N,K;
    static int[] arr;
    static int[] visited;
    static int[] result;
    static Map<Integer, int[]> index = new HashMap<>();
    static String[] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        K = Integer.parseInt(info[1]);
        arr = new int[N];
        visited = new int[N];
        result = new int[K];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            arr[i] = i;
            int x = Integer.parseInt(info[0]);
            int y = Integer.parseInt(info[1]);
            index.put(i, new int[]{x,y});
        }

        combination(0,0);
        System.out.println(answer);
    }

    static void combination(int depth, int start) {
        if(depth == K) {
            simulation();
            answer = Math.min(answer, tempDistance);
            tempDistance = 0;
            return;
        }
        for(int i=start; i<N; i++) {
            if(visited[i]==0) {
                visited[i] = 1;
                result[depth] = arr[i];
                combination(depth+1, i+1);
                visited[i] = 0;
            }
        }
    }

    static void simulation() {
        for(int i=0; i<N; i++) {
            int distance = Integer.MAX_VALUE;
            if(visited[i] == 0) {
                int[] homeInfo = index.get(i);
                for(int j=0; j<K; j++) {
                    int[] indexInfo = index.get(result[j]);
                    distance = Math.min(distance, getDistance(homeInfo[0],homeInfo[1],indexInfo[0],indexInfo[1]));
                }
                tempDistance = Math.max(tempDistance, distance);
            }
        }
    }

    static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}