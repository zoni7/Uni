import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
  String greetings() throws RemoteException;
}
