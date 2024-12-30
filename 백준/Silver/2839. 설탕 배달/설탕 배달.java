import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int x = 0;   // 5kg 봉지 갯수
    static int y = 0;   // 3kg 봉지 갯수
    static int temp = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        while(true) {
            int total = N-3*temp;
            if(total < 0) {
                break;
            }
            if(total % 5 == 0) {
                y = temp;
                x = total/5;
                break;
            }
            temp+=1;
        }

        if(x+y == 0) {
            System.out.println(-1);
        } else {
            System.out.println(x+y);
        }
    }
}