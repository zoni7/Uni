package ex1;


/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class main
{
    public static void main (String args[])
    {
        for(int i=0;i<3;i++) {
            Hilo h = new Hilo(i);
            h.start();
        }
    
    }
}
