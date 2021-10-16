public class Area{
    // Attributes
    public long Lattitude {
        get;
        set;
    }

    public long Longitude {
        get;
        set;
    }

    public long Radius {
        get;
        set;
    }

    public unit Population {
        get;
        set;
    }
    
    public long InfectionRate {
        get;
        set;
    }

    // Relationships Attributes
    public virtual ICollection<Person> LivesInPeople {
        get;
        set;
    }
    // Contructor
    // public Area(long la, long lo, long ra, unit p, long i) {}
    
}