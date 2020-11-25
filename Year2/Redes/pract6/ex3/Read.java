package ex3;
import java.util.Scanner;
import java.io.*;
import java.net.*;

/**
 * Write a description of class Write here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Read extends Thread
{
    Socket client;
    
    public Read(Socket s) {
        client = s;        
    }
    
    public void run() {
        try {
            String leido;
            Scanner read= new Scanner(client.getInputStream());
            
            while(read.hasNext()) {
                leido = read.nextLine();
                System.out.println(leido);
            }
            //client.close();
        } catch(IOException e) {
                System.out.println("No connection possible");
        }
    }
}
