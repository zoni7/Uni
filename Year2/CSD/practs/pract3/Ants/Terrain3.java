import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
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
            int x = v.getPos(a).x;
            int y = v.getPos(a).y;
            boolean wait = true;
            v.turn(a);            
            Pos dest = v.dest(a);
            while (v.occupied(dest) && wait == true) {
                wait = queue[dest.x][dest.y].await(300, TimeUnit.MILLISECONDS); 
                v.retry(a);
            }
            if (wait == false) {v.chgDir(a);}
            v.go(a); queue[x][y].signal();
        } finally {lock.unlock();}
        
    }
}