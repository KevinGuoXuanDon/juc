package day3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xuandongguo
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        Cache cache = new Cache();
        for (int i = 1; i <= 5; i++) {
            final int temp=i;
            new Thread(()->{
                cache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int temp=i;
            new Thread(()->{
                cache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}

/**
 *
 */
class Cache{
    /**
     * volatile共享资源同步
     */
    private volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    public void put(String key, Object value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+("写入")+key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+("写入OK")+key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }

    public void get(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+("读取")+key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+("读取ok")+key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}