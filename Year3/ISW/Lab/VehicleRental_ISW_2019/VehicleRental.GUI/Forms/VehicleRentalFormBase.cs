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

namespace VehicleRental.Presentation
{
    public partial class VehicleRentalFormBase : Form
    {
        protected IVehicleRentalService service;

        public VehicleRentalFormBase()
        {
            InitializeComponent();
        }
        public VehicleRentalFormBase(IVehicleRentalService service) : this()
        {
            this.service = service;
        }
    }
}
