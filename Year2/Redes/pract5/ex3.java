import java.util.Scanner;
import java.io.*;
import java.net.*;
/**
 * Write a description of class Echo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ex3
{
    public static void main(String args[]) {
        try {
            ServerSocket ss = new ServerSocket(8000);
            while(true) {
                Socket s = ss.accept();
                Scanner receive= new Scanner(s.getInputStream());
                System.setProperty("line.separator", "\r\n");
                PrintWriter send = new PrintWriter(s.getOutputStream(), true);
                
                send.println("HTTP/1.0 200 OK");
                send.println("Content-Type: text/plain" );
                send.println("\r\n");
                
                
               while(receive.hasNext()) {
                     send.println(receive.nextLine());
                }
               
                s.close();
               
            }
        } catch(IOException e) { System.out.println(e); }
        
    }
}
