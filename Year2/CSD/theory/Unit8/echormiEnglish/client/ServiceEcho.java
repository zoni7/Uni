
import java.rmi.*;

interface ServiceEcho extends Remote {
	String echo (String s) throws RemoteException;
}
