package day2.supportclass;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xuandongguo
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        //7颗龙珠召唤神龙
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.print("召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            //lambda表达式本质还是一个类，不能拿到上面for循环中的i,需要通过final获取
            int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"获取第"+ finalI +"颗龙珠");
                //等待计数器变为1
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
