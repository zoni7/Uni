using System;
using System.Collections.Generic;
using System.Text;

namespace TarongISW.Entities
{
    public partial class Group
    {
        // Attributes
        public DateTime? Date {
            get;
            set;
        }

        public int Id 
        {
            get;
            set;
        }

        // Attributes Relationships
        public virtual Parcel Parcel {
            set;
            get;
        }

        public virtual ICollection<Contract> Members {
            get;
            set;
        }
        public virtual ICollection<Crate> Crates
        {
            get;
            set;
        }
        
    }
}
