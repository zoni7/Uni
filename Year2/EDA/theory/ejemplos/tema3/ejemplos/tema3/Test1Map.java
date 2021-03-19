package ejemplos.tema3;

import librerias.estructurasDeDatos.modelos.Map; 
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.deDispersion.TablaHash; 
import java.util.Locale; 
import java.util.Scanner;  

/**
 * Ejemplo Tema 3-S1: programa en el que se define, crea y 
 *                    usa un Map
 * @author (prof EDA) 
 * @version (Curso 2015-2016)
 */                                                

public class Test1Map {
    
    public static void main(String[] args) {
        
        // Por simplicidad, la frase no se lee de un fichero,  
        // sino que se lee de teclado como un String de Palabras 
        // separadas por blancos. Una frase (String) ejemplo seria: 
        // "vale, aunque es un poco rollo lo hago para que se vea como funciona el Map!! Se me ha olvidado escribir palabras repetidas vaya!!"

        // lectura de la frase (String) a partir de la que se construye el Map
        Locale localEDA = new Locale("es", "US");
        Scanner teclado = new Scanner(System.in).useLocale(localEDA);
        System.out.println("Escribe frase con palabras separadas por blancos:");
        String texto = teclado.nextLine();

        // Creacion del Map vacio ... ?Que Clave y Valor tiene cada Entrada
        // del Map? ?De que tipos son? 
        Map<String, String> m = new TablaHash<String, String>(texto.length());

        // Construccion del Map, via insercion/actualizacion de sus Entradas, 
        // a partir de la frase leida: uso del metodo split de String con
        // separador " " (uno o mas)
        String[] palabrasDelTexto = texto.split(" +");
        for (int i = 0; i < palabrasDelTexto.length; i++) {
            String palabraMin = palabrasDelTexto[i].toLowerCase();
            m.insertar(palabraMin, ""); 
            //OJO: LO MAS BARATO SERIA d.insertar(palabraMin, palabraMin);
        }
        // OJO: nos piden mostrar las palabras distintas que aparecen, que 
        // NO son las Entradas del Map sino SOLO sus claves
        ListaConPI<String> deClaves = m.claves();
        System.out.println("Palabras distintas frase, o Claves del Map:\n" 
                           + deClaves);
    } 
}