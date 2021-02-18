public class CalculateResults extends Thread{
    private String result = "Not calculated";
    public void run(){
        result = calculate();
    }
    private String calculate(){
        // Performs a long-time calculation
        try {Thread.sleep(10000);
        } catch(InterruptedException e){};
        System.out.println("Agent thread finishes its calculation");
        return "Calculation done";
    }
    public String getResults(){
        return result; 
    }
}
