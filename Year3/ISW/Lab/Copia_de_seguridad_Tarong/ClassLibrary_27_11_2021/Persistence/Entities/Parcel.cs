using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;

namespace TarongISW.Entities
{
    public partial class Parcel
    {
        [Key]
        public string CadastralReference
        {
            get;
            set;
        }
        public string Name
        {
            get;
            set;
        }
        public double Size
        {
            get;
            set;
        }
        public virtual Product Product
        {
            get;
            set;
        }
        public virtual ICollection<Group> Groups
        {
            get;
            set;
        }
        public virtual Person Owner
        {
            get;
            set;
        }
    }
}
