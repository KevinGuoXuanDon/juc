package day2.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {
        //Map也线程不安全，所以一般工作中不会直接使用 new HashMap<>()
        //Map<String,String> map = new HashMap<>(16,0.75f);
        //第一种解决方法
        Collections.synchronizedMap(new HashMap<>(16,075f));
        //JUC
        Map<String,String> map = new ConcurrentHashMap<>(16,0.75f);
        for (int i = 0; i <= 30; i++) {
            new Thread(()->{
                map.put(UUID.randomUUID().toString().substring(1),"1");
                System.out.print(map);
            },String.valueOf(i)).start();
        }
    }
}
