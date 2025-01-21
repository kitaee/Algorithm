import java.util.*;
import java.io.*;

public class Main {

    static class MaxNode implements Comparable<MaxNode> {
        int value;

        public MaxNode(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(MaxNode maxNode) {
            if(this.value <= maxNode.value) {
                return 1;
            } return -1;
        }
    }

    static class MinNode implements Comparable<MinNode> {
        int value;

        public MinNode(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(MinNode minNode) {
            if(this.value >= minNode.value) {
                return 1;
            } return -1;
        }
    }

    static int T,K;
    static Queue<MinNode> minQueue;
    static Queue<MaxNode> maxQueue;
    static Map<Integer, Integer> map;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            K = Integer.parseInt(br.readLine());
            minQueue = new PriorityQueue<>();
            maxQueue = new PriorityQueue<>();
            map = new HashMap<>();

            for(int j=0; j<K; j++) {
                String[] info = br.readLine().split(" ");
                String command = info[0];
                int target = Integer.parseInt(info[1]);
                if(command.equals("I")) {
                    minQueue.offer(new MinNode(target));
                    maxQueue.offer(new MaxNode(target));
                    map.put(target, map.getOrDefault(target, 0)+1);
                } else {
                    if(target == -1) {
                        while(!minQueue.isEmpty()) {
                            MinNode minNode = minQueue.poll();
                            if(map.containsKey(minNode.value)) {
                                map.put(minNode.value, map.get(minNode.value)-1);
                                if(map.get(minNode.value) == 0) {
                                    map.remove(minNode.value);
                                }
                                break;
                            }
                        }
                    } else {
                        while(!maxQueue.isEmpty()) {
                            MaxNode maxNode = maxQueue.poll();
                            if(map.containsKey(maxNode.value)) {
                                map.put(maxNode.value, map.get(maxNode.value)-1);
                                if(map.get(maxNode.value) == 0) {
                                    map.remove(maxNode.value);
                                }
                                break;
                            }
                        }
                    }
                }
            }

            if(map.isEmpty()) {
                answer.append("EMPTY\n");
            } else {
                while(!maxQueue.isEmpty()) {
                    MaxNode maxNode = maxQueue.poll();
                    if(map.containsKey(maxNode.value)) {
                        answer.append(maxNode.value + " ");
                        break;
                    }
                }
                while(!minQueue.isEmpty()) {
                    MinNode minNode = minQueue.poll();
                    if(map.containsKey(minNode.value)) {
                        answer.append(minNode.value);
                        break;
                    }
                }
                answer.append("\n");
            }
        }
        System.out.println(answer.toString());
    }
}