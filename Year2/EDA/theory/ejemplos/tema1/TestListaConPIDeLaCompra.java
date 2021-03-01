package ejemplos.tema1;

import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

public class TestListaConPIDeLaCompra {
    public static void main (String[] args) {
     
        ListaConPI<String> l = new LEGListaConPI<String>();
        
        /*l.inicio();*/
        l.insertar("perejil"/*, l.talla()*/); // Modification exercise 6
        l.insertar("patatas"/*, l.talla()*/);
        l.insertar("cerezas"/*, l.talla()*/);
        l.insertar("leche"/*, l.talla()*/);
        System.out.println("Mi lista de la compra es:\n" + l.toString());
        
        System.out.println("He olvidado apuntar el perejil?");
        // Busqueda secuencial de "perejil" en la lista tal que 
        // si no esta se apunta al final de esta
        l.inicio(); String e = "perejil";
        while (!l.esFin() && !l.recuperar(/*i*/).equals(e)) { l.siguiente(); }
        if (l.esFin()) {
            l.inicio(); // modification exercise 5
            l.insertar(e/*, l.talla()*/);
            System.out.println("Pues si. Lo apunto al inicio y arreglado:\n"
                               + l.toString());
        }
        else { System.out.println("Pues no. Ya esta en la lista!"); 
             // Modification exercise 6
             l.eliminar();
             while(!l.esFin() && !l.recuperar(/*i*/).equals("cerezas")) { l.siguiente(); }
             l.insertar(e);
             System.out.println("Voy a apuntarlo antes de las cerezas: \n" + l.toString()); 
        }
    }
}