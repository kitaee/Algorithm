import java.io.*;
import java.util.*;

public class Main {

    static String inputString;
    static String bomb;
    static Stack<Character> stack = new Stack<>();
    static boolean hasBomb = true;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputString = br.readLine();
        bomb = br.readLine();

        for(int i=0; i<inputString.length(); i++) {
            stack.push(inputString.charAt(i));
            if(stack.size() >= bomb.length()) {
                hasBomb = true;
                for(int j=0; j<bomb.length(); j++) {
                    if(bomb.charAt(j) != stack.get(stack.size()-bomb.length()+j)) {
                        hasBomb = false;
                        break;
                    }
                }
                if(hasBomb) {
                    for(int j=0; j<bomb.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        if(stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            for(int i=0; i<stack.size(); i++) {
                sb.append(stack.get(i));
            }
            System.out.println(sb.toString());
        }
    }
}