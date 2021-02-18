public class ThreadName extends Thread {

    
    public static void printMsg() {
        System.out.println ("name=" +
        Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        for ( int i = 0; i < 10; i++ ) {
            Thread tt= new Thread() {
                public void run() {
                    for (int i = 0; i < 3; i++) {
            
                        printMsg();
                    }
                }
            };
            tt.setName("MyThread" + i);
            if (i<5) tt.start(); 
        }
    }
}