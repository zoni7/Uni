// rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	
public class Server{
  public static void main (String[] args) { 
    try {  
        Registry reg = LocateRegistry.getRegistry();   //we asume by default registry port=1099   
        reg.rebind("objectHello", new ImplHello());      
        System.out.println("Server Hello prepared"); 
    } catch (Exception e) {
	    System.err.println("Server exception thrown: " + e.toString());
	    e.printStackTrace();
	}
  }      
}
