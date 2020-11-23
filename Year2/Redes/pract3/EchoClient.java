import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Write a description of class TCPCient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EchoClient
{
      public static void main(String args[]) {
           try {
               int port = 7;
               String server = "zoltar.redes.upv.es";
               Socket s = new Socket(server,port);
               Scanner read= new Scanner(s.getInputStream());
               PrintWriter pw = new PrintWriter(s.getOutputStream());
               // sending to the host
               pw.println("Hello world \n ");             
               pw.flush();
               // reading from the host
               System.out.println(read.nextLine());
              
               
               s.close();
            } catch(UnknownHostException e) {
                System.out.println("Unknown server name");
            } catch(IOException e) {
                System.out.println("No connection possible");
            }
      }
}
