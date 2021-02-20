package librerias.estructurasDeDatos.modelos;

/** Cola extendida
 *  @param <E>, el tipo de los datos de la cola
 */
    
public interface ColaPlus<E> extends Cola<E> {
    
    /** Devuelve la talla de una Cola */
    int talla();
}
