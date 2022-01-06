using System;
using System.Collections.Generic;
using System.Text;

namespace TarongISW.Entities
{
    public partial class Crate
    {
        // Attributes
        public double? Loss
        {
            get { return WeightInParcel - WeightInCoop; }
            //set;
        }

        public int Id 
        {
            get;
            set;
        }

        public double? WeightInCoop
        {
            set;
            get;
        }

        public double WeightInParcel
        {
            set;
            get;

        }
        // Relationships attributes
        public virtual Group Group
        {
            get;
            set;
        }

        public virtual Trip Trip
        {
            get;
            set;
        }

        public virtual Contract Contract
        {
            set;
            get;
        }
        public virtual Product Product
        {
            get;
            set;
        }
    }
}
