package day1.producerAndConsumer;

/**
 * @author xuandongguo
 */
public class AchievedBySynchronized {
    public static void main(String[] args) {
        Data data = new Data();
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
class Data{
    private int number=0;
    public synchronized void increment() throws InterruptedException {
        // 这里判断不能用if，只做一次判断可能会虚假唤醒
        // 拿两个加法线程A、B来说，比如A先执行，执行时调用了wait方法，那它会等待，此时会释放锁
        // 那么线程B获得锁并且也会执行wait方法，两个加线程一起等待被唤醒。
        // 此时减线程中的某一个线程执行完毕并且唤醒了这俩加线程，那么这俩加线程不会一起执行，其中A获取了锁并且加1，执行完毕之后B再执行。
        // 如果是if的话，那么A修改完num后，B不会再去判断num的值，直接会给num+1。
        // 如果是while的话，A执行完之后，B还会去判断num的值，因此就不会执行
        while(number!=0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+""+number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while(number==0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+""+number);
        this.notifyAll();
    }
}