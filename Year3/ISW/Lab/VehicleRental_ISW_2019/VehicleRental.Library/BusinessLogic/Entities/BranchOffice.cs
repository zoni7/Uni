namespace VehicleRental.Domain 
{
	using System.Collections.Generic;

	public partial class BranchOffice
	{
        protected BranchOffice()
        {
            PickUpReservations = new List<Reservation>();
            ReturnReservations = new List<Reservation>();

        }

        public BranchOffice(string address) : this()
        {
            this.Address = address;
        }

        public override string ToString()
        {
            return "Office (" + Id + "):" + Address;
        }
    }
}

