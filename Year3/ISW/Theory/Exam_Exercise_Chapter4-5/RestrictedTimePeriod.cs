public class RestrictedTimePeriod{
    public LocalTime StartDate {
        get;
        set;
    }
    public LocalTime EndDate {
        get;
        set;
    }
    public virtual PerimeterBased Perimeter {get;set;}
    public virtual Service Service {get;set;}

    // Constructor
    // public RestrictedTimePeriod(LocalTime sd, LocalTime ed, PerimeterBased P, Service s) {...}
}