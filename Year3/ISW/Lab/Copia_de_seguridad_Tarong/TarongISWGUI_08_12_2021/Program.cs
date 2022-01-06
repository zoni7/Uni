using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using TarongISW.Entities;
using TarongISW.Persistence;
using TarongISW.Services;

namespace TarongISWGUI
{
    static class Program
    {
        /// <summary>
        /// Punto de entrada principal para la aplicación.
        /// </summary>
        [STAThread]
        static void Main()
        {
            ITarongISWService service = new TarongISWService(new
                EntityFrameworkDAL(new TarongISWDbContext()));

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new TarongISWApp(service));


            // Data base elements to TEST
            service.RemoveAllData();

            Person p = new Person("18809898S", "Pepe");
            service.AddPerson(p);
        }
    }
}
