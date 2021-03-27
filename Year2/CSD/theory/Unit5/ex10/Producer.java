import java.util.concurrent.Semaphore;

public	class	Producer	extends	Thread	{
    private	Buffer	b;
    private	int	number;		
    private	Semaphore	sem;					
    public	Producer(Buffer	ca,	int	id, Semaphore sem)	{
                        b	=	ca;
                        number	=	id;
                        this.sem = sem;
    }
    public	void	run()		{
        for	(int	i	=	1;	i	<	101;	i++)		{
            b.put(i);
            System.out.println("Producer #"	+	number + " puts: "	+	i);
            
        }
        
    }
}
