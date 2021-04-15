package ejemplos.tema3.librerias.estructurasDeDatos.deDispersion;

import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

/**
 * TablaHash: implementacion SIN REHASHING de una Tabla Hash  
 * Enlazada en la que sus cubetas, o listas de colisiones, 
 * se representan mediante Listas con PI de EntradaHash<C, V>
 * 
 * @param <C>  tipo de las Claves del Map que implementa
 * @param <V>  tipo de Valores del Map que implementa
 *
 * @version (Curso 2018/19)
 */

public class TablaHash<C, V> implements Map<C, V> {
    
    // Una Tabla Hash TIENE:
    
    // UNA CTE JAVA que representa...
    /** El valor (float) del factor de carga de una Tabla Hash  
     *  (valor por defecto en la clase java.util.HashMap) */
    public static final double FACTOR_CARGA = 0.75;
    
    // UN array de Listas Con PI de EntradaHash<C, V> elArray:
    // - elArray[h] representa una cubeta, o lista de    
    //   colisiones asociadas al indice Hash h
    // - elArray[h] contiene la referencia a la Lista     
    //   Con PI donde se encuentran todas las Entradas  
    //   cuya Clave tiene un indice Hash h 
    protected ListaConPI<EntradaHash<C, V>>[] elArray;
    
    // UNA talla que representa el numero de Entradas  
    // almacenadas en una Tabla Hash o, equivalentemente, 
    // en sus cubetas
    protected int talla; 
    
    // UN metodo indiceHash que representa la funcion de 
    // Dispersion de la Tabla
    //**SIN ESTE METODO NO SE TIENE UNA TABLA HASH, SOLO UN ARRAY**
    // Devuelve el indice Hash de la Clave c de una Entrada, 
    // i.e. la posicion de la cubeta en la que la que se ubica  
    // la Entrada de Clave c
    protected int indiceHash(C c) {
        int indiceHash = c.hashCode() % this.elArray.length;
        if (indiceHash < 0) { indiceHash += this.elArray.length; }
        return indiceHash;
    }
    
    
    /** Crea una Tabla Hash vacia, con tallaMaximaEstimada  
     *  Entradas y factor de carga 0.75 */
    @SuppressWarnings("unchecked") 
    public TablaHash(int tallaMaximaEstimada) {
        int capacidad = (int) (tallaMaximaEstimada / FACTOR_CARGA);
        capacidad = siguientePrimo(capacidad);
        elArray = new LEGListaConPI[capacidad];
        for (int i = 0; i < elArray.length; i++) {
            elArray[i] = new LEGListaConPI<EntradaHash<C, V>>();
        }
        talla = 0;
    }
    // Devuelve un numero primo MAYOR o IGUAL a n, 
    // i.e. el primo que sigue a n
    public static final int siguientePrimo(int n) {
        int aux = n;
        if (aux % 2 == 0) { aux++; }
        for ( ; !esPrimo(aux); aux += 2) { ; }
        return aux;
    } 
    // Comprueba si n es un numero primo
    protected static final boolean esPrimo(int n) {
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) { return false; } // n NO es primo
        }
        return true; // n SI es primo
    }
    
    /** Devuelve el actual factor de carga de una Tabla Hash,   
     *  lo que equivale a la longitud media de sus cubetas en  
     *  una implemetacion Enlazada de la Tabla */
    public final double factorCarga() { 
        return (double) talla / elArray.length; 
    }
    
    /** Comprueba si una Tabla Hash esta vacia,  
     *  i.e. si tiene 0 Entradas */
    public boolean esVacio() { return talla == 0; }
    
    /** Devuelve la talla, o numero de Entradas, 
      * de una Tabla Hash */
    public int talla() { return talla; } 

    /** Devuelve una ListaConPI con las talla() claves  
     *  de una Tabla Hash */
    public ListaConPI<C> claves() {
        ListaConPI<C> res = new LEGListaConPI<C>();
        for (int i = 0; i < elArray.length; i++) {
            ListaConPI<EntradaHash<C, V>> l = elArray[i];
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                EntradaHash<C, V> e = l.recuperar();
                res.insertar(e.clave); 
            }
        }
        return res;
    }
    
    /** Devuelve un String con las Entradas de una Tabla Hash
     *  en un formato texto dado (ver toString de EntradaHash)
     */
    // RECUERDA: usa la clase StringBuilder por eficiencia
    public final String toString() {
        StringBuilder res = new StringBuilder();
        for (ListaConPI<EntradaHash<C, V>> l : elArray) {
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                res.append(l.recuperar() + "\n");
            }
        }   
        return res.toString(); 
    }
    
    /** Devuelve el valor de la entrada con Clave c de una 
     *  Tabla Hash, o null si tal entrada no esta en la Tabla */
    public V recuperar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> l = elArray[pos];
        V valor = null;        
        // Busqueda de la Entrada de Clave c en la cubeta l 
        l.inicio();
        while (!l.esFin() && !l.recuperar().clave.equals(c)) { 
            l.siguiente(); 
        }
        // Resolucion de la Busqueda: SII esta en la cubeta l,    
        // se recupera el valor de la Entrada de Clave c 
        if (!l.esFin()) {
            valor = l.recuperar().valor;
        }
        return valor;
    }
    
    /** Elimina la Entrada con Clave c de una Tabla Hash y 
     *  devuelve su valor asociado, o null si tal entrada 
     *  no esta en la Tabla */
    public V eliminar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> l = elArray[pos];
        V valor = null;      
        // Busqueda de la Entrada de Clave c en la cubeta l
        l.inicio();
        while (!l.esFin() && !l.recuperar().clave.equals(c)) { 
            l.siguiente(); 
        }
        // Resolucion de la Busqueda: SII esta en la cubeta     
        // l, se recupera el valor de la Entrada de Clave c  
        // y, luego, se elimina de l
        if (!l.esFin()) {
            valor = l.recuperar().valor;
            l.eliminar();
            talla--;
        }
        return valor;
    }
        
    /** Inserta la Entrada (c, v) en una Tabla Hash y   
     *  devuelve el antiguo valor asociado a c, o null 
     *  si tal entrada  no esta en la Tabla */
    // NO HACE REHASHING. En la Parte 2 de la Practica 3 se 
    // modificara este metodo para que efectue rehashing 
    // cuando, TRAS insertar una nueva Entrada en su 
    // correspondiente cubeta (ListaConPI) e incrementar la 
    // talla de la Tabla, factorDeCarga() > FACTOR_CARGA
    public V insertar(C c, V v) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C,V>> l = elArray[indiceHash(c)];
        V antiguoValor = null;
        // Busqueda de la Entrada de Clave c en la cubeta l
        l.inicio();
        while (!l.esFin() && !l.recuperar().clave.equals(c)) { 
            l.siguiente(); 
        }
        // Resolucion de la busqueda: si la Entrada (c, v) NO
        // esta en la Tabla, se inserta al final de la cubeta
        // l y se incrementa la talla de la Tabla; sino, si la 
        // Entrada ya esta en l, se actualiza su valor.
        if (l.esFin()) { 
            // Insercion efectiva de (c, v)
            l.insertar(new EntradaHash<C, V>(c, v));
            talla++;
            // if (factorCarga() > FACTOR_CARGA) { rehashing(); }
        }
        else { 
            // Recuperacion del valor actual de la Entrada de 
            // Clave C, para devolverlo, y actualizacion de 
            // dicho valor a v
            antiguoValor = l.recuperar().valor; 
            l.recuperar().valor = v;
        }
        return antiguoValor;
    }
}