import java.io.*;
import java.util.*;

public class Main {

    static int a,b,c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        a = Integer.parseInt(info[0]);
        b = Integer.parseInt(info[1]);
        c = Integer.parseInt(info[2]);

        System.out.println(pow(a, b));
    }

    static long pow(long base, long exponent) {
        if(exponent == 1) {
            return a%c;
        }

        long temp = pow(base, exponent/2);
        if(exponent%2 == 1) {
            return (temp*temp%c)*a%c;
        }
        return temp*temp%c;
    }
}