public class Service {
    // Attributes 
    public int Code {
        get;
        set;
    }
    public string Name {
        get;
        set;
    }
    public string Description {
        get;
        set;
    }
    // Relationships Attributes

    public virtual ICollection<RestrictedTimePeriod> Periods {
        get;
        set;
    }
    // Constructor
    // public Service(int c, string n, string d) {}
    /*
        We don't have to provide a Period parameter because the minimum cardinality is 0 and not 1
    */
}