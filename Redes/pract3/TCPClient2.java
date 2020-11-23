import java.net.*;
import java.io.*;

/**
 * Write a description of class TCPCient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TCPClient2
{
      public static void main(String args[]) {
           try {
               int port = 81;
               String server = "www.upv.es";
               Socket s = new Socket(server,port);
               System.out.println("connected again");
               s.close();
            } catch(UnknownHostException e) {
                System.out.println("Host no trobat");
            } catch(IOException e) {
                System.out.println("No va la coneccio");
            }
      }
}
