import java.net.*;
import java.io.*;

/**
 * Write a description of class TCPCient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TCPClient
{
      public static void main(String args[]) throws UnknownHostException
      , IOException {
      
           int port = 80;
           String server = "www.upv.es";
           Socket s = new Socket(server,port);
           System.out.println("connected");
           s.close();
      }
}
