import java.util.concurrent.locks.*;
import java.util.concurrent.TimeUnit;
/**
 * Native monitor based Terrain
 * 
 * @author CSD Juansa Sendra
 * @version 2021
 */
public class Terrain3 implements Terrain {
    Viewer v;
    ReentrantLock lock;
    Condition[][] queue;
    
    /**
     * Terrain3 constructor, initializing the lock and all queues
     */
    public  Terrain3 (int t, int ants, int movs) {
        lock = new ReentrantLock();
        v=new Viewer(t,ants,movs,"0.- basic monitor");
        queue = new Condition[v.getHeight()][v.getWidth()];
        for (int i =0; i < v.getHeight(); i++) {
            for (int j = 0; j < v.getWidth(); j++) {
                queue[i][j] = lock.newCondition();
            }
        }
        for (int i=0; i<ants; i++) new Ant(i,this,movs).start();
    }

    /**
     *  The ant is created
     */
    public void     hi      (int a) {
        try{
            lock.lock();
            v.hi(a); 
        } finally {lock.unlock();}
           
    }

    /**
     *  The ant is deleted and leving the current possition free
     */
    public void     bye     (int a) {         
        try{
            // Before leaving the ant notifies if someone is  waiting for its cell
            lock.lock();
            int x = v.getPos(a).x;
            int y = v.getPos(a).y;
            queue[x][y].signal();
            v.bye(a);
        } finally {lock.unlock();}  
    }

    /**
     *  The ant move to the next possition leving the current one free
     * 
     *  Preventing deadlocks: Prevention is used and hold and wait is the
     *  Coffman condition which is broken here. So we are breacking cyrcularity as well
     */
    public void     move    (int a) throws InterruptedException {
        
        try{
            lock.lock();
            int x = v.getPos(a).x;
            int y = v.getPos(a).y;
            boolean wait = true;
            v.turn(a);            
            Pos dest = v.dest(a);
            while (v.occupied(dest) && wait == true) {
                wait = queue[dest.x][dest.y].await(300, TimeUnit.MILLISECONDS); 
                v.retry(a);
            }
            // Wait gets false when the timeout period expired
            if (wait == false) {v.chgDir(a);}
            v.go(a); queue[x][y].signal();
        } finally {lock.unlock();}
        
    }
}
