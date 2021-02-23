// CSD feb 2015 Juansa Sendra

public class Pool3 extends Pool{ //max capacity
    private int numInt = 0;
    private int numKids = 0;

    private int ni = 7;
    private int nk = 3;
    private int op = (int) Math.round(ni/nk);
    private int max = (ni + nk) / 2;

    public void init(int ki, int cap)           {}
    public synchronized void kidSwims() throws InterruptedException {
        while((numInt == 0 || numKids >= (op * numInt)) || (numInt + numKids) >= max ) { //no kids alone && max kids/instructor
            log.waitingToSwim();
            wait();
        }
        numKids++; // update the state
        log.swimming();
        
    }
    public synchronized void kidRests()  throws InterruptedException  {
        numKids--; // update the state
        notifyAll(); // notify instructorRests(), instructorSwims() and kidSwim()
        log.resting();
         
    
    }
    public synchronized void instructorSwims() throws InterruptedException  {
        while ((numInt + numKids) >= max) {
            log.waitingToSwim();
            wait();
        }
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
        notifyAll(); // notify instructorSwims() and kidSwims()
        log.resting();
        
        
    }
}
