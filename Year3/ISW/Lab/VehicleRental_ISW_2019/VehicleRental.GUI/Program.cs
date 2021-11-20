using System;
using System.Windows.Forms;
using VehicleRental.Persistence;
using VehicleRental.Services;

namespace VehicleRental.Presentation
{
    static class Program
    {
        /// <summary>
        /// Punto de entrada principal para la aplicación.
        /// </summary>
        [STAThread]
        static void Main()
        {
            IVehicleRentalService service = new VehicleRentalService(new EntityFrameworkDAL(new VehicleRentalDbContext()));

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new VehicleRentalApp(service));
        }
    }
}
