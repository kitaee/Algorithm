import java.io.*;

public class Main {

    static int N,M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        parent = new int[N];
        for(int i=0; i<N; i++) {
            parent[i] = i;
        }

        for(int i=0; i<M; i++) {
            info = br.readLine().split(" ");
            int v1 = Integer.parseInt(info[0]);
            int v2 = Integer.parseInt(info[1]);

            if(findParent(parent[v1]) == findParent(parent[v2])) {
                System.out.println(i+1);
                return;
            }
            unionParent(v1, v2);
        }

        System.out.println(0);
    }

    static int findParent(int node) {
        if(parent[node] == node) {
            return node;
        }
        return findParent(parent[node]);
    }

    static void unionParent(int node1, int node2) {
        node1 = findParent(node1);
        node2 = findParent(node2);

        if(node1 < node2) {
            parent[node2] = node1;
        } else {
            parent[node1] = node2;
        }
    }
}