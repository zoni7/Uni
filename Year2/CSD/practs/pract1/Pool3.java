// CSD feb 2015 Juansa Sendra

public class Pool3 extends Pool{ //max capacity
    private int numInt = 0;
    private int numKids = 0;

    private int ki;
    private int cap;

    public void init(int ki, int cap)  {this.ki = ki; this.cap = cap;}
    public synchronized void kidSwims() throws InterruptedException {
        while((numInt == 0 || numKids >= (ki * numInt)) || (numInt + numKids) >= cap ) { //no kids alone, max kids/instructor, max capacity 
            log.waitingToSwim();
            wait();
        }
        numKids++; // update the state
        //notifyAll(); // notify other kids waiting to swim
        log.swimming();
        
    }
    public synchronized void kidRests()  throws InterruptedException  {
        numKids--; // update the state
        notifyAll(); // notify instructorRests(), instructorSwims() and kidSwim()
        log.resting();
         
    
    }
    public synchronized void instructorSwims() throws InterruptedException  {
        while ((numInt + numKids) >= cap) { // max capacity
            log.waitingToSwim();
            wait();
        }
        numInt++;  // update the state
        notifyAll(); // notify kidSwims()
        log.swimming();   
        
    }
    public synchronized void instructorRests() throws InterruptedException  {
        while((numKids > 0 && numInt == 1) || (numKids >= (ki * (numInt -1)) && numKids != 0)) { //no kids alone && max kids/instructor
            log.waitingToRest();
            wait();
        }
        numInt--; // update the state
        notifyAll(); // notify instructorSwims() and kidSwims()
        log.resting();
        
        
    }
}
