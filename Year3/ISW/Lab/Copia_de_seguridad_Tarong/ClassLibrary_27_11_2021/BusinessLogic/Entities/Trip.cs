using System;
using System.Collections.Generic;
using System.Text;

namespace TarongISW.Entities
{
    public partial class Trip
    {

        public Trip() {
            this.Crates = new List<Crate>();
        }

        public Trip(Truck Truck):this() {

            this.CoopArrival = null;
            this.ParcelExit = null;
            this.UnloadTime = null;
            this.Truck = Truck;
        }

    }
}
