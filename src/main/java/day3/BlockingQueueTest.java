package day3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xuandongguo
 */
public class BlockingQueueTest {
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(0);
    public static void main(String[] args) {

        
    }
    public void test1() throws InterruptedException {
        blockingQueue.add("0");
        blockingQueue.add("a");
        blockingQueue.offer("b");
        blockingQueue.put("c");
        blockingQueue.offer("d",2, TimeUnit.SECONDS);

        blockingQueue.remove();
        blockingQueue.poll();
        blockingQueue.take();
        blockingQueue.poll(2,TimeUnit.SECONDS);
    }
}
