package ex2;

import java.util.Scanner;
import java.io.*;
import java.net.*;
/**
 * Write a description of class Service here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Service extends Thread
{
    Socket client;
    
    public Service(Socket s) {
        client = s;
    }
    
    public void run() {
        try {
               String leido;
               Scanner read= new Scanner(client.getInputStream());
               PrintWriter pw = new PrintWriter(client.getOutputStream());
               leido = read.nextLine();
               while (!leido.equalsIgnoreCase("FIN")) {
                   // imprimir en el client
                   System.out.println(leido);
                   // imprimir en el serer
                   pw.println(leido);
                   pw.flush();
                   // preguntar una altra vegada
                   leido = read.nextLine();
               }
               // Tanca el socket quan es pose "FIN"
               client.close();

               
            } catch(IOException e) {
                System.out.println("No connection possible");
            }
    }
}
