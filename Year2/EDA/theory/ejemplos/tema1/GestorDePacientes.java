package ejemplos.tema1;

import librerias.estructurasDeDatos.lineales.ArrayColaPlus;

/** Ejercicio: La clase GestorDePacientes usa Cola */

public class GestorDePacientes {

    public static final int MAXIMO_DIARIO_PACIENTES = 40;
    public static final double HORA_INICIO_CONSULTA = 9.00; 
    public static final double TIEMPO_MEDIO_VISITA = 0.15;
    
    private librerias.estructurasDeDatos.modelos.ColaPlus<Paciente> q;  
    private double horaCita;
  
    public GestorDePacientes() {
        q = new ArrayColaPlus<Paciente>(); 
        horaCita = HORA_INICIO_CONSULTA;
    }
    
    public String darCita(Paciente x) {
        String res = "Espere un momento; veo si le pueden atender magnana... ";
        boolean aceptado = (q.talla() <= MAXIMO_DIARIO_PACIENTES); 
        if (!aceptado) { res += "\nLo siento. Magnana no podemos atenderle"; }
        else {
            q.encolar(x); 
            res += "\nConfirmado, le esperamos magnana a las " 
                   + String.format("%2.2f", horaCita);
            horaCita += TIEMPO_MEDIO_VISITA;
            if (horaCita - Math.round(horaCita) < 0.0) { 
                horaCita = Math.round(horaCita);
            }
        }
        return res;
    }

    public String toString() {
        return "Historiales de sus " + q.talla() 
               + " Pacientes de magnana en orden de visita\n" + q;
    }                    
}