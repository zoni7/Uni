import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Crosswalk {
    private int c, c_waiting, p, p_waiting;
    private Condition OKcars, OKpedestrians;
    
    private ReentrantLock lock;
    
    public Crosswalk() {
        c = c_waiting = p = p_waiting = 0;
        lock = new ReentrantLock();
        OKcars = lock.newCondition();
        OKpedestrians = lock.newCondition();
    }
    public void enterC() {
        try{
            lock.lock();
            while (p >0){
            //cannot cross
                try {
                    OKcars.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            //can cross
            c++;
            //notify that cars can now cross
            OKcars.signal();
        } finally { lock.unlock(); }
    }
   public void leaveC() { 
        try {
            lock.lock();
            c--;
            //notify that cars can now cross
            OKcars.signal();
            
            //if needed, notify that pedestrians 
            //can now cross
            if (c == 0) OKpedestrians.signal();
        } finally {lock.unlock();}
    
   } 
   
   public void enterP() {
       try {
           lock.lock();
            while ((c>0)||(c_waiting >0)){
                //wait
                try {
                    OKpedestrians.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            p++;
            // notify that pedestrians can now cross
            OKpedestrians.signal();
        } finally {lock.unlock();}
    }
   public void leaveP() { 
       try {
           lock.lock();
            p--;
            //if needed, notify that cars can now cross
            if (p == 0) OKcars.signal();   
            //if needed, notify that pedestrians 
            //can now cross
            if (c_waiting == 0) OKpedestrians.signal();
       } finally {lock.unlock();}
   }
}