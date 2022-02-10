package day3;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xuandongguo
 */
public class SyncrhonizedQueueTest  {
    public static void main(String[] args) {
        SynchronousQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                blockingQueue.put("1");
                System.out.println("put1");
                blockingQueue.put("2");
                System.out.println("put2");
                blockingQueue.put("3");
                System.out.println("put3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Take"+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Take"+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Take"+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"2").start();
    }
}
