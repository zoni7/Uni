package practica2;

/**
 * class WrapperClassesUse.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class WrapperClassesUse {        
    public static void main(String[] args) {            
        // Assignment of wrapper variables to elementary types 
        int i = new Integer(123456);
        double num = new Double(10.55);
        char caracter = new Character('o');
            
        // Writing elementary variables
        System.out.println("int i = " + i);
        System.out.println("double num = " + num);
        System.out.println("char caracter " + caracter);      
               
        // Assignment of elementary values to wrapper variables
        Integer eI = 123456; 
        Double eNum = 10.55;
        Character eCaracter = 'o';  
               
            
        // Writing wrapper variables
        System.out.println("Integer I = " + eI);
        System.out.println("Double eNum = " + eNum);
        System.out.println("Character eCaracter = " + eCaracter);
        // TO COMPLETE ...
    }    
}