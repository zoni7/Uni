public class Asignatura {
    public string Nombre{get;set;}
    public long Codigo{get;set;}
    public string Curso{get;set;}

    //Relationships
    public virtual ICollection<Profesor> ProfesoresLista {get;set;}
    public virtual ICollection<Teoria> TemariosTeoricos {get;set;}
    public virtual ICollection<Practica> Practicas {get;set;}

    // Constructor
    public Asignatura() {
        ProfesoresLista = new List<Profesor>();
        TemariosTeoricos = new List<Teoria>();
        Practicas = new List<Practica>();
    }
    public Asignatura(string n, long c, string cu, Teoria t):this() {
        Nombre =n;
        Codigo=c;
        Curso=cu;
        TemariosTeoricos.add(t);
    }
}