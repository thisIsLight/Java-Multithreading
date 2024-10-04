//We can resolve by various means
// Using synchronized method
// Using synchronized block
// Using AtomicInteger

import java.util.concurrent.atomic.AtomicInteger;

class Counter{
    public int count = 0;
    private Object lock = new Object();
    public AtomicInteger countAtomic = new AtomicInteger(0);

    public synchronized void IncrementUsingMethodSync(){
        count++;
    }

    public void IncrementUsingSyncBlock(){
        synchronized(lock){
            count++;
        }
    }

    public synchronized void IncrementUsingAtomicInteger(){
        countAtomic.incrementAndGet();
    }
}

public class ResolvingRaceCondition {
    public static void main(String args[]) throws Exception{

        Counter countObj = new Counter();

        Thread thread1 = new Thread( () -> {
            for(int i=0;i<100000;i++)
                // countObj.IncrementUsingMethodSync();
                // countObj.IncrementUsingSyncBlock();
                countObj.IncrementUsingAtomicInteger();
        });

        Thread thread2 = new Thread( () -> {
            for(int i=0;i<100000;i++)
                // countObj.IncrementUsingMethodSync();
                // countObj.IncrementUsingSyncBlock();
                countObj.IncrementUsingAtomicInteger();
        });


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        // System.out.println(countObj.count);
        System.out.println(countObj.countAtomic);
    }
}
