import java.io.*;
import java.util.*;

public class Main {

    static int N,M,L;
    static List<Integer> dist = new ArrayList<>();
    static String[] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        L = Integer.parseInt(info[2]);
        dist.add(0);
        dist.add(L);

        info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            dist.add(Integer.parseInt(info[i]));
        }
        Collections.sort(dist);
        System.out.println(binarySearch());
    }

    static int createRestArea(int mid) {
        int count = 0;
        for(int i=0; i<dist.size()-1; i++) {
            count += ((dist.get(i+1)-dist.get(i)-1)/mid);
        }
        return count;
    }

    static int binarySearch() {
        int left = 1;
        int right = L-1;

        while(left <= right) {
            int mid = (left+right)/2;
            int count = createRestArea(mid);

            if(count <= M) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        return left;
    }
}