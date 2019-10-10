import org.junit.Test;

import java.util.*;

public class Test1 {
    @Test
    public void test02(){
        String s = "abcdekka27qoq";
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(!map.containsKey(chars[i])){
                map.put(chars[i],1);
            }else{
                Integer integer = map.get(chars[i]);
                integer++;
                map.put(chars[i],integer);
            }
        }
        System.out.println(map);
    }
    @Test
    public void Test03(){
        Thread01 thread01 = new Thread01();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    thread01.subThread();
                }
            }
        }).start();
        for (int j = 0; j <3; j++) {
            thread01.mainThread();
        }
    }

    public static void main(String[]a){
        Thread03 thread03 = new Thread03();
        Thread thread = new Thread(thread03, "主线程");
        Thread thread1 = new Thread(thread03, "次线程");
        thread.start();
        thread1.start();
    }
}
class Thread01{
    private boolean flag;
    public synchronized void mainThread(){
        if(flag==false){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
        flag=true;
        notify();
    }
    public synchronized void subThread(){
        if(flag==true){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 5; j++) {
            System.out.println(Thread.currentThread().getName()+" "+j);
        }
        flag=false;
        notify();
    }
}
class Thread03 implements Runnable{
    private int i=1000;
    String s = "锁";
    @Override
    public void run() {
        while (true){
              synchronized (s) {
                  if (i >= 0) {
                          System.out.println(Thread.currentThread().getName() + "," + i);
                          i--;
                  }
             }
        }
    }
}



