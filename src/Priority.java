public class Priority implements Runnable{
    int count; Thread thread; static boolean stop = false; static String currentName;

    Priority(String name){
        thread = new Thread (this, name);
        count = 0;
        currentName = name;
    }

    public void run(){
        System.out.println(thread.getName() + " starts to operate");
        do{ count++;
            if(currentName.compareTo(thread.getName()) != 0){
                currentName = thread.getName();
                System.out.println(currentName + " is executed");
            }
    } while (stop == false && count < 10_000_000);
        stop = true;
        System.out.println("\n" + thread.getName() + " finishes running");
}}
class PriorityDemo{
    public static void main(String[] args) {
        Priority mt1 = new Priority("high priority thread");
        Priority mt2 = new Priority("Thread with low priority");
        Priority mt3 = new Priority("Thread # 1 with normal priority");
        Priority mt4 = new Priority("Thread # 2 with normal priority");
        Priority mt5 = new Priority("Thread # 3 with normal priority");

        mt1.thread.setPriority(Thread.NORM_PRIORITY + 5);
        mt2.thread.setPriority(Thread.NORM_PRIORITY - 4);

        mt1.thread.start();mt2.thread.start();mt3.thread.start();mt4.thread.start();mt5.thread.start();
    try{
        mt1.thread.join();mt2.thread.join();mt3.thread.join();mt4.thread.join();mt5.thread.join();
    }catch (InterruptedException e){
        System.out.println("the main thread starts running");}
        System.out.println("\n High priority thread counted to " + mt1.count);
        System.out.println("The loe priority thread added to " + mt2.count);
        System.out.println("Thread # 1 with normal priority added to " + mt3.count);
        System.out.println("Thread # 2 with normal priority added to " + mt4.count);
        System.out.println("Thread # 3 with normal priority added to " + mt5.count);
    }
}
