// CSD feb 2015 Juansa Sendra

public class Pool2 extends Pool{ //max kids/instructor
    private int numInt = 0;
    private int numKids = 0;

    public void init(int ki, int cap)           {}
    public synchronized void kidSwims() throws InterruptedException {
        while(numInt == 0 || numKids >= (2 * numInt)) { //no kids alone && max kids/instructor
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
        while((numKids > 0 && numInt == 1) || (numKids >= (2 * (numInt -1)) && numKids != 0)) { //no kids alone && max kids/instructor
            log.waitingToRest();
            wait();
        }
        numInt--; // update the state
        log.resting();
        
        
    }
}
