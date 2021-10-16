public class Individual : LockDown {
    // Relationships Attributes
    public virtual ICollection<Person> AffectedPeople {
        get;
        set;
    }
    // Constructor 
    // public Individual(DateTime sd, DateTime ed,Person p):base(sd, ed) {}
    /*   WHY A PERSON IN THE CONSTRUCTOR?
        because the minimum cardinality is one
    */
    /*
        We should call the constructor of the parent class -> base(...)
    */
}