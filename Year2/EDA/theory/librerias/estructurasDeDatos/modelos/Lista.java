package librerias.estructurasDeDatos.modelos;

/** Coleccion de datos que se gestionan de forma secuencial, accediendo 
 *  uno tras otro, desde el primero al ultimo segun su orden de insercion
 *  @param <E>, tipo de los datos de la Lista
 *  @author EDA 
 *  @version Febrero 2016
 */

public interface Lista<E> {
    
// Metodos Modificadores del estado de la Lista:
    /** SII 0<=i<=talla(): 
     *  inserta el elemento e en la posicion i de una Lista
     *  @param E dato a insertar
     *  @param int, posicion de insercion */
    void insertar(E e, int i);
    
    /** SII 0<=i<=talla(): 
     *  elimina el elemento que ocupa la posicion i de una Lista
     *  @param int posicion de insercion */
    void eliminar(int i);
    
// Metodos Consultores del estado de la Lista:
    /** SII 0<=i<=talla(): 
     *  devuelve el elemento que ocupa la posicion i de una Lista
     *  @param int posicion
     *  @return E dato en posicion i */
    E recuperar(int i);
    
    /** Comprueba si una Lista esta vacia 
        @return boolean, true si esta vacia, false en caso contrario */
    boolean esVacia();
    
    /** Devuelve la talla de una Lista, o su numero de elementos
     *  @return int numero de elementos de la lista */
    int talla();
}