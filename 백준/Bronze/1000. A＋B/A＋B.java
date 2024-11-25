import java.io.*;
import java.util.*;

public class Main {

    static int A,B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        A = Integer.parseInt(info[0]);
        B = Integer.parseInt(info[1]);
        System.out.println(A+B);
    }
}