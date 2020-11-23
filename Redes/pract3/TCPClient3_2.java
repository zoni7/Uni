import java.net.*;
import java.io.*;

/**
 * Write a description of class TCPCient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TCPClient3_2
{
      public static void main(String args[]) {
           try {
               int port = 80;
               String server = "www.upv.es";
               Socket s = new Socket(server,port);
               System.out.println("connected");
               // giving information
               System.out.println("Local IP: " + s.getLocalAddress().toString() +
                                    "\n Remote IP: " + s.getInetAddress().toString() +
                                    "\n Local port: " + s.getLocalPort() +
                                    "\n Remote port" + s.getPort() + "\n");
               
               s.close();
            } catch(UnknownHostException e) {
                System.out.println("Unknown server name");
            } catch(IOException e) {
                System.out.println("No connection possible");
            }
      }
}
