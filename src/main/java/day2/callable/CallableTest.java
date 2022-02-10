package day2.callable;

import com.sun.tools.javadoc.Start;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xuandongguo
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new Thread(new Runnable() {}).start();
        // new Thread(new FutureTask<V>()).start();
        // new Thread(new FutureTask<V>(Callable)).start();
        MyThread myThread = new MyThread();
        //适配类
        FutureTask futureTask = new FutureTask(myThread);
        //这里只会执行一次。
        //     * NEW -> COMPLETING -> NORMAL
        //     * NEW -> COMPLETING -> EXCEPTIONAL
        //     * NEW -> CANCELLED
        //     * NEW -> INTERRUPTING -> INTERRUPTED
        //第二次调用的时候futuretask的状态已经不是new了，此时会直接结束线程,导致任务不执行.
        //而第一次调用时返回结果已经保存
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();

        //获取Callable返回结果,这个get要等待结果返回，futuretask源码使用了awaitDone，可能会产生阻塞
        //把它放到最后，或者使用异步通信解决。
        String s = (String) futureTask.get();
    }
}

class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.print("call");
        return "123";
    }
}

/**
 * 老式runnable
 */
class MThread implements Runnable{

    @Override
    public void run() {

    }
}