package day4ThreadPoolAndFunctionalInterface;

import java.util.concurrent.*;

/**
 * @author xuandongguo
 */
//使用线程池来创建线程
public class ThreadPoolCreate {
    public static void main(String[] args) {
        //单个线程
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
        //创建一个固定大小的线程池
        ExecutorService threadPool2 =Executors.newFixedThreadPool(5);
        //可伸缩的线程池
        ExecutorService threadPool3 =Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,5,
                3,TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 1; i <= 9  ; i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }
}
