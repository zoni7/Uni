import java.util.concurrent.locks.*;
/**
 * Native monitor based Terrain
 * 
 * @author CSD Juansa Sendra
 * @version 2021
 */
public class Terrain2 implements Terrain {
    Viewer v;
    ReentrantLock lock;
    Condition[][] queue;
    
    /**
     * Terrain2 constructor, initializing the lock and all queues
     */
    public  Terrain2 (int t, int ants, int movs) {
        lock = new ReentrantLock();
        v=new Viewer(t,ants,movs,"0.- basic monitor");
        queue = new Condition[v.getHeight()][v.getWidth()];
        // Create a condition for every cell
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
            lock.lock();
            // Before leaving the ant notifies if someone is  waiting for its cell
            int x = v.getPos(a).x;
            int y = v.getPos(a).y;
            queue[x][y].signal();
            v.bye(a);
        } finally {lock.unlock();}  
    }

    /**
     *  The ant move to the next possition leving the current one free
     */
    public void     move    (int a) throws InterruptedException {
        
        try{
            lock.lock();
            int x = v.getPos(a).x;
            int y = v.getPos(a).y;
            v.turn(a);            
            Pos dest = v.dest(a);
            while (v.occupied(dest)) {
                queue[dest.x][dest.y].await();
                 v.retry(a);
                }
            v.go(a); queue[x][y].signal();
        } finally {lock.unlock();}
        
    }
}