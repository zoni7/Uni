class ProdCons {
    static BoundedBuffer buf=
    new BoundedBuffer(1);
   
   public static void main(String[]
   args){
    new Thread(new Runnable() {
        public void run() {
            for (int i=0; i<10; i++) {
                buf.put(i);
                System.out.println("Puts " + i);
            }
        }
    },"producer").start();
   
    new Thread(new Runnable() {
        public void run() {
            for (int i=0; i<10; i++) {
                System.out.println(buf.get());
            }
        }
    },"consumer").start();
   }
   }