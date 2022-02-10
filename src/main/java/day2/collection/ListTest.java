package day2.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author xuandongguo
 */
public class ListTest {
    public static void main(String[] args) {
        //Exception in thread "8" java.util.ConcurrentModificationException 并发修改异常
        //并发下,ArrayList并不安全。
        //解决方案：
        // 1 替换为 new Vector<>(); //很老的数据结构了，它的add方法有关键字syncrhonized，使得线程安全
        // 2 List<String> list = Collections.synchronizedList(new ArrayList<>()) 使用Collection.synrhonized方法
        // 3. JUC如何解决: new CopyOnWriteArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
