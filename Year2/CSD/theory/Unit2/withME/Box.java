public class Box {
    private int content = 0;
    private boolean full = false;

    public synchronized int get() {
        // Waits until the buffer is not empty
        while (content == 0) {
            try {
                wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int value = content;
        content = 0;
        full = false;
        return value;
    }

    public synchronized void put(int value) {
        // Waits until the buffer is not full 
        while (full) {
            try {
                wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        full = true;
        content = value; 
        
    }
}