package day2.supportclass;

import java.util.concurrent.CountDownLatch;

/**
 * @author xuandongguo
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        //倒计时，总数为100,有必须要执行的任务时使用
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 1; i <= 100; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"Go");
                //计时-1
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //等待计数器归零
        countDownLatch.await();
        System.out.print("close the door");
    }
}
