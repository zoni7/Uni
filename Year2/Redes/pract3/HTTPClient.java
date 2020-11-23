import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Write a description of class TCPCient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HTTPClient
{
      public static void main(String args[]) {
           try {
               int port = 80;
               String server = "www.upv.es";
               Socket s = new Socket(server,port);
               Scanner read= new Scanner(s.getInputStream());
               System.out.println("connected");
              
               System.setProperty("line.separator", "\r\n");
               PrintWriter pw = new PrintWriter(s.getOutputStream());
               // sending to the host
               pw.println("GET / HTTP/1.0\r\n\r\n");             
               pw.flush();
               // reading from the host
               while (read.hasNext()) {
                   System.out.println(read.nextLine());
                }
               s.close();
            } catch(UnknownHostException e) {
                System.out.println("Unknown server name");
            } catch(IOException e) {
                System.out.println("No connection possible");
            }
      }
}
