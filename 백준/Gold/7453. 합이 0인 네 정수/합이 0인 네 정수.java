import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] arr1,arr2,arr3,arr4;
    static long answer = 0;
    static int[] tempArr1,tempArr2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr1 = new int[n];
        arr2 = new int[n];
        arr3 = new int[n];
        arr4 = new int[n];
        tempArr1 = new int[n*n];
        tempArr2 = new int[n*n];
        int index = 0;

        for(int i=0; i<n; i++) {
            String[] info = br.readLine().split(" ");
            arr1[i] = Integer.parseInt(info[0]);
            arr2[i] = Integer.parseInt(info[1]);
            arr3[i] = Integer.parseInt(info[2]);
            arr4[i] = Integer.parseInt(info[3]);
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                tempArr1[index] = arr1[i] + arr2[j];
                tempArr2[index] = arr3[i] + arr4[j];
                index+=1;
            }
        }

        Arrays.sort(tempArr1);

        for(int i=0; i<n*n; i++) {
            int lowBound = lowerBound(tempArr2[i]*-1);
            int upBound = upperBound(tempArr2[i]*-1);
            answer += (upBound - lowBound);
        }

        System.out.println(answer);
    }

    static int lowerBound(int target) {
        int left = 0;
        int right = tempArr1.length-1;

        while(left <= right) {
            int mid = (left+right)/2;
            if(tempArr1[mid] < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        return left;
    }

    static int upperBound(int target) {
        int left = 0;
        int right = tempArr1.length-1;

        while(left <= right) {
            int mid = (left+right)/2;
            if(tempArr1[mid] <= target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        return left;
    }
}