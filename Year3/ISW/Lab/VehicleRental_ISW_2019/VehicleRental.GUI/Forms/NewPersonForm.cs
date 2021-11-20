using System;
using System.Windows.Forms;
using VehicleRental.Services;
using VehicleRental.Domain;

namespace VehicleRental.Presentation
{
    public partial class NewPersonForm : VehicleRentalFormBase
    {

        protected NewReservationForm newReservationForm;

        public NewPersonForm() : base()
        {
            InitializeComponent();
        }

        public NewPersonForm(NewReservationForm newReservationForm, IVehicleRentalService service) : base(service)
        {
            InitializeComponent();
            this.newReservationForm = newReservationForm;
        }
        protected virtual bool fieldsOK()
        {
            return 
                !string.IsNullOrEmpty(dnitextBox.Text) &&
                !string.IsNullOrEmpty(nametextBox.Text) &&
                !string.IsNullOrEmpty(addresstextBox.Text) &&
                !string.IsNullOrEmpty(postalcodetextBox.Text) &&
                !string.IsNullOrEmpty(citytextBox.Text);
        }
        public virtual void Clear() { 
            dnitextBox.Clear();
            nametextBox.Clear();
            addresstextBox.Clear();
            postalcodetextBox.Clear();
            citytextBox.Clear();
        }


        protected virtual void addButton_Click(object sender, EventArgs e)
        {
            if (fieldsOK())
            {
                if (service.findPersonByDni(dnitextBox.Text) != null)
                    MessageBox.Show("Person with this DNI already exists", "Error");
                else {
                    Person p = new Person(dnitextBox.Text, nametextBox.Text, addresstextBox.Text, citytextBox.Text, postalcodetextBox.Text, driverLicensedateTimePicker.Value);
                    service.addPerson(p);
                    newReservationForm.addPersonFormConfirmed(p.Dni);

                }
            }
            else MessageBox.Show("Missing Fields", "Error");
        }
    }
}
