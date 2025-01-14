import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Node node) {
            if(Math.abs(this.value) == Math.abs(node.value)) {
                return this.value - node.value;
            }
            return Math.abs(this.value) - Math.abs(node.value);
        }
    }

    static int N;
    static Queue<Node> queue = new PriorityQueue<>();
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            int target = Integer.parseInt(br.readLine());
            if(target == 0) {
                if(queue.isEmpty()) {
                    answer.append(0 + "\n");
                } else {
                    answer.append(queue.poll().value + "\n");
                }
            } else {
                queue.offer(new Node(target));
            }
        }

        System.out.println(answer);
    }
}