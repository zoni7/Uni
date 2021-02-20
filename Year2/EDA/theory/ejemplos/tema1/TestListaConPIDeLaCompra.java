package ejemplos.tema1;

import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

public class TestListaConPIDeLaCompra {
    public static void main (String[] args) {
     
        ListaConPI<String> l = new LEGListaConPI<String>();
        
        /*l.inicio();*/

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
            l.insertar(e/*, l.talla()*/);
            System.out.println("Pues si. Lo apunto al final y arreglado:\n"
                               + l.toString());
        }
        else { System.out.println("Pues no. Ya esta en la lista!"); }
    }
}