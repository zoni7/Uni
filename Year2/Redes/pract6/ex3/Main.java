package ex3;

import java.util.Scanner;
import java.io.*;
import java.net.*;

/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    public static void main(String argv[])  {
        try {
            Socket client = new Socket("localhost",7777);
            Write w = new Write(client);
            w.start();
            Read r = new Read(client);
            r.start();
        } catch (UnknownHostException e) {
            System.out.println("Host desconectat");
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("No es pot conectar");
            System.out.println(e);
        }
        } // Main End
}
