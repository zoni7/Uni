import java.net.*;
import java.io.*;
/**
 * Write a description of class ClienteUDP here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ex3
{
    public static void main(String[] args) throws IOException {
        DatagramSocket s = new DatagramSocket();
        int p = s.getLocalPort(); 
        // Creating the message
        String ms = new String("hello there, I am Obi-Wan Kenobi\n");
        // Creating the packet
        DatagramPacket dp = new DatagramPacket(ms.getBytes(),
            ms.getBytes().length, InetAddress.getByName("localhost"),7777);
        // sending the message
        s.send(dp);
        s.close();
  }
}

