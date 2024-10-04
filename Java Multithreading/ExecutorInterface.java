//In production, we don't create threads separately and try to manage them
//We create a executorService that gives us many types of theadPools.
//We use this ExecutorService to do the thread management

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorInterface {
    public static void main(String args[]) throws Exception{
        UsingBasicExecutor();

        UsingFuture();

    }

    private static void UsingFuture() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int taskId = i;
            tasks.add(() -> {
                // Simulate some work
                Thread.sleep(1000);
                return taskId;
            });
        }

        List<Future<Integer>> results = executor.invokeAll(tasks);

        for(Future<Integer> res : results){
            System.out.println(res.get());
        }

        executor.shutdown();
    }

    private static void UsingBasicExecutor() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i=0;i<10;i++){
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown(); // No more threads can be added now
        try{
            executor.submit(() -> {
                System.out.println("Thread Addded");
            });
        }
        catch(Exception e){
            System.out.println("No new exception is added : " + e);
        }
    }
}
