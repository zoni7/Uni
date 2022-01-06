using System;
using System.Collections.Generic;
using System.Text;

namespace TarongISW.Entities
{
    public partial class Trip
    {
        public double CarriedWeight {
            get { return Truck.TareWeight } 
            //set; 
        }
        //Derived attribute
        public DateTime? CoopArrival { get; set; }
        public int id { get; set; }
        //Never set in constructor, handled by EntitiyFramework
        public DateTime? ParcelExit { get; set; }
        public DateTime? UnloadTime { get; set; }

        public virtual Truck Truck { get; set; }
        //Part of relation

        public virtual ICollection<Crate> Crates {
            get;
            set;
        }
    }
}
