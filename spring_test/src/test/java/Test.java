import java.util.*;

public class Test {

    public static void main(String[] a) {
        int[] arr = {7, 4, 13, 12};
        int min = 0;
        int max = 0;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (arr[i] <= arr[j]) {
                    if(j==arr.length-1){
                        min = arr[i];
                        break;
                    }
                }
                else {break;}
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                if (arr[i] >= arr[j]) {
                    if(j==arr.length-2){
                        max = arr[i];
                        break;
                    }
                }
                else {break;}
            }
        }
        for (int k = 0; k < arr.length; k++) {
            if (min == arr[k]) {
                temp = arr[arr.length - 1];
                arr[arr.length-1] = min;
                arr[k] = temp;
            }
            if(max==arr[k]){
                temp = arr[0];
                arr[0] = max;
                arr[k] = temp;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
