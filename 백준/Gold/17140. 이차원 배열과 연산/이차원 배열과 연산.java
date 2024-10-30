import java.io.*;
import java.time.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int value;
        int count;

        public Node(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(Node node) {
            if(this.count == node.count) {
                return this.value - node.value;
            }
            return this.count - node.count;
        }
    }

    static int r,c,k;
    static int[][] arr = new int[3][3];
    static int answer = 0;
    static int size = 0;
    static Map<Integer, Integer> map;
    static Queue<Node> queue;
    static List<List<Integer>> tempArr;
    static List<Integer> tempSubArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        r = Integer.parseInt(info[0])-1;
        c = Integer.parseInt(info[1])-1;
        k = Integer.parseInt(info[2]);

        for(int i=0; i<3; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<3; j++) {
                arr[i][j] = Integer.parseInt(info[j]);
            }
        }

        while(true) {
            if(isAvailable()) {
                break;
            }
            String operationType = getOperationType();
            tempArr = new ArrayList<>();
            size = 0;

            if(operationType.equals("R")) {
                for(int i=0; i<arr.length; i++) {
                    map = new HashMap<>();
                    queue = new PriorityQueue<>();
                    tempSubArr = new ArrayList<>();
                    for(int j=0; j<arr[i].length; j++) {
                        if(arr[i][j] != 0) {
                            map.put(arr[i][j], map.getOrDefault(arr[i][j], 0)+1);
                        }
                    }
                    for(int value : map.keySet()) {
                        queue.offer(new Node(value, map.get(value)));
                    }
                    while(!queue.isEmpty()) {
                        Node node = queue.poll();
                        tempSubArr.add(node.value);
                        tempSubArr.add(node.count);
                    }
                    size = Math.max(size, tempSubArr.size());
                    tempArr.add(tempSubArr);
                }
                if(size >= 100) {
                    arr = new int[arr.length][100];
                } else {
                    arr = new int[arr.length][size];
                }
                arr = new int[arr.length][size];
                for(int i=0; i<tempArr.size(); i++) {
                    for(int j=0; j<tempArr.get(i).size(); j++) {
                        arr[i][j] = tempArr.get(i).get(j);
                    }
                }
            } else {
                for(int i=0; i<arr[0].length; i++) {
                    map = new HashMap<>();
                    queue = new PriorityQueue<>();
                    tempSubArr = new ArrayList<>();
                    for(int j=0; j<arr.length; j++) {
                        if(arr[j][i] != 0) {
                            map.put(arr[j][i], map.getOrDefault(arr[j][i], 0)+1);
                        }
                    }
                    for(int value : map.keySet()) {
                        queue.offer(new Node(value, map.get(value)));
                    }
                    while(!queue.isEmpty()) {
                        Node node = queue.poll();
                        tempSubArr.add(node.value);
                        tempSubArr.add(node.count);
                    }
                    size = Math.max(size, tempSubArr.size());
                    tempArr.add(tempSubArr);
                }
                if(size >= 100) {
                    arr = new int[100][arr[0].length];
                } else {
                    arr = new int[size][arr[0].length];
                }
                for(int i=0; i<tempArr.size(); i++) {
                    for(int j=0; j<tempArr.get(i).size(); j++) {
                        arr[j][i] = tempArr.get(i).get(j);
                    }
                }
            }

            answer+=1;
            if(answer > 100) {
                answer = -1;
                break;
            }
        }
        System.out.println(answer);
    }

    static String getOperationType() {
        if(arr[0].length <= arr.length) {
            return "R";
        }
        return "C";
    }

    static boolean isAvailable() {
        if(arr.length>r && arr[0].length>c && arr[r][c]==k) {
            return true;
        }
        return false;
    }
}