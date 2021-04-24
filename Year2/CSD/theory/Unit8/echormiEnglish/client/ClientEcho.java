import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

class ClientEcho {
    static public void main (String args[]) {
        if (args.length<2) {
            System.err.println("Usage: ClientEcho hostRegistry numPortRegistry arguments...");
            return;
        }

       
        try {
            System.out.println("client starts");
            Registry reg=LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
            ServiceEcho srv = (ServiceEcho) reg.lookup("Echo");
    
            for (int i=2; i<args.length; i++)
                System.out.println(srv.echo(args[i]));
        }
        catch (RemoteException e) {
            System.err.println("Communication Error: " + e.toString());
        }
        catch (Exception e) {
            System.err.println("Exception in ClientEcho:");
            e.printStackTrace();
        }
    }
}
