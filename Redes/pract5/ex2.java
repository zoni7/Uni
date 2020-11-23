import java.util.Scanner;
import java.io.*;
import java.net.*;
/**
 * Write a description of class Echo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ex2
{
    public static void main(String args[]) {
        try {
            ServerSocket ss = new ServerSocket(7777);
            while(true) {
                Socket s = ss.accept();
                Scanner receive= new Scanner(s.getInputStream());
                PrintWriter send = new PrintWriter(s.getOutputStream());
                send.printf(receive.nextLine() + "\n" + "A client has been" +
                "connected to the server");
                send.printf("Local IP: " + s.getLocalAddress() +
                            "\n IP: " + s.getInetAddress() +
                            "\n Local Port: " + s.getLocalPort() +
                            "\n Port: " + s.getPort()
                            );
                send.flush();
                
                s.close();
            }
        } catch(IOException e) { System.out.println(e); }
        
    }
}
