package ejemplos.tema2; 

import java.awt.Point; // para usar a la clase Point en buscarEnMatriz

/** Ejercicios sobre Búsqueda Binaria en arrays
 *  @author EDA
 *  @version Septiembre 2020
 */

public class BusquedaBinaria1y2D {
    
    /** SII m es un matriz cuadrada ordenada Asc. por filas y sin repetidos: 
     *  obtiene con coste logaritmico la posicion del elemento e en m
     *  (busqueda con garantia de exito, por simplificar)
     */
    public static <E extends Comparable<E>> Point buscarEnMatriz(E[][] m, E e) {
        return buscarEnMatriz(m, e, 0, m[0].length - 1, m[0].length); 
    }
    
    // Busqueda en dos dimensiones: Busqueda binaria de e sobre una  
    // matriz ordenada, con garantia de exito por simplificar
    // F = m[0].length AND x = fFin-fIni+1 
    // AND 0 <= fIni < F AND 0 <= fFin < F
    private static <E extends Comparable<E>> Point  buscarEnMatriz(E[][] m, 
        E e, int  fIni, int  fFin, int f) {
        // Paso 1: busqueda binaria de la fila donde se encuentra e
        int fMedia = (fIni + fFin) / 2;
        if (m[fMedia][0].compareTo(e) > 0) { 
            return buscarEnMatriz(m, e, fIni, fMedia - 1, f); 
        }
        else { 
            if (m[fMedia][f - 1].compareTo(e) < 0) { 
                return buscarEnMatriz(m, e, fMedia + 1, fFin, f);
            }
            else { 
                // Paso 2: fMedia es la fila donde esta e ... 
                //         En que columna cRes?
                // Busqueda Binaria con Garantia de exito de cRes en fMedia
                int cRes = buscarBin(m[fMedia], e);
                return new Point(fMedia, cRes);
            }       
        }
    }
    
    /** SII v es un array ordenado Asc.: obtiene la posicion de la primera  
     *  aparicion de e en v; si e no esta en v devuelve -1 para advertirlo.
     */
    public static <E extends Comparable<E>> int buscarBin(E[] v, E e) {
        return buscarBin(v, e, 0, v.length - 1);
    }
    //Busqueda en una dimension: Busqueda Binaria de e en un array ordenado
    private static <E extends Comparable<E>> int buscarBin(E[] v, E e, 
        int  izq , int  der) {
        if (izq > der) { return -1; }
        else {
            int mitad = (izq + der) / 2;
            int comp = v[mitad].compareTo(e);
            if (comp == 0) { return mitad; }
            else {
                if (comp < 0) { return buscarBin(v, e, mitad + 1, der); }
                else          { return buscarBin(v, e, izq, mitad - 1); }
            }
        }
    } 
}
