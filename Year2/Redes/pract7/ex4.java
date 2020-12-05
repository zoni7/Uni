import java.net.*;
import java.io.*;
/**
 * Write a description of class ClienteUDP here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ex4
{
    public static void main(String[] args) throws IOException {
        DatagramSocket s = new DatagramSocket();
        
        // Creating the message
        String ms = new String("hello there, I am Obi-Wan Kenobi\n");
        // Creating the packet
        DatagramPacket dp = new DatagramPacket(ms.getBytes(),
            ms.getBytes().length, InetAddress.getByName("localhost"),7777);
        // sending the message
        s.send(dp);
        
        byte[] buffer = new byte[1000];
        DatagramPacket p = new DatagramPacket(buffer, 1000);
        s.receive(p);
        String sReceive = new String(p.getData(), 0, p.getLength());
        
        System.out.println(sReceive);
        s.close();
  }
}

