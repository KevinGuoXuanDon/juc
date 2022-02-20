package SingleInstance;


import java.util.concurrent.*;

public class LazyMan {
    //volatile关键字防止指令重排问题
    private volatile static LazyMan instance;
    private static boolean secret = false;

    //反射可能通过构造器来修改访问权限，使得类能被多次实例
    //这里需要用一个密钥，当调用构造器实例这个类时，secret变为true，防止反射调用
    private LazyMan(){
        if(secret == false){
            secret = true;
        }else{
            throw new RuntimeException("不要试图用反射破坏单例");
        }


    }

    //这里需要双重判断实例是否为空，并且加上锁，否则多线程情况下可能创建出多个"单例"
    public static LazyMan getInstance(){
        if(instance == null){
            synchronized (LazyMan.class){
                if(instance == null){
                    instance = new LazyMan();
                }
            }
        }
        return instance;
    }
}
