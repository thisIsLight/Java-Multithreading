//Threads can be created using two ways : Thread class inherit or Runnable interface implementation
//We generally go with Runnable calss so that we can implement more that one interface at a time


class CreatingThreadWithThreadClass extends Thread{
    public void run(){
        System.out.println("This is the Thread class thread : "+Thread.currentThread().threadId());
    }
}

class CreatingThreadWithRunnableInterface implements Runnable{
    public void run(){
        System.out.println("This is the Runnable interface thread : "+Thread.currentThread().threadId());
    }
}

public class CreateThreads {
    public static void main(String args[]) throws Exception{

        Thread thread1 = new CreatingThreadWithThreadClass();

        Runnable runnableObject = new CreatingThreadWithRunnableInterface();
        Thread thread2 = new Thread(runnableObject);

        thread1.start();
        thread2.start();

        System.out.println("This is the main Thread : "+Thread.currentThread().threadId());

        thread1.join();
        thread2.join();

    }
}
