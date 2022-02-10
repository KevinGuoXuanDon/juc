package day1.eightLock;

import java.util.concurrent.TimeUnit;

/**
 * @author xuandongguo
 */
public class EightLock {
    public static void main(String[] args) {
        Phone p = new Phone();
        Phone p2 = new Phone();
        new Thread(()->{
            p.sentSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            p2.call();
        },"B").start();
    }
}
 
class Phone{
    public synchronized void sentSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
    public void hello(){
        System.out.println("hello");
    }
}
