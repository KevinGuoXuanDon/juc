package day4ThreadPoolAndFunctionalInterface;

import java.util.function.Predicate;

/**
 * @author xuandongguo
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> predicate = (str)->{
            return str.isEmpty();
        };
    }
}
