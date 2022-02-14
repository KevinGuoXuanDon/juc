package day5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author xuandongguo
 */
public class PerformanceTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("test1");
        test1();
        System.out.println("Test2");
        test2();
        System.out.println("Test3");
        test3();
    }
    //迭代一亿次计算
    public static void test1(){
        long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 0L; i <= 100000000L; i++) {
            sum+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println(sum+"   "+(end-start));
    }
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinTest(0L,100000000L);
        ForkJoinTask<Long> forkJoinTest = forkJoinPool.submit(task);
        long sum = forkJoinTest.get();
        long end = System.currentTimeMillis();
        //如果不需要返回值，使用execute方法代替submit，时间会从125降低为6
        System.out.println(sum+"  "+(end-start));
    }
    public static void test3(){
        long start= System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L,100000000L).parallel()
                .reduce(0,Long::sum);
        long end=System.currentTimeMillis();
        System.out.println(sum+"   "+(end-start));
    }
}
