import java.io.*;
import java.util.*;

public class Main {

    static class Info implements Comparable<Info> {
        int start;
        int end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Info info) {
            if(this.end == info.end) {
                return this.start - info.start;
            }
            return this.end - info.end;
        }
    }

    static int N;
    static Queue<Info> queue = new PriorityQueue<>();
    static int answer = 0;
    static int now = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            queue.offer(new Info(Integer.parseInt(info[0]), Integer.parseInt(info[1])));
        }

        while(!queue.isEmpty()) {
            Info info = queue.poll();
            if(now <= info.start) {
                answer+=1;
                now = info.end;
            }
        }

        System.out.println(answer);
    }
}