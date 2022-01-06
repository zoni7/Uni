using System;
using System.Collections.Generic;
using System.Text;

namespace TarongISW.Entities
{
    public partial class Person
    {
        public Person()
        {
            this.Parcels = new List<Parcel>();
            this.Contracts = new List<Contract>();
        }
        public Person(string idp, string namep) : this()
        {
            this.Id = idp;
            this.Name = namep;
        }

        // Check if the DNI is correct
        public bool CheckDni()
        {
            string dni = this.Id;
            // the dni doesn't have 9 values
            if (dni.Length != 9) return false;

            string dniNumbers = dni.Substring(0, dni.Length - 1);
            string dniLeter = dni.Substring(dni.Length - 1, 1);

            int idInteger = Int16.Parse(dniNumbers);
            if (CalculateDNILeter(idInteger) != dniLeter)
            {
                // Incorrect letter
                return false;
            }
            // It is valid
            return true;
        }

        static string CalculateDNILeter(int dniNumbers)
        {
            // Load check digits
            string[] control = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E" };
            var mod = dniNumbers % 23;
            return control[mod];
        }
    }
}
