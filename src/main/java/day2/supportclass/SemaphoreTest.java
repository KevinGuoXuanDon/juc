package day2.supportclass;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author xuandongguo
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        //三个停车位，线程数为3,限流
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                //acquire得到
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"get car parks");
                    //停车2s
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"release car park");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放线程
                   semaphore.release();
                }

            },String.valueOf(i)).start();
        }
    }
}
