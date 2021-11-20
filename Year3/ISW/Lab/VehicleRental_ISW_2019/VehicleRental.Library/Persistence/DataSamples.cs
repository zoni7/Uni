using System;
using VehicleRental.Services;
using VehicleRental.Domain;
using VehicleRental.Persistence;

namespace VehicleRental
{
    public static class DataSamples
    {
        public static BranchOffice Office1 { get; } = new BranchOffice("Design Patterns Street, 3");
        public static BranchOffice Office2 { get; } = new BranchOffice("Testing Avenue, 12");
        public static BranchOffice Office3 { get; } = new BranchOffice("Modelling Square");
        public static Category Luxury { get; } = new Category("Luxury", 23, 12, 2, 32, 14);
        public static Category Sedan { get; } = new Category("Sedan", 22, 10, 2, 30, 10, Luxury);
        public static Category Economy { get; } = new Category("Economy", 15, 5, 1, 15, 5, Sedan);
        public static CreditCard VisaCard { get; } = new CreditCard("1122334455667788", 12, 2025, 123, "Visa");
        public static Customer Charly { get; } = new Customer("11111111A", "Charly Singleton", "Three Layers Avenue", "StartUpCity", "11111", new DateTime(2015, 7, 12), new DateTime(2016, 2, 4), VisaCard);
        public static Person John { get; } = new Person("222222222B", "John Interface", "Use Cases Street", "HackerCity", "99999", new DateTime(2014, 5, 23));
        public static Reservation CharlyReservation { get; } = new Reservation(Charly, Office1, new DateTime(2016, 12, 23), Office1, new DateTime(2016, 12, 24), Economy, Charly);

        public static void CreateSampleDB(IDAL dal)
        {
            RemoveAllData(dal);
            dal.Insert(CharlyReservation);
            dal.Insert(John);
            dal.Insert(Office2);
            dal.Insert(Office3);
            dal.Commit();
        }

        public static void CreateSampleDB(IVehicleRentalService service)
        {
            service.removeAllData();
            service.addReservation(CharlyReservation);
            service.addPerson(John);
            service.addBranchOffice(Office2);
            service.addBranchOffice(Office3);
        }

        public static void RemoveAllData(IDAL dal)
        {
            dal.Clear<BranchOffice>();
            dal.Clear<Category>();
            dal.Clear<Reservation>();
            dal.Clear<Person>();
            dal.Clear<Customer>();
            dal.Clear<CreditCard>();
            dal.Commit();
        }



        public static void DisplayData(IDAL dal)
        {
            Console.WriteLine("Branch Offices");
            foreach (BranchOffice office in dal.GetAll<BranchOffice>())
            {
                Console.WriteLine(office);
            }
            Console.WriteLine("Categories");
            foreach (Category category in dal.GetAll<Category>())
            {
                Console.WriteLine(category);
            }
            Console.WriteLine("People");
            foreach (Person office in dal.GetAll<Person>())
            {
                Console.WriteLine(office);
            }
            Console.WriteLine("Customers");

            foreach (Customer office in dal.GetAll<Customer>())
            {
                Console.WriteLine(office);
            }

            Console.WriteLine("Reservations");
            foreach (Reservation reservation in dal.GetAll<Reservation>())
            {
                Console.WriteLine(reservation);
            }
            Console.WriteLine("Pres Key to exit...");
            Console.ReadKey();
        }
    }
}
