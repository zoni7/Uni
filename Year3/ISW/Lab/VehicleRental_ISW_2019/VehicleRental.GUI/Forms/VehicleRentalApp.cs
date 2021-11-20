using System;
using System.Windows.Forms;
using VehicleRental.Services;

namespace VehicleRental.Presentation
{
    public partial class VehicleRentalApp : VehicleRentalFormBase
    {
        private ListReservationsForm listReservationForm;
        private NewReservationForm newReservationForm;

        public VehicleRentalApp(IVehicleRentalService service) : base(service)
        {
            InitializeComponent();
            listReservationForm = new ListReservationsForm(service);
            newReservationForm = new NewReservationForm(service);
        }

        private void newToolStripMenuItem_Click(object sender, EventArgs e)
        {
            newReservationForm.ShowDialog();
        }

        private void listToolStripMenuItem_Click(object sender, EventArgs e)
        {
            listReservationForm.ShowDialog();
        }

        private void createNewDBToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                DataSamples.CreateSampleDB(service);
                MessageBox.Show("Database Created Sucessfully");
            }
            catch (ServiceException ex)
            {
                MessageBox.Show("Unable to populate Database");
                Console.WriteLine(ex.Message);
            } finally
            {

            }
        }

        private void exitButton_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }
    }


}
