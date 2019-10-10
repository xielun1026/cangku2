package KAOSHI02;

import java.util.Random;

//随机生成8个1-10之间的数（包括1和10），将生成的数放到数组中，计算数组元素的平均值，并将结果打印到控制台上.
public class Test0000 {
    public static void main(String[] args) {
        //创建随机对象；
        Random r = new Random();
        int[]arr = new int[8];
        //初始一个存储和的变量；
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(10)+1;
            sum += arr[i];
        }
        //用总数除以数组长度获得平均值；
        int avg = sum/arr.length;
        System.out.println("该数组的平均值为："+avg);
    }
}
