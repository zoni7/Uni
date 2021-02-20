package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.ColaPlus;

/** Un ArrayColaPlus ES UN ArrayCola que implementa ColaPlus
 *  @param <E>, el tipo de los datos de la ColaPlus
 */

public class ArrayColaPlus<E> extends ArrayCola<E> implements ColaPlus<E> {
    
    /** devuelve la talla de una Cola */
    public final int talla() {
        // usando unicamente los metodos que hereda de ArrayCola
        int res = 0;
        while (!this.esVacia()) { 
            E primero = this.desencolar(); 
            res++; 
        }
        return res;
    }
}