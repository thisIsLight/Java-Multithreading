//We will implemet a thread using Runnable lambda function
//We are going to use the following : 
//Sleep()
//Start()
//Yield()
//Join()
//Interrupt()

public class ThreadMethods {
    public static void main(String args[]) throws Exception{
        
        Thread thread1 = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("Entered the thread using start()");
                System.out.println("waited for 1 second using sleep()");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("THread was interrupted");
            }
        });

        //start()
        thread1.start();
        Thread.sleep(1000);
        System.out.println("Interrupting the thread");
        thread1.interrupt();

    }
}
