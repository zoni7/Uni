import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

class ImplHello extends UnicastRemoteObject implements Hello {
  ImplHello() throws RemoteException {
    super();
  }
  
  public String greetings() throws RemoteException {
    System.out.println("Remote method greetings() called"); 
    return "Hello World";
  }      
}
