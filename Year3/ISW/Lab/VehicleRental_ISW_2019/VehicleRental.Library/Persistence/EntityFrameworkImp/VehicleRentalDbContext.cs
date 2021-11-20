
using VehicleRental.Domain;
using System.Data.Entity;

namespace VehicleRental.Persistence
{
    public class VehicleRentalDbContext : DbContext
    {
        public VehicleRentalDbContext() : base("Name=VehicleRentalDbConnection") //this is the connection string name
        {
            /*
            See DbContext.Configuration documentation
            */
            Configuration.ProxyCreationEnabled = true;
            Configuration.LazyLoadingEnabled = true;
        }

        static VehicleRentalDbContext()
        {
            //Database.SetInitializer<VehicleRentalDbContext>(new CreateDatabaseIfNotExists<VehicleRentalDbContext>());
            Database.SetInitializer<VehicleRentalDbContext>(new DropCreateDatabaseIfModelChanges<VehicleRentalDbContext>());
            //Database.SetInitializer<VehicleRentalDbContext>(new DropCreateDatabaseAlways<VehicleRentalDbContext>());
            //Database.SetInitializer<VehicleRentalDbContext>(new VehicleRentalDbInitializer());
            //Database.SetInitializer(new NullDatabaseInitializer<VehicleRentalDbContext>());
        }

        public IDbSet<BranchOffice> BranchOffices { get; set; }
        public IDbSet<Reservation> Reservations { get; set; }
        public IDbSet<Category> Categories { get; set; }
        public IDbSet<Person> People { get; set; }
        public IDbSet<Customer> Customers { get; set; }
        public IDbSet<CreditCard> CreditCards { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            // Primary keys with non conventional name
            /*
            modelBuilder.Entity<Person>().HasKey(p => p.Dni);
            modelBuilder.Entity<Customer>().HasKey(c => c.Dni);
            modelBuilder.Entity<CreditCard>().HasKey(c => c.Digits);
            */
            // Classes with more than one relationship
            /*
            modelBuilder.Entity<Reservation>().HasRequired(r => r.PickUpOffice).WithMany(o => o.PickUpReservations).WillCascadeOnDelete(false);
            modelBuilder.Entity<Reservation>().HasRequired(r => r.ReturnOffice).WithMany(o => o.ReturnReservations).WillCascadeOnDelete(false);
            */
        }

    }

}