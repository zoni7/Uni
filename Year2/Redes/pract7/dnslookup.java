import java.util.Scanner;
import java.net.InetAddress;
import java.io.*;
import java.net.UnknownHostException;

/**
 * Write a description of class dnslookup here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class dnslookup
{
    public static void main(String args[]) {
        
        try {
            InetAddress ipServer = InetAddress.getByName(args[0]);
            System.out.println(ipServer.toString());
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
    } 
}
