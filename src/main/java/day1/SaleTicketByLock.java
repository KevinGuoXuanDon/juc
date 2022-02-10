package day1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuandongguo
 */
public class SaleTicketByLock {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();
        // Runnable函数式接口functionalInterface 如果不用lambda
        new Thread(() -> {
            for (int i = 0; i < 600; i++) {
                ticket.sale();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 600; i++) {
                ticket.sale();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 600; i++) {
                ticket.sale();
            }
            //当且仅当这个线程拥有这个对象锁时，返回true
            System.out.println(Thread.holdsLock("c"));
        },"C").start();

    }
}


class Ticket2{
    private int number = 1000;
    Lock lock = new ReentrantLock();
    public synchronized void sale(){
        lock.lock();
        try {
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"卖出第"+(number--)+"，剩余"+number);}
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //用完解锁，使用finally修饰.
            lock.unlock();
        }
    }
}