package SingleInstance;

//当这个单例中大量的数据结构时，直接创建会比较消耗内存
//而懒汉式是动态的
public class Hungary {
    private final static Hungary instance = new Hungary();

    private byte[] bytes1 = new byte[1024*1024];
    private byte[] bytes2 = new byte[1024*1024];
    private byte[] bytes3 = new byte[1024*1024];

    private Hungary(){

    }

    public static Hungary getInstance(){
        return instance;
    }
}
