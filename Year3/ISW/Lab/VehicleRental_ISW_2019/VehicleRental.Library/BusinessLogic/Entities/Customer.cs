namespace VehicleRental.Domain
{
	using System;

	public partial class Customer : Person
	{
        protected Customer() : base() { }
        public Customer(string dni, string name, string address, string city,
            string postalCode, DateTime dateDriverLicense,
            DateTime registrationDate, CreditCard card)
            : base(dni, name, address, city, postalCode, dateDriverLicense)
        {
            this.RegistrationDate = registrationDate;
            this.CreditCard = card;
        }

        public override string ToString()
        {
            return "Customer (" + Dni + "): " + Name;
        }
    }
}

