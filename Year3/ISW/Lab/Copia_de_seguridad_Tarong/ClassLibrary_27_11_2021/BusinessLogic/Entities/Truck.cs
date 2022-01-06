using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TarongISW.Entities
{
    public partial class Truck
    {

        public Truck() 
        { 
            this.Trips = new List<Trip>(); 
        }

        public Truck( string Id, double MaximumAuthorizedMass, double TareWeight):this() {         
            this.Id = Id;
            this.MaximumAuthorisedMass = MaximumAuthorizedMass;
            this.TareWeight = TareWeight;
        }

        public Trip GetLastTrip()
        {
            return this.Trips.Last();
        }

    }
}
