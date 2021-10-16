public class PerimeterBased : LockDown{
    // Relationships Attributes
    public virtual ICollection<Area> AffectedArea {
        get;
        set;
    }

    public virtual ICollection<RestrictedTimePeriod> RestrictedTimePeriod {
        get;
        set;
    }
    // Constructor
    // public PerimeterBased (DateTime sd, DateTime ed, Area a):base(sd,ed) {}
    /*
        We should call the constructor of the parent class -> base(...)
    */
}