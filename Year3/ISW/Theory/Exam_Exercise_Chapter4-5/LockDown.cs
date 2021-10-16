public abstract class LockDown {
    
    // Attributes
    public DateTime StartDate {
        get;
        set;
    }

    public DateTime EndDate {
        get;
        set;
    }

    /* DO WE HAVE TO DEFINE A CONTRUCTOR FOR AN ABSTRACT CLASS?
        In this case yes because we have attributes and have to be initialized
    */
    // Constructor
    // public LockDown(DateTimec s, DateTime e) {}
}