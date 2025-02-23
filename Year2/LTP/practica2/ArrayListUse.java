package practica2;

import java.util.*;
import java.io.*;

/**
 * class ArrayListUse.
 * 
 * @author LTP
 * @version 2020-21
 */

public class ArrayListUse {       
    public static void main(String[] args) {        
        if (args.length != 1) {
            System.err.print("You must specify an argument: filename");
            System.exit(0);
        }
        
        File fd = new File(args[0]);
        Scanner file = null;
        // Creating ArrayList object		
        // TO COMPLETE ...
        ArrayList<String> list = new ArrayList<String>();
        
        try {
            file = new Scanner(fd);
        } 
        catch (FileNotFoundException e) {
            System.err.println("File does not exists " + e.getMessage());
            System.exit(0);
        }
                
        // Reading file, adding lines to the list		
        while (file.hasNext()) {
            // TO COMPLETE ...
            list.add(file.nextLine());
        }
        file.close();
		
        // Sorting the list, writing it to console        
        // TO COMPLETE ...
        
        /* we have to writte Collections in order to call the static method
         * You can find information of how to implement the sort method here: 
         * https://www.geeksforgeeks.org/how-to-sort-an-arraylist-in-ascending-order-in-java/
         */
        
        Collections.sort(list);
        System.out.println(list.toString());
    }     
}