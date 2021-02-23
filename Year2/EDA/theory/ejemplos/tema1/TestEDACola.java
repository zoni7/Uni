package ejemplos.tema1;

import librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.lineales.ArrayCola;


/** Clase que prueba la jerarquia Cola */

public class TestEDACola {
    public static void main(String[] args) {
        // we can not use new Cola<Integer> as the dynamic type because Cola is an interface
        // Static type q is Cola<Integer>
        // Dynamic type q is ArrayCola<Integer>
        Cola<Integer> q = new ArrayCola<Integer>(); // new ArrayCola() can be used
                                                            // but can cause errors
                                                            
        int tallaQ = 0; //Modification 1
        
        System.out.println("Creada una Cola con " + tallaQ //Modification 2
                           + " Integer, q = " + q.toString());
                           
        q.encolar(new Integer(10)); tallaQ++; //Modification 3
        q.encolar(new Integer(20)); tallaQ++; //Modification 4
        q.encolar(new Integer(30)); tallaQ++; //Modification 5
        System.out.println("La Cola de Integer actual es q = " + q.toString());
        System.out.println("Usando otros metodos para mostrar sus Datos... ");
        String datosQ = "";
        while (!q.esVacia()) {
            Integer primero = q.primero();
            if (primero.equals(q.desencolar())) { datosQ += primero + " "; }
            else { datosQ += "ERROR "; }
            tallaQ--; //Modification 6
        }
        System.out.println(" el mismo, " + datosQ 
                           + ", PERO q se vacia, q = " + q.toString());
    }
}