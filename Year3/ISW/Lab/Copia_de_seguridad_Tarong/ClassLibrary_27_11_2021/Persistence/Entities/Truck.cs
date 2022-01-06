using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
namespace TarongISW.Entities
{
    public partial class Truck
    {
        public double MaximunWeight
        {
            get { return MaximumAuthorisedMass - TareWeight; } 
            // set;
        }
        //Derived attribute
        public string Id { get; set; }
        public double MaximumAuthorisedMass { get; set; }
        public double TareWeight { get; set; }
        public virtual ICollection<Trip> Trips { get; set; }
        //may be null since the cardinality is 0..n
        //Part of relation

    }
}