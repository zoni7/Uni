package ex2;
import java.util.Scanner;
import java.io.*;
import java.net.*;

/**
 * Write a description of class EchoConcurrent here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EchoConcurrent
{
    public static void main(String argv[]) throws
                        UnknownHostException, IOException {
        int port=7777; //wellknown port of the server
        ServerSocket server=new ServerSocket(port);
        while (true) {
            Socket client=server.accept(); // waiting for a client
            // To serve a client request, it creates a Service(Socket s)object
            // passing the socket “client” as a parameter in the constructor
            Service Cl=new Service(client);
            // and start the thread that give service to the client in parallel
            Cl.start();
            } // While End
        } // Main End
} // ConcurrentServer End 

