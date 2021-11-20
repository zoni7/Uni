namespace VehicleRental.Domain
{
	using System;
	using System.Collections.Generic;

	public partial class Person
	{
        protected Person()
        {
            Reservations = new List<Reservation>();
        }

        public Person(string dni, string name, string address, string city,
            string postalCode, DateTime dateDriverLicense) : this()
        {
            this.Dni = dni;
            this.Name = name;
            this.Address = address;
            this.City = city;
            this.PostalCode = postalCode;
            this.DateDriverLicense = dateDriverLicense;
        }

        public override string ToString()
        {
            return "Person (" + Dni + "): " + Name;
        }
    }
}

