import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Write a description of class TCPCient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DayTimeClient
{
      public static void main(String args[]) {
           try {
               int port = 13;
               String server = "zoltar.redes.upv.es";
               Socket s = new Socket(server,port);
               Scanner read= new Scanner(s.getInputStream());
               System.out.println(read.nextLine());
               
               s.close();
            } catch(UnknownHostException e) {
                System.out.println("Unknown server name");
            } catch(IOException e) {
                System.out.println("No connection possible");
            }
      }
}
