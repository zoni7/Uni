public class SimpleThreads {
    // Display a message, preceded by the name of the current thread
    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s:%s%n",threadName, message);
    }
    private static class MessageLoop implements Runnable {
        public void run() {
        String importantInfo[] = {"One","Two","Three","Four"};
        try {
            for (int i = 0; i < importantInfo.length; i++) {
                Thread.sleep(4000); // Pause for 4 seconds
                threadMessage(importantInfo[i]); // Print a message
            }
        }catch(InterruptedException e) {threadMessage("I wasn't done!");}
        } 
    }
    public static void main(String args[]) throws InterruptedException {
        // Delay, in milliseconds before we interrupt MessageLoop thread
        long patience = 1000 * 60; // (default one minute)
        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();
        threadMessage("Waiting for MessageLoop thread to finish");
        // loop until MessageLoop thread exits
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            //Wait maximum of 1 second for MessageLoop thread to finish.
            t.join(1000);
            if (((System.currentTimeMillis()-startTime)>patience)&& t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                // Shouldn't be long now --- wait indefinitely
                t.join();
            } 
         }
        threadMessage("Finally!");
    } 
} 
