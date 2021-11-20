namespace VehicleRental.Domain
{

	public partial class Category
	{
        protected Category() { }

        public Category(string name, double priceUnlimitedMileage, double priceFixedMileage,
            double priceAdditionalKm, double priceFullInsurance, double pricePartialInsurance) : this()
        {
            this.Name = name;
            this.PriceUnlimitedMileage = priceUnlimitedMileage;
            this.PriceFixedMileage = priceFixedMileage;
            this.PriceAdditionalKm = priceAdditionalKm;
            this.PriceFullInsurance = priceFullInsurance;
            this.PricePartialInsurance = pricePartialInsurance;
            Upper = null;
        }

        public Category(string name, double priceUnlimitedMileage, double priceFixedMileage,
            double priceAdditionalKm, double priceFullInsurance, double pricePartialInsurance, 
            Category upper) : this(name, priceUnlimitedMileage, priceFixedMileage, priceAdditionalKm, priceFullInsurance, pricePartialInsurance)
        {
            this.Upper = upper;
        }

        public override string ToString()
        {
            return "Category (" + Id + "):" + Name;
        }
    }
}

