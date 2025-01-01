import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static String target;
    static StringBuilder sb = new StringBuilder();
    static Stack<String> stack;
    static boolean flag = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            target = br.readLine();
            stack = new Stack<>();
            flag = true;
            for(int j=0; j<target.length(); j++) {
                String current = String.valueOf(target.charAt(j));
                if(current.equals("(")) {
                    stack.push(current);
                } else {
                    if(stack.isEmpty()) {
                        sb.append("NO\n");
                        flag = false;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
            if(!flag) {
                continue;
            }
            if(stack.isEmpty()) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb.toString());
    }
}