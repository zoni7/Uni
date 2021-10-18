public class ApHORA
{
    // Relationships
    public virtual ICollection<Asignatura> Asignaturas {get;set;}
    public virtual ICollection<Profesor> Profesores {get;set;}
    // Constructors
    public ApHORA() {
        Asignaturas = new List<Asignatura>();
    }

    public ApHORA(Asignatura a, Profesor p):this() {
        Asignaturas.add(a);
        Profesores.add(p);
    }
}