public class Grupo {
    // Attributes
    public string Cod_g {get;set;}
    public string Tamaño {get;set;}
    // Relationships
    public virtual Profesor ProfesorAsignado {get;set;}
    public virtual Franja_horaria Horario {get;set;}

    // Constructor
    public Grupo(){}
    public Grupo(string c, string t, Profesor p , Franja_horaria f ) {
        this.cod_g = c;
        this.tamaño = t;
        this.ProfesorAsignado = p;
        this.Horario = f;
        
    }
}