package ejemplos.tema3.ejemplos.tema3;

import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.deDispersion.TablaHash;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Traduccion Bilingue, Palabra a Palabra, de un texto.
 * La clase tiene dos metodos:
 *  1.- cargarDiccionario: crea y devuelve el Map que se usa en la traduccion, 
 *      leyendo sus Entradas del fichero diccioSpaEng.txt ubicado en eda
 *  2.- traducir: traduce la frase textoC palabra a palabra usando el Map d 
 *      Cuando d no contenga la traduccion para una palabra de textoC, el 
 *      String resultado de traducir debe contener el literal "<error>"en  
 *      lugar de su traduccion
 *      
 * @author (prof EDA) 
 * @version (Curso 2015-2016)
 */

public class Test4Map {
    
    public static Map<String, String> cargarDiccionario() {
        String nDic = "diccioSpaEng.txt";
        Map<String, String> d = new TablaHash<String, String>(100);
        try { 
            Scanner ft = new Scanner(new File(nDic), "ISO-8859-1");
            while (ft.hasNextLine()) {
                String linea = ft.nextLine();
                String[] a = linea.split("\t");
                d.insertar(a[0], a[1]);
            }
            ft.close();
            return d;
        } catch (FileNotFoundException e) {
            System.out.println("** Error: No se encuentra el fichero " + nDic);
            return null;
        }
    }
            
    public static String traducir(String textoC, Map<String, String> d) {
        /*COMPLETAR*/
    }
}
