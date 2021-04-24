import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

class ServerEcho  {
    static public void main (String args[]) {
       if (args.length!=2) {
            System.err.println("Usage: ServerEcho hostRegister numPortRegister");
            return;
        }
       
        try {
         
            Registry reg=LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
            ServiceEchoImpl srv = new ServiceEchoImpl();
            reg.rebind("Echo",srv);
            System.out.println("Server running ");
        }
        catch (RemoteException e) {
            System.err.println("Communication Error : " + e.toString());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Exception in ServerEcho:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
