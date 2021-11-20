using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;


using VehicleRental.Services;
using VehicleRental.Domain;

namespace VehicleRental.Presentation
{
    public partial class ListReservationsForm : VehicleRentalFormBase
    {
        
        public ListReservationsForm(IVehicleRentalService service) : base(service)
        {
            InitializeComponent();
            LoadData();
        }
        public void LoadData()
        {
            ICollection<Customer> customers = service.findAllCustomers();
            customersComboBox.Items.Clear();
            if (customers!=null)
                foreach (Customer c in service.findAllCustomers())
                    customersComboBox.Items.Add(c.Dni);
            customersComboBox.SelectedIndex = -1;
            customersComboBox.ResetText();
            reservationsbindingSource.DataSource = null;
        }

        private void customersComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            string dni = (string) customersComboBox.SelectedItem;
            ICollection<Reservation> reservations = service.findReservationsbyCustomerID(dni);

            //A list of anonymous objects is created to display 
            //DataGrid with the info that is needed
            BindingList<object> bindinglist = new BindingList<object>();
            foreach (Reservation r in reservations)

                //Adding one anonymous object for each reservation obtained
                bindinglist.Add(new
                {
                    //ds_... are DataPropertyNames defined in the DataGridView object
                    //see DataGridView column definitions in Visual Studio Designer
                    ds_Id = r.Id,
                    ds_Customer = r.Customer.Name,
                    ds_PickUpOffice = r.PickUpOffice.Address,
                    ds_ReturnOffice = r.ReturnOffice.Address,
                    ds_Category = r.Category.Name,
                    ds_NumDrivers = r.Drivers.Count
                });

            reservationsbindingSource.DataSource = bindinglist;

        }
    }
}
