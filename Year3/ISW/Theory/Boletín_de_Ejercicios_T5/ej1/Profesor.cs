public class Profesor {
    // Attributos 
    public string Codigo {get;set;}
    public string Nombre {get;set;}
    // Relationships
    public virtual  ICollection<Asignatura> AsignaturasImpartidas {get;set;}
    public virtual  ICollection<Grupo> GruposImpartidos {get;set; }

    // Constructor
    public Profesor() {
        AsignaturasImpartidas = new List<Asignatura>();
        GruposImpartidos = new List<Grupo>();        
    }

    public Profesor(string c, string n, Asignatura a, Grupo g):this() {
        Codigo = c;
        Nombre = n;
        AsignaturasImpartidas.add(a);
        GruposImpartidos.add(g);
    }
}
