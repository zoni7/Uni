import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondition 
{
 public static void main(String[] args) 
 {
 CountDownLatch barrier = new CountDownLatch(2); 
 AtomicInteger c = new AtomicInteger(0);
 int loops=1000;
 System.out.println("Loops "+ loops );
 
 Incrementer inc = new Incrementer(c, 1, loops, barrier);
 Decrementer dec = new Decrementer(c, 2, loops, barrier); 
 inc.start();
 dec.start();

 // Wait for the end of the threads
 try {
    barrier.await();
} catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
} 
 System.out.println("Main Thread obtains: "+c.get() );
 }
}