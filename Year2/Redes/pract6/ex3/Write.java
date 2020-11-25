package ex3;
import java.util.Scanner;
import java.io.*;
import java.net.*;

/**
 * Write a description of class Read here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Write extends Thread
{
    Socket client;
    
    public Write(Socket s) {
        client = s;        
    }
    
    public void run() {
        try {
            String leido;
            Scanner read= new Scanner(System.in);
            PrintWriter pw = new PrintWriter(client.getOutputStream());
            leido = read.nextLine();
            while(!leido.equalsIgnoreCase("FIN")) {
                pw.println(leido);
                pw.flush();
            }
            // es tanca el client quant escrius
            client.close();
        } catch(IOException e) {
                System.out.println("No connection possible");
        }
    }
}
