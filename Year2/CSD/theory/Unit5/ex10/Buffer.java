import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public	class	Buffer	{
    private	int	store	=	0;
    private	boolean	full	=	false;
    ReentrantLock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition();
    Condition notFull = lock.newCondition();
    
    
    public	int	get() 		{
        lock.lock();
        try {
            while(!full) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            int	value	=	store;
            store	=	0;
            full	=	false;
            notEmpty.signal();
            return	value;
        } finally {lock.unlock();}
    }
    public	void	put(int	value) {
        lock.lock();
        try {
            while(full) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            full	=	true;
            store	=	value;
            notFull.signal();
        } finally {lock.unlock();}
        
    }
}