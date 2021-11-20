namespace VehicleRental.Domain
{
    using System;
    using System.Collections.Generic;

    public partial class Reservation
    {
        protected Reservation()
        {
            Drivers = new List<Person>();
        }
        public Reservation(Customer customer, BranchOffice pickupBranchOffice, DateTime pickupDate,
            BranchOffice returnBranchOffice, DateTime returnDate, Category category, IEnumerable<Person> drivers) : this()
        {
            //Autoincremental Id
            PickupDate = pickupDate;
            ReturnDate = returnDate;
            Category = category;
            PickUpOffice = pickupBranchOffice;
            ReturnOffice = returnBranchOffice;
            Customer = customer;
            Drivers = new List<Person>(drivers);
            // Updating customer reservations
            customer.Reservations.Add(this);
            //Updating Branch Offices
            PickUpOffice.PickUpReservations.Add(this);
            ReturnOffice.ReturnReservations.Add(this);
        }

        public Reservation(Customer customer, BranchOffice pickupBranchOffice, DateTime pickupDate,
            BranchOffice returnBranchOffice, DateTime returnDate, Category category, Person driver) 
            : this(customer, pickupBranchOffice, pickupDate, returnBranchOffice, returnDate, category, new List<Person>())
        {
            Drivers.Add(driver);
        }

        public int NumDrivers { get { return Drivers.Count; } }

        public override string ToString()
        {
            return "Reservation (" + Id + ") from " + PickupDate + " to " + ReturnDate
                + " of " + Category + " by " + Customer;
        }
    }
}

