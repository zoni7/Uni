package ejemplos.tema2;

import java.util.Arrays;

/** Clase de prueba de los metodos de BusquedaBinaria1y2 
 *
 *  @author EDA
 *  @version Septiembre 2020
 */

public class TestBusquedaBinaria {
    
    // obtiene una matriz de fxc Integer ordenados Asc. por filas
    private static Integer[][] generarMatrizOrdenadaInteger(int f, int c) {
        Integer[][] res = new Integer[f][c];
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = new Integer((i * f + j));
            }
        }
        return res;  
    }
    
    public static void main(String[] args) {
        
        Integer[][] m = generarMatrizOrdenadaInteger(10, 10);
        System.out.println("Matriz de Integer sobre la que se busca:\n");  
        for (int i = 0; i < 10; i++) {
            System.out.println("\t" + Arrays.toString(m[i]));
        }
        System.out.println();
        
        Integer e = m[0][0];
        System.out.println(e + " se encuentra en el punto " 
            + BusquedaBinaria1y2D.buscarEnMatriz(m, e) + " de la matriz ");
        
        e = m[9][9];
        System.out.println(e + " se encuentra en el punto " 
            + BusquedaBinaria1y2D.buscarEnMatriz(m, e) + " de la matriz ");
        
        e = m[3][7];
        System.out.println(e + " se encuentra en el punto " 
            + BusquedaBinaria1y2D.buscarEnMatriz(m, e) + " de la matriz ");
        
    }
}
