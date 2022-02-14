package day5;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author xuandongguo
 * 3000
 */
class ForkJoinTest extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    //临界值
    private Long temp = 10000L;

    public ForkJoinTest(long start, long end){
        this.start=start;
        this.end=end;
    }
    @Override
    protected Long compute() {
        //如果超过临界值
        if(end-start<temp){
            Long sum=0L;
            for (Long i = start; i <=end; i++) {
                sum+=i;
            }
            return sum;
        }
        else{
            //forkjoin递归
            long middle=(start+end)/2;
            ForkJoinTest forkJoinTest = new ForkJoinTest(start, middle);
            //拆分任务，把任务压入线程队列
            forkJoinTest.fork();
            ForkJoinTest forkJoinTest2 = new ForkJoinTest(middle+1, end);
            forkJoinTest2.fork();
            return forkJoinTest.join()+forkJoinTest2.join();
        }
    }
}
