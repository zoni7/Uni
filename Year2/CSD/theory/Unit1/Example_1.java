class Example_1 {
    public static void main(String[] args) {
        CalculateResults agent = new CalculateResults();
        agent.start();
        // It does something during the calculation process
        System.out.println("Main in execution");

        while(agent.isAlive()){
            Thread.yield(); 
        }
        // Employs the result
        System.out.println(agent.getResults());
    }
}