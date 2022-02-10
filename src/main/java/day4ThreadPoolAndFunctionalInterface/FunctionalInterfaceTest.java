package day4ThreadPoolAndFunctionalInterface;

import java.util.function.Function;

/**
 * @author xuandongguo
 */
public class FunctionalInterfaceTest {
    public static void main(String[] args) {
        //更简洁的：(str)->str
        Function n = (str)->{
            return str;
        };
        System.out.println(n.apply(123));
    }
}
