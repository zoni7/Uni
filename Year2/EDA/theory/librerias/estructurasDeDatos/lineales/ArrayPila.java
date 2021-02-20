package librerias.estructurasDeDatos.lineales;

import  librerias.estructurasDeDatos.modelos.Pila; 

/** Implementacion de Pila sobre un array
 *  @param <E>, el tipo de los datos de la Pila
 */

public class ArrayPila<E> implements Pila<E> {  
    
    protected static final int CAPACIDAD_POR_DEFECTO = 50;
    protected E[] elArray;
    protected int tope;      

    /** crea una Pila vacia */
    @SuppressWarnings("unchecked")
    public ArrayPila() {
        /*COMPLETAR*/;
        tope = -1;
    }
      
    /** inserta el Elemento e en una Pila, o lo situa en su tope */
    public void apilar(E e) {
        /*COMPLETAR*/
    }
    // duplica el tamagno actual de un array
    @SuppressWarnings("unchecked")
    protected void duplicarArray() {
        E[] nuevoArray = (E[]) new Object[elArray.length * 2];
        System.arraycopy(elArray, 0, nuevoArray, 0, tope);
        elArray = nuevoArray;
    }  
      
    /** SII !esVacia()
     *  devuelve y elimina de una Pila el Elemento que ocupa su tope */
    public E desapilar() { 
        /*COMPLETAR Y CORREGIR*/
        return null;
    }
      
    /** SII !esVacia()
     *  devuelve el Elemento que ocupa el tope de una Pila */
    public E tope() { 
        /*CORREGIR*/
        return null;
    }
      
    /** comprueba si una Pila esta vacia */
    public boolean esVacia() {
        /*CORREGIR*/
        return false;
    }
        
    /** devuelve un String con los datos de una Pila en orden LIFO, inverso 
     *  al de insercion, y con el formato que se usa en el estandar de Java
     *  Por ejemplo, si se tiene una Pila con los Integer del 1 al 4, en
     *  orden LIFO, toString devuelve [4, 3, 2, 1];
     *  si la Pila esta vacia, entonces devuelve [] 
     */
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("["); 
        /*COMPLETAR*/
        return res.toString();
    }
}
