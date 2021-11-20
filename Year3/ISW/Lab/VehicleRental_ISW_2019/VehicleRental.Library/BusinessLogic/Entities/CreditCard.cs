namespace VehicleRental.Domain
{

	public partial class CreditCard
	{
        protected CreditCard() { }

        public CreditCard(string number, int month, int year, int cvc, string type) : this()
        {
            this.Digits = number;
            this.Month = month;
            this.Year = year;
            this.Cvc = cvc;
            this.Type = type;
        }

        public override string ToString()
        {
            return "Credit Card:" + Digits;
        }
    }
}

