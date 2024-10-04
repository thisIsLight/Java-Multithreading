//Java provide a specific locking system.
//This gives us the ability to lock the critical section separately using a ReentrantLock

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class BasicReLocks{
    public int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment(){
        try{
            lock.lock();
            count++;
        }    
        finally{
            lock.unlock();
        }
    }
}

class ReLocksUsingConditions{
    public ArrayList<Integer> queue = new ArrayList<Integer>(5);
    private final ReentrantLock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();

    public void put(int value){
        try{
            lock.lock();
            while(queue.size() == 5){
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(value);
            c2.signal();
        }
        finally{
            lock.unlock();
        }
    }

    public void get(){
        try {
            lock.lock();
            while(queue.isEmpty()){
                try {
                    c2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.remove(0);
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
}



public class ReentrantLocking {
    public static void main(String args[]) throws Exception{

        RunningBasicLock();

        RunningPubSub();
        
    }

    private static void RunningPubSub() throws InterruptedException {
        ReLocksUsingConditions obj = new ReLocksUsingConditions();

        Thread t1 = new Thread(() -> {
            for(int i=0;i<100000;i++){
                obj.put(i);
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i=0;i<100000;i++){
                System.out.println(obj.queue.size());
                obj.get();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(obj.queue);
    }

    private static void RunningBasicLock() throws InterruptedException {
        BasicReLocks obj = new BasicReLocks();

        Thread t1 = new Thread(() -> {
            for(int i=0;i<100000;i++){
                obj.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i=0;i<100000;i++){
                obj.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(obj.count);
    }
}
