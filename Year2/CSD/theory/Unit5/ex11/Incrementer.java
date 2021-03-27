import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Incrementer extends Thread 
{
    private CountDownLatch barrier;
    private AtomicInteger c;
    private int myname; 
    private int cycles;
    public Incrementer(AtomicInteger count, 
    int name, int quantity, CountDownLatch barrier) 
    {
        this.barrier = barrier;
        c = count;
        myname = name;
        cycles=quantity;
    }
    public void run() 
        {
        for (int i = 1; i < cycles; i++) 
        {
            c.incrementAndGet();
            try 
        { 
        sleep((int)(Math.random() * 
        10)); } 
        catch (InterruptedException e) { }
        }
        System.out.println("Thread #" + 
        myname + " has done "+cycles+" increments ");
        barrier.countDown();
    }
}
