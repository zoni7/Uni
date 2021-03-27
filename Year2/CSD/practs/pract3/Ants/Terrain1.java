import java.util.concurrent.locks.*;
/**
 * Native monitor based Terrain
 * 
 * @author CSD Juansa Sendra
 * @version 2021
 */
public class Terrain1 implements Terrain {
    Viewer v;
    ReentrantLock lock;
    Condition queue;
    public  Terrain1 (int t, int ants, int movs) {
        lock = new ReentrantLock();
        queue = lock.newCondition();
        v=new Viewer(t,ants,movs,"0.- basic monitor");
        for (int i=0; i<ants; i++) new Ant(i,this,movs).start();
    }
    public void     hi      (int a) {
        try{
            lock.lock();
            v.hi(a); 
        } finally {lock.unlock();}
           
    }
    public void     bye     (int a) {         
        try{
            lock.lock();
            v.bye(a);
        } finally {lock.unlock();}  
    }
    public void     move    (int a) throws InterruptedException {
        try{
            lock.lock();
            v.turn(a); Pos dest=v.dest(a); 
            while (v.occupied(dest)) {queue.await(); v.retry(a);}
            v.go(a); queue.signal();
        } finally {lock.unlock();}
        
    }
}