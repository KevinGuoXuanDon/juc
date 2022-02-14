package day4ThreadPoolAndFunctionalInterface;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author xuandongguo
 * 流式计算
 */
public class StreamTest {
    /**
     * 五个用户进行筛选
     * ID为偶数
     * 年龄大于23
     * 用户名转为大写字母
     * 用户名倒着排序
     * 最后只输出一个用户
     * @param args
     */
    public static void main(String[] args) {
        User u1 = new User(1,"a",21);
        User u2= new User(2,"b",22);
        User u3 = new User(3,"c",23);
        User u4 = new User(4,"d",24);
        User u5= new User(6,"e",25);
        List<User> list = Arrays.asList(u1,u2,u3,u4,u5);
        list.stream().filter(user->{return user.getId()%2==0 && user.getAge()>23;})
                .map(user -> {return user.getUsername().toUpperCase();})
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .forEach(System.out::println);
    }
}
@Data
class User{
    private int id;
    private int age;
    private String username;
    public User(int id,String username,int age){
        this.id=id;
        this.username=username;
        this.age=age;
    }
}
