import java.util.concurrent.Semaphore;

public	class	Consumer	extends	Thread	{
    private	Buffer	b;
    private	int	number;							
    private	Semaphore	sem;
    public	Consumer(Buffer	ca,	int	id,Semaphore sem)		{
        b = ca;
        number	=	id;
        this.sem = sem;
    }
    public	void	run() {
        int	value	=	0;
        for	(int	i	=	1;	i	<	101;	i++)	{
            value	=	b.get();
            System.out.println("Consumer #"	+	number	+
            " gets: "	+	value);
                    
        }
        sem.release();
    }
}
