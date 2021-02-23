// CSD feb 2015 Juansa Sendra
import java.lang.Math;

public class Pool2 extends Pool{ //max kids/instructor
    private int numInt = 0;
    private int numKids = 0;

    private int ni = 7;
    private int nk = 3;
    private int op = (int) Math.round(ni/nk);

    public void init(int ki, int cap)           {}
    public synchronized void kidSwims() throws InterruptedException {
        while(numInt == 0 || numKids >= (op * numInt)) { //no kids alone && max kids/instructor
            log.waitingToSwim();
            wait();
        }
        numKids++; // update the state
        log.swimming();
        
    }
    public synchronized void kidRests()  throws InterruptedException  {
        numKids--; // update the state
        notifyAll(); // notify instructorRests()
        log.resting();
         
    
    }
    public synchronized void instructorSwims() throws InterruptedException  {
        numInt++;  // update the state
        notifyAll(); // notify kidSwims()
        log.swimming();   
        
    }
    public synchronized void instructorRests() throws InterruptedException  {
        while((numKids > 0 && numInt == 1) || (numKids >= (op * (numInt -1)) && numKids != 0)) { //no kids alone && max kids/instructor
            log.waitingToRest();
            wait();
        }
        numInt--; // update the state
        log.resting();
        
        
    }
}
