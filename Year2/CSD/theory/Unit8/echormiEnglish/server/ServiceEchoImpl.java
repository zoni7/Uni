import java.rmi.*;
import java.rmi.server.*;

class ServiceEchoImpl extends UnicastRemoteObject implements ServiceEcho {
    ServiceEchoImpl() throws RemoteException {
    }
    public String echo(String s) throws RemoteException {
    	//System.out.println("Service echo has been called"); 
        return s.toUpperCase();
    }
}
