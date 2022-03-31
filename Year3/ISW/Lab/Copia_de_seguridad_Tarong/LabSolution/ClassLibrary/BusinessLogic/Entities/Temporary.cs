﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TarongISW.Entities
{
    public partial class Temporary 
    {
        public Temporary() { 
            
        }

        public Temporary(string bankAccount, DateTime initialDate, string SSN,
Person hired) : base(bankAccount, initialDate, SSN, hired) {
            this.FinalDate = null;
        }

        // Checks if the IitialDate given is earlier than FinalDate
        public bool CheckFinalDate() {
            if (this.FinalDate == null) return true;
            int result = DateTime.Compare(this.InitialDate, this.FinalDate.Value);          
            // Initial date is earlier
            if (result < 0)
                return true;
            else if (result == 0)
                return true;
            // Initial date is later
            else
                return false;
           
        }
    }
}