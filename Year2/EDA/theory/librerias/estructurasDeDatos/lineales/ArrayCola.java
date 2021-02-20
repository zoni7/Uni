package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.Cola;

/** Implementaci�n de Cola utilizando un array circular
 *  @param <E>, el tipo delos datos de la Cola
 */

public class ArrayCola<E> implements Cola<E> {
    
    protected static final int CAPACIDAD_POR_DEFECTO = 30000;
    protected E[] elArray;    
    protected int finalC, principioC, talla;
    
    public int talla() { return talla; }
   
    /** crea una Cola vacia */
    @SuppressWarnings("unchecked")
    public ArrayCola() {
        elArray = (E[]) new Object[CAPACIDAD_POR_DEFECTO];
        talla = 0; principioC = 0; finalC = 0;
    }
    
    /** inserta el Elemento e en una Cola, o lo situa en su final */
    //  SII no hay suficiente espacio en elArray se duplica 
    //  el tamagno de elArray circular
    public void encolar(E e) {
        if (talla == elArray.length) { duplicarArrayCircular(); }
        elArray[finalC] = e;
        finalC = incrementar(finalC); talla++;
    }
    // duplica el tamagno actual de un array circular 
    @SuppressWarnings("unchecked")
    protected void duplicarArrayCircular() {
        E[] nuevoArray = (E[]) new Object[elArray.length * 2];
        for (int i = 0; i < talla; i++, principioC = incrementar(principioC)) {
            nuevoArray[i] = elArray[principioC];
        }
        elArray = nuevoArray; principioC = 0; finalC = talla;
    }
    // incrementa un indice de un array circular 
    protected int incrementar(int indice) {
        if (indice + 1 == elArray.length) { return 0; }
        return indice + 1;
    }
    
    /** SII !esVacia()
     *  obtiene y elimina el dato que ocupa su principio */ 
    // principio se incrementa circularmente 
    public E desencolar() {
        E elPrimero = elArray[principioC];
        principioC = incrementar(principioC); talla--;
        return elPrimero;
    }
    
    /** SII !esVacia()
     *  obtiene el dato que ocupa el principio de una   
     *  Cola, el primero en orden de insercion */
    public E primero() { return elArray[principioC]; }
    
    /** comprueba si una Cola esta vacia */
    public boolean esVacia() { return talla == 0; }
    
    /** devuelve un String con los datos en orden FIFO, o de insercion,
     *  y con el formato que se usa en el estandar de Java. 
     *  Por ejemplo, si se tiene una Cola con los Integer del 1 al 4, 
     *  en orden FIFO, toString devuelve [1, 2, 3, 4]; 
     *  si la Cola esta vacia, entonces devuelve [] 
     */
    // OJO: se contempla la circularidad de elArray no solo usando el metodo
    // incrementar, sino contando el numero de Elementos desde 0 hasta talla-1
    /*public String toString() {
        // NOTA: se usa la clase StringBuilder, en lugar de String, 
        // por motivos de eficiencia
        StringBuilder res = new StringBuilder();
        res.append("[");
        // NOTA: por cuestiones de formato ... 
        // -Recorrer el ArrayCola desde el primero al PENULTIMO de sus datos; 
        // -En cada dato visitado, si aux es la variable del bucle de 
        //  recorrido, agnadir (append) a res aux.dato + ", "
        int aux = principioC;
        for (int i = 0, j = talla - 1; i < j; i++, aux = incrementar(aux)) {
            res.append(elArray[aux].toString() + ", ");
        }
        //aux = incrementar(aux);
        // NOTA: por cuestiones de formato, al terminar el bucle, 
        // agnadir el ultimo elemento al resultado;
        // en funcion de la talla, dicho elemento es el String 
        // elArray[aux].toString()+"]" o el String "]"
        if (talla != 0) { res.append(elArray[aux].toString() + "]"); }
        else { res.append("]"); }
        return res.toString();
    }*/
}
