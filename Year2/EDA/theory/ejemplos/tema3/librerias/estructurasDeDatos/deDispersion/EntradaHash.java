package ejemplos.tema3.librerias.estructurasDeDatos.deDispersion;

/**
 * Representa un elemento de la Lista con PI que implementa   
 * una cubeta de una TablaHash, esto es una Entrada o par
 * (clave, valor)
 * 
 * @param <C>  el tipo de la clave
 * @param <V>  el tipo del valor asociado a una clave
 * 
 * @version (Curso 2015/16)
 */

class EntradaHash<C, V> {
    
    protected C clave;
    protected V valor;

    EntradaHash(C c, V v) {
        this.clave = c;
        this.valor = v;
    }
    public String toString() { return "(" + clave + ", " + valor + ")"; }
}