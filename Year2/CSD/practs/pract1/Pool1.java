import javax.print.attribute.standard.NumberOfInterveningJobs;

// CSD feb 2015 Juansa Sendra

public class Pool1 extends Pool {   //no kids alone
    private int numInt = 0;
    private int numKids = 0;

    private int ki;
    private int cap;

    public void init(int ki, int cap)  {this.ki = ki; this.cap = cap; }
    public synchronized void kidSwims() throws InterruptedException {
        while(numInt == 0) { //no kids alone
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
        numInt++; // update the state
        notifyAll(); // notify kidSwims(), instructorRests()
        log.swimming();   
         
        
    }
    public synchronized void instructorRests() throws InterruptedException  {
        while(numKids > 0 && numInt == 1) { //no kids alone
            log.waitingToRest();
            wait();
        }
        numInt--; // update the state
        log.resting();
        
        
    }
}
