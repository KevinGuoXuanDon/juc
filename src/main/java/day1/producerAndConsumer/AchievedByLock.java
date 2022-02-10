package day1.producerAndConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuandongguo
 */
public class AchievedByLock  {
    public static void main(String[] args) {
        Data1 data = new Data1();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

/**
 * 资源数字类
 * 判断等待，业务，通知
 */
class Data1{
    private int number=0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    //等待
    //condition.await();
    //唤醒全部
    //condition.signalAll();

    public  void increment() throws InterruptedException {

        lock.lock();
        try {
            while(number!=0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+""+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void decrement() throws InterruptedException {
        lock.lock();
        try {
            while(number==0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+""+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}