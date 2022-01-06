using System;
using System.Collections.Generic;
using System.Text;

namespace TarongISW.Entities
{
    public partial class Parcel
    {
        public Parcel()
        {
            this.Groups = new List<Group>();
        }
        public Parcel(string cadastralReferncep, string namep, Product productp, double sizep, Person ownerp) : this()
        {
            this.CadastralReference = cadastralReferncep;
            this.Name = namep;
            this.Product = productp;
            this.Size = sizep;
            this.Owner = ownerp;
        }
    }
}

