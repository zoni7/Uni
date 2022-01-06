using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using TarongISW.Entities;
using TarongISW.Services;

namespace TarongISWGUI
{
    public partial class ContractForm : Form

    {
        ITarongISWService service;
        private List<Person> dniList { get; set; }
        public ContractForm(ITarongISWService service)
        {
            this.service = service;
            InitializeComponent();

            
        }

        private void ContractForm_Load(object sender, EventArgs e)
        {
            // Fill combobox with DNIs
            /*
            dniList = service.getAllPersons();
            foreach( Person p in dniList)
            {
                dni_cb.Items.Add()
            }
            */
            
        }

        

        // Button create
        private void create_b_Click(object sender, EventArgs e)
        {
            string dni = dni_cb.Text;
            string bankaccount = bankAccount_txtb.Text;
            string SSN = ssn_txtb.Text;
            DateTime initialDate = initialDate_dateP.Value;
            DateTime finalDate = finalDate_dateP.Value;
            double salary = Decimal.ToDouble(salary_numb.Value);
                      
            try
            {
                Person person = service.FindPersonById(dni);
                if (permanent_radiob.Checked)
                {
                    // Add permanent
                    Permanent perm = new Permanent(bankaccount, initialDate, SSN, person, salary);
                    service.AddPermanent(perm);
                } else
                {
                    // Add temporary
                    Temporary temp = new Temporary(bankaccount, initialDate, SSN, person);
                    temp.FinalDate = finalDate;
                    service.AddTemporary(temp);
                }
            }
            catch (ServiceException sEXc)
            {
                // Show a message to the user                
                DialogResult message = MessageBox.Show(this, sEXc.Message , "Error",
                    MessageBoxButtons.RetryCancel, MessageBoxIcon.Error);
                if (message == DialogResult.Cancel) this.Close();
                else if (message == DialogResult.Retry) // Retry operation
                { }
            }

        }

        private void cancel_b_Click(object sender, EventArgs e)
        {
            // Cancel the operation
            this.Close();
        }
        

        private void permanent_radiob_CheckedChanged(object sender, EventArgs e)
        {
            salary_numb.Enabled = true;
            finalDate_dateP.Enabled = false;
        }

        private void temporary_radiob_CheckedChanged(object sender, EventArgs e)
        {
            salary_numb.Enabled = false;
            finalDate_dateP.Enabled = true;
        }

        private void salary_numb_ValueChanged(object sender, EventArgs e)
        {

        }

       
    }
}
