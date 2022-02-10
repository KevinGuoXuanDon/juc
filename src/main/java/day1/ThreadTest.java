package day1;
import java.lang.Thread;
public class ThreadTest {
    public static void main(String[] args) {
        //view the original code of thread
        new Thread().start();
        //get cpu processors 获取CPU核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        //check processors' state
        //Thread.State;
        //Sleep需要捕获异常
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadTest t = new ThreadTest();
    }
}
