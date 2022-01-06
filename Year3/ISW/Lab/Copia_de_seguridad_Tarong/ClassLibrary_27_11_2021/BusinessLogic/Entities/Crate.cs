using System;
using System.Collections.Generic;
using System.Text;


namespace TarongISW.Entities
{
    public partial class Crate
    {
        public Crate()
        {
        }

        public Crate(Product p, double wP, Contract c, Group g, Trip t)
        {
            //this.Loss = null;
            this.WeightInCoop = null;
            this.WeightInParcel = wP;
            this.Group = g;
            this.Trip = t;
            this.Contract = c;
            this.Product = p;
        }
    }
}
