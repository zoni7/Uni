package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.Lista;

/** Implementacion enlazada de una Lista  
 *  @param <E>, el tipo de los datos de la Lista
 */

public class LEGLista<E> implements Lista<E> {

    // una LEG TIENE UN
    protected NodoLEG<E> primero;
    // una LEG TIENE UNA
    protected int talla;
    
    /** crea una Lista vacia, de talla cero */
    public LEGLista() {
        this.primero = null;
        this.talla = 0;
    }
    
    /** SII 0<=i<=talla() 
     *  inserta el elemento e en la posicion i de una Lista */
    public void insertar(E e, int i) {
        NodoLEG<E> nuevo = new NodoLEG<E>(e); talla++;
        NodoLEG<E> aux = primero, ant = null; 
        for (int j = 0; j < i; j++) {
            ant = aux; 
            aux = aux.siguiente;
        }   
        nuevo.siguiente = aux;
        if (ant == null) { primero = nuevo; }
        else { ant.siguiente = nuevo; }
    }
    
    /** SII talla()>0 AND 0<=i<talla() 
     *  elimina el elemento que ocupa la posicion i de una Lista */
    public void eliminar(int i) {
        talla--;
        NodoLEG<E> aux = primero, ant = null;
        for (int j = 0; j < i; j++) {
            ant = aux; 
            aux = aux.siguiente;
        }   
        if (ant == null) { primero = aux.siguiente; }
        else { ant.siguiente = aux.siguiente; }
    }
     
    /** SII talla()>0 AND 0<=i<talla()
     *  devuelve el elemento que ocupa la i-esima posicion de una Lista */
    public E recuperar(int i) {
        NodoLEG<E> aux; int j;
        for (aux = primero, j = 0; j < i; aux = aux.siguiente, j++) { ; } 
        return aux.dato;
    }
    
    /** comprueba si una Lista esta vacia **/
    public boolean esVacia() { 
        return primero == null; 
        //alternativamente: return talla == 0;
    }
    
    /** devuelve la talla de una Lista */
    public int talla() { return this.talla; }
    
    /** devuelve el String con los datos de una Lista en orden de insercion */
    public String toString() {
        String res = "";
        for (NodoLEG<E> aux = primero;  aux != null;  aux = aux.siguiente) {
            res += aux.dato.toString() + "\n";
        }
        return res;
    }
}