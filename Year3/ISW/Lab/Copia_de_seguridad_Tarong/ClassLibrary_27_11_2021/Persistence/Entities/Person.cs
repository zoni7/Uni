using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;


namespace TarongISW.Entities
{
    public partial class Person
    {
        public string Id
        {
            get;
            set;
        }
        public string Name
        {
            get;
            set;
        }
        public virtual ICollection<Parcel> Parcels
        {
            get;
            set;
        }
        public virtual ICollection<Contract> Contracts
        {
            get;
            set;
        }


    }
}
