import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Write a description of class TCPCient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SMTPClient
{
      public static void main(String args[]) {
           try {
               int port = 25;
               String server = "smtp.upv.es";
               Socket s = new Socket(server,port);
               Scanner read= new Scanner(s.getInputStream());
               // separate the characters
               System.setProperty("line.separator", "\r\n");
               PrintWriter pw = new PrintWriter(s.getOutputStream());
               // sending to the host
               pw.println("HELO upv.es");             
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
