public class Teoria : Grupo {
    // Relationships
    public virtual Asignatura Asignatura {get;set;}

    // Constructor
    public Teoria() {}
    public Teoria(Profesor ProfesorAsignado, Franja_horaria Horario, Asignaura a, string Cod_g, string Tamaño):base(ProfesorAsignado,Horario) {
        this.Asignatura = a;
    }
    
}