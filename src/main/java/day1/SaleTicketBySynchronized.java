package day1;

/**
 * 线程是一个单独的资源类，没有任何附属操作。
 */
public class SaleTicketBySynchronized {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        // Runnable函数式接口functionalInterface 如果不用lambda
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 600; i++) {
                    ticket.sale();
                }
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
        },"C").start();
    }
}


class Ticket{
    private int number = 1000;
    public synchronized void sale(){
        if(number>0) System.out.println(Thread.currentThread().getName()+"卖出第"+(number--)+"，剩余"+number);
    }
}