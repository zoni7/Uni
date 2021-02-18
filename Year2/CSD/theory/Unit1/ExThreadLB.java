public class ExThreadLB {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        for (int i=0; i<10; i++){
            new Thread(() -> System.out.println("executed by" + Thread.currentThread().getName()), "MyThread " + i).start();
        } 
    } 
}