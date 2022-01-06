using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using TarongISW.Services;

namespace TarongISWGUI
{
    public partial class TarongISWApp : Form
    {
        private ITarongISWService service;
        private ContractForm contractForm;
        public TarongISWApp(ITarongISWService service)
        {
            InitializeComponent();
            this.service = service;
            contractForm = new ContractForm(service);
        }

        private void TarongISWApp_Load(object sender, EventArgs e)
        {

        }
             

        private void button1_Click_1(object sender, EventArgs e)
        {

            // Set the Parent Form of the Child window.
            contractForm.MdiParent = this;
            contractForm.StartPosition = FormStartPosition.CenterScreen;
            contractForm.Dock = DockStyle.Fill;
            // Display the new form in a non modal way.
            contractForm.Show();

        }
    }
}
