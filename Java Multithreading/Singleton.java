class SingletonClass{
    private static SingletonClass instance = null;
    public static Object lock = new Object();

    private SingletonClass() {
        super();
    }

    public static SingletonClass getInstance(){
        
        if(instance == null){
            synchronized(lock){
                if(instance == null){
                    instance = new SingletonClass();
                }
            }
        }
        return instance;
    }
}

public class Singleton {
    public static void main(String args[]){

        Thread t1 = new Thread(() -> {
            System.out.println(SingletonClass.getInstance());
        });
        Thread t2 = new Thread(() -> {
            System.out.println(SingletonClass.getInstance());
        });
        Thread t3 = new Thread(() -> {
            System.out.println(SingletonClass.getInstance());
        });
        Thread t4 = new Thread(() -> {
            System.out.println(SingletonClass.getInstance());
        });
        Thread t5 = new Thread(() -> {
            System.out.println(SingletonClass.getInstance());
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}
