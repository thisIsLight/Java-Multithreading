class OddEven{
    public int current = 1;

    public void PrintOdd(){
        
        synchronized(this){
            while(current%2 == 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(current);
            current++;
            notify();
        }
    }

    public void PrintEven(){
        synchronized(this){
            while(current%2 != 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(current);
            current++;
            notify();
        }
    }
}

public class WaitNotify {
    public static void main(String args[]){

        OddEven helper = new OddEven();

        Thread t1 = new Thread(() -> {
            while(helper.current < 101){
                helper.PrintOdd();
            }
        });
        Thread t2 = new Thread(() -> {
            while(helper.current < 101){
                helper.PrintEven();
            }
        });

        t1.start();
        t2.start();

    }
}
