import java.util.concurrent.Semaphore;

public	class	Main	{
    public	static	void	main(String[]	args)		{		
        Buffer	c	=	new	Buffer();
        Semaphore sem = new Semaphore(0);
        Consumer	c1	=	new	Consumer(c,	1, sem);
        Producer	p1	=	new	Producer(c,	2, sem);	
       					
        c1.start();
        p1.start();
        try {
            sem.acquire(); 
            sem.acquire(); 
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        System.out.println("Producer and " + "Consumer have terminated.");
    }
}
