public class Person{
    // Attributes
    public string Dni {
        get;
        set;
    }
    public string Name {
        get;
        set;
    }

    // Relationships Attributes
    public virtual ICollection<Individual> Individuals { 
        get;
        set;
    }

    public virtual Area AreaLiving{
        get;
        set;
    }

    //Constructor
    // public Person (string d, string n, Area a) {} 
    // Area a is passed here but not in Area constructor because we have a 1 to 1 relationship in the minimum cardinality
    // you have to chose one contructor from both classes

    // Minimum cardinality of Individual and person is 0 so don't pass a parameter in the constructor
}