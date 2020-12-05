import java.net.*;
import java.io.*;
/**
 * Write a description of class ClienteUDP here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ex2
{
    public static void main(String[] args) throws IOException {
        DatagramSocket s = new DatagramSocket();
        int p = s.getLocalPort(); 
        System.out.println(p);
        s.close();
  }
}

