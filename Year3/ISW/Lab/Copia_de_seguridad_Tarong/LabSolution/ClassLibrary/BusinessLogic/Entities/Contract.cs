using System;
using System.Collections.Generic;
using System.Text;

namespace TarongISW.Entities
{
    public partial class Contract
    {

        public Contract() {
            Crates = new List<Crate>();
            Groups = new List<Group>();
        }

        public Contract(string bankAccount, DateTime initialDate, string SSN, Person hired):this() 
        {

            this.BankAccount = bankAccount;
            this.InitialDate = initialDate;
            this.SSN = SSN;

            Hired = hired;
        }

        // Check if the bankaccount has 20 numbers
        public bool CheckBanckAccount()
        {
            if (this.BankAccount.Length == 20) return true;
            else return false;
        }
        // Check if the SSN has 9 numbers
        public bool CheckSSN()
        {
            if (this.SSN.Length == 9) return true;
            else return false;
        }
    }
}
