import java.util.concurrent.locks.*;
/**
 * Native monitor based Terrain
 * 
 * @author CSD Juansa Sendra
 * @version 2021
 */
public class Terrain1 implements Terrain { // using ReentrantLock with one condition
    Viewer v;
    ReentrantLock lock; // Lock used
    Condition queue; // Only one condition

    /**
     * Terrain1 constructor, initializing the lock and the only queue
     */
    public  Terrain1 (int t, int ants, int movs) {
        lock = new ReentrantLock(); // initialize lock
        queue = lock.newCondition();
        v=new Viewer(t,ants,movs,"0.- basic monitor");
        for (int i=0; i<ants; i++) new Ant(i,this,movs).start();
    }

    /**
     *  The ant is created
     */
    public void     hi      (int a) {
        // With Mutual Exclusion
        try{ 
            lock.lock();
            v.hi(a); 
        } finally {lock.unlock();}
           
    }

    /**
     *  The ant is deleted and leving the current possition free
     */
    public void     bye     (int a) {  
        // With Mutual Exclusion       
        try{
            lock.lock();
            // Before leaving the ant notifies if someone is  waiting for its cell
            queue.signalAll();
            v.bye(a);          
        } finally {lock.unlock();}  
    }

    /**
     *  The ant move to the next possition leving the current one free
     */
    public void     move    (int a) throws InterruptedException {
        // With Mutual Exclusion and conditional synchronization
        try{
            lock.lock();
            v.turn(a); Pos dest=v.dest(a); 
            while (v.occupied(dest)) {
                queue.await();
                v.retry(a);
            }
            v.go(a); queue.signalAll();
        } finally {lock.unlock();}
        
    }
}