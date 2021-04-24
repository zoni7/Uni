 
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) {
        try {
            int port = 0;
            String host = "localhost";
            
            if (args.length == 2) {
                port = Integer.parseInt(args[1]);
            } 
            
            if (args.length >= 1) {
                host = args[0];
            }

            Registry reg = LocateRegistry.getRegistry(host, port);
            Hello h = (Hello) reg.lookup("objectHello");      
            System.out.println(h.greetings());
            
        } catch (Exception e) {
            System.err.println("Client exception thrown: " + e.toString());
            e.printStackTrace();
        }            
    }
}
