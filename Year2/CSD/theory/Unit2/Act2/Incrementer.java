public class Incrementer extends Thread
{ private Counter c;
 private int myname;
 private int cycles;
 public Incrementer(Counter count,
 int name, int quantity)
 { c = count;
 myname = name;
 cycles=quantity;
 }

 public void run()
 { for (int i = 0; i < cycles; i++)
 { c.increment();
 try
 { sleep((int)(Math.random() * 10));
 } catch (InterruptedException e){ }
 }
 System.out.println("Incrementer #" + myname+"has done "+cycles+" increments.");
 }
}