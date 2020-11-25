package ex1;

import java.util.Scanner;
import java.io.*;
import java.net.*;


/**
 * Write a description of class Hilo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hilo extends Thread
{
    private int id;    
    public Hilo(int id) {
        this.id = id;
    }
    
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.print(id + "\n");
            try {sleep(100);}
            catch(InterruptedException e) {}
        }
    }
   
}

