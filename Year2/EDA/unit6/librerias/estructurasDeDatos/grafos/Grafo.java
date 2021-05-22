package librerias.estructurasDeDatos.grafos;

import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.modelos.Cola;

/** Clase abstracta Grafo: Base de la jerarquia Grafo, que define el 
 *  comportamiento de un grafo<br> 
 *  No es una interfaz porque incluye el codigo de aquellas operaciones  
 *  de un grafo que son independientes tanto de su tipo como de 
 *  su implementacion<br>
 *  
 *  @version Abril 2019
 */

public abstract class Grafo {
    
    protected boolean esDirigido;
    
    /** Crea un grafo vacio, Dirigido si esDigrafo es true
     *  o No Dirigido en caso contrario.
     *  @param esDigrafo Indica el tipo del grafo, Dirigido o No
     */
    public Grafo(boolean esDigrafo) { esDirigido = esDigrafo; }
    
    
    /** Comprueba si un grafo es o no Dirigido.
     *  @return boolean true si el grafo es Dirgido y false si es No Dirigido
     */
    public boolean esDirigido() { return esDirigido; }
        
    /** Devuelve el numero de vertices de un grafo.
     *  @return int numero de vertices de un grafo
     */
    public abstract int numVertices();
    
    /** Devuelve el numero de aristas de un grafo.
     *  @return int numero de aristas de un grafo
     */
    public abstract int numAristas();
    
    /** Comprueba si la arista (i, j) esta en un grafo.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     *  @return boolean true si (i, j) esta y false en caso contrario
     */
    public abstract boolean existeArista(int i, int j);
    
    /** Devuelve el peso de la arista (i, j) de un grafo, 0 si dicha arista 
     *  no esta en el grafo.
     *  @return double Peso de la arista (i, j), 0 si no existe.
     */
    public abstract double pesoArista(int i, int j);
    
    /** Si no esta, inserta la arista (i, j) en un grafo no Ponderado.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     */
    public abstract void insertarArista(int i, int j);
    
    /** Si no esta, inserta la arista (i, j) de peso p en un grafo Ponderado.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     *  @param p    Peso de la arista (i, j)
     */
    public abstract void insertarArista(int i, int j, double p);

    /** Devuelve una Lista Con PI que contiene los adyacentes al vertice i de 
     *  un grafo.
     *  @param i Vertice del que se obtienen los adyacentes
     *  @return ListaConPI con los vertices adyacentes a i
     */
    public abstract ListaConPI<Adyacente> adyacentesDe(int i);
       
    /** Devuelve un String con cada uno de los vertices de un grafo y sus 
     *  adyacentes, en orden de insercion. 
     *  @return  String que representa a un grafo
     */        
    public String toString() {
        String res = "";  
        for (int i = 0; i < numVertices(); i++) {
            res += "V\u00E9rtice: " + i;
            ListaConPI<Adyacente> l = adyacentesDe(i);
            if (l.esVacia()) { res += " sin Adyacentes "; }
            else { res += " con Adyacentes "; } 
            // res += (l.esVacia()) ? " sin adyacentes " :  " con adyacentes: ";
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                res +=  l.recuperar() + " ";  
            }
            res += "\n";  
        }
        return res;      
    }   
    
    // Se iran agnadiendo metodos y atributos "auxiliares" 
    // conforme avance el tema 
}
