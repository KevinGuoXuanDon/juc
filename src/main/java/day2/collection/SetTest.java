package day2.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author xuandongguo
 */
public class SetTest {
    public static void main(String[] args) {
        //不安全
        //Set<String> set = new HashSet<>();
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set2 = new CopyOnWriteArraySet();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,1));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
