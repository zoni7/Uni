// CSD Mar 2013 Juansa Sendra

public class LimitedTable extends RegularTable { //max 4 in dinning-room
    int numPhilo = 0; // shared counter of Philos
    public LimitedTable(StateManager state) {super(state);}
    public synchronized void enter(int id) throws InterruptedException {
        while (numPhilo >= 4) { wait(); } //max 4 in dinning-room
        numPhilo++;
    }
    public synchronized void exit(int id) {
        numPhilo--;
        notifyAll(); // notifies enter()
    }
}
