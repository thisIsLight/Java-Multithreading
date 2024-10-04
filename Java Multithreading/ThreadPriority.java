public class ThreadPriority {
    public static void main(String args[]){

        Thread thread1 = new Thread(() -> {
            System.out.println("Running High Priority Thread : " + Thread.currentThread().threadId());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Running Low Priority Thread : " + Thread.currentThread().threadId());
        });

        thread1.setPriority(10);
        thread2.setPriority(1);

        thread2.start();
        thread1.start();


    }
}
