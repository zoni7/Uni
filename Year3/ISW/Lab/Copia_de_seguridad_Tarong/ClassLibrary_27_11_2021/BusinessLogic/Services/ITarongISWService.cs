using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TarongISW.Entities;


namespace TarongISW.Services
{
    public interface ITarongISWService
    {
        void RemoveAllData();
        void Commit();

        // Necesario para la inicialización de la BD
        void AddPerson(Person p);
        void AddParcel(Parcel parcel);
        void AddTruck(Truck truck);

        // A partir de aquí son los CU pedidos
        Person FindPersonById(string id);

        void AddPermanent(Permanent perm);
        void AddTemporary(Temporary temp);
        List<Contract> GetAllContracts();

        Parcel FindParcelById(string cadas);

        void AddGroup(Group group);
        List<Group> GetAllGroups();

        Truck FindTruckById(string id);
        List<Truck> GetAllTrucks();

        List<Trip> GetAllTrips();

        void AddCrate(Crate crate);
        List<Crate> GetAllCrates();

    }
}