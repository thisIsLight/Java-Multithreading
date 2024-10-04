//We will create an object and try to increment a property of it using two different threads
//When we run them for many times, there are scenarios when both of them will try to access it and there is a data inconsisteny 
//100000+100000 should give 200000 but it yields something less than 200000

class Counter{
    public int count = 1;

    public void Increment(){
        count++;
    }
}

public class RaceCondition {
    public static void main(String args[]) throws Exception{

        Counter countObj = new Counter();

        Thread thread1 = new Thread( () -> {
            for(int i=0;i<100000;i++)
                countObj.Increment();
        });

        Thread thread2 = new Thread( () -> {
            for(int i=0;i<100000;i++)
                countObj.Increment();
        });


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(countObj.count);
    }
}
