using System;
using System.Collections.Generic;
using System.Text;

namespace TarongISW.Entities
{
    public partial class Group
    {
        public Group() {
            // Collections
            Crates = new List<Crate>();
            Members = new List<Contract>();
        }

        public Group(DateTime d, Parcel p): this() {
            this.Date = d;
            this.Parcel = p;
        }

        
    }
}
