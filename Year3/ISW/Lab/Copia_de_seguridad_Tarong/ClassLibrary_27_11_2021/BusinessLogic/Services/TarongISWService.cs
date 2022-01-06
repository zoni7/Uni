using System;
using System.Collections.Generic;
using System.Linq;
using TarongISW.Entities;
using TarongISW.Persistence;

namespace TarongISW.Services
{
    public class TarongISWService : ITarongISWService
    {
        private readonly IDAL dal;

        public TarongISWService(IDAL dal)
        {
            this.dal = dal;
        }

        public void RemoveAllData()
        {
            dal.RemoveAllData();
        }

        public void Commit()
        {
            dal.Commit();
        }

        public void AddPerson(Person person)
        {
            // Check if the Dni is correct
            if (!person.CheckDni()) throw new ServiceException("DNI is not correct.");
            // Restricction: there cannot be two people with the same id.
            if (dal.GetById<Person>(person.Id) == null)
            {
                dal.Insert<Person>(person);
                Commit();
            }
            else throw new ServiceException("Person with Id " + person.Id + " already exists.");
        }

        public void AddParcel(Parcel parcel)
        {
            // Restriction: there cannot be two parcels with the same name.
            if (!dal.GetWhere<Parcel>(x => x.CadastralReference == parcel.CadastralReference).Any())
            {
                dal.Insert<Parcel>(parcel);
                Commit();
            }
            else throw new ServiceException("Parcel with Name " + parcel.Name + " already exists.");
        }

        public void AddTruck(Truck truck)
        {
            // Restriction: there cannot be two trucks with the same id.
            if (dal.GetById<Truck>(truck.Id) == null)
            {
                dal.Insert<Truck>(truck);
                Commit();
            }
            else throw new ServiceException("Truck with Id " + truck.Id + " already exists.");
        }

        // From here, implement your own code for the rest of use cases.


        // Return a list with all the contracts
        public List<Contract> GetAllContracts()
        {
            return new List<Contract>(dal.GetAll<Contract>());
        }


        public void AddPermanent(Permanent perm)
        {
            // Add person if there is no peope inscribed will be implemented in the GUI
            // Check if there is non-valid information
            if (perm.CheckBanckAccount() && perm.CheckSSN() && perm.CheckSalary())
            {
                // Check if the person has an active contract               
                if (perm.Hired.LastActiveContract() == null)
                {
                    dal.Insert<Contract>(perm);
                    Commit();
                }
                else throw new ServiceException("Permanent contract already exists.");
            }
            else throw new ServiceException("Permanent contract data is not correct.");
        }

        public Parcel FindParcelById(string cadas) //implement
        {
            if (dal.GetById<Parcel>(cadas) != null)
            {
                return dal.GetById<Parcel>(cadas);
            }
            else throw new ServiceException("Parcel looked for not exists");
        }

        public void AddPermanent(string bankAccount, DateTime initialDate, string SSN, Person hired, double salary)
        {
            Permanent perm = new Permanent(bankAccount, initialDate, SSN, hired, salary);
            AddPermanent(perm);
        }

        public void AddTemporary(Temporary temp)
        {
            // Check if there is non-valid information
            if (temp.CheckBanckAccount() && temp.CheckSSN() && temp.CheckFinalDate())
            {
                // Check if the person has an active contract                
                if (temp.Hired.LastActiveContract() == null)
                {
                    dal.Insert<Contract>(temp);
                    Commit();
                }
                else throw new ServiceException("Temporary contract already exists.");
            }
            else throw new ServiceException("Temporary contract data is not correct.");
        }
        public void AddTemporary(string bankAccount, DateTime initialDate, string SSN, Person hired)
        {
            Temporary temp = new Temporary(bankAccount, initialDate, SSN, hired);
            AddTemporary(temp);
        }



        public List<Group> GetAllGroups() //implement
        {
            return new List<Group>(dal.GetAll<Group>());
        }

        public Truck FindTruckById(string id) //implement
        {
            if (dal.GetById<Truck>(id) != null)
            {
                return dal.GetById<Truck>(id);
            }
            else throw new ServiceException("Truck with  " + id + " does not exist");
        }



        public Person FindPersonById(string id)
        {
            if (dal.GetById<Person>(id) != null)
            {
                return dal.GetById<Person>(id);
            }
            else throw new ServiceException("Person with id " + id + " does not exist");
        }

        public void AddGroup(Group g)
        {
            dal.Insert<Group>(g);
            Commit();
        }

        public List<Truck> GetAllTrucks()
        {
            return new List<Truck>(dal.GetAll<Truck>());
        }

        public List<Trip> GetAllTrips()
        {
            return new List<Trip>(dal.GetAll<Trip>());
        }

        public void AddCrate(Crate crate)
        {
            if (dal.GetById<Crate>(crate.Id) == null)
            {
                dal.Insert<Crate>(crate);
                dal.Commit();
            }
            else throw new ServiceException("Crate with Id " + crate.Id + " already exists.");
        }

        public List<Crate> GetAllCrates()
        {
            return new List<Crate>(dal.GetAll<Crate>());
        }


        public List<Parcel> ShowParcels()
        {
            return new List<Parcel>(dal.GetAll<Parcel>());
        }

        public List<Trip> GetTruckTrips(string plateNumber, DateTime startDate, DateTime endDate)
        {
            Truck t = dal.GetById<Truck>(plateNumber);
            if (t != null)
            {
                List<Trip> res = (List<Trip>)t.Trips;
                return TripsBetweenDates(startDate, endDate, res);
            }
            else throw new ServiceException("Truck with Id " + plateNumber + " does not exist.");
        }

        List<Trip> TripsBetweenDates(DateTime startDate, DateTime endDate, List<Trip> list)
        {
            List<Trip> result = new List<Trip>();
            if (DateTime.Compare(startDate, endDate) <= 0)
            {
                foreach (Trip trip in list)
                {
                    List<Crate> crates = (List<Crate>)trip.Crates;
                    Crate c = crates.Last();
                    Group g = c.Group;
                    DateTime date = (DateTime)g.Date;
                    if (date != null)
                    {
                        if (DateTime.Compare(startDate, date) <= 0 && DateTime.Compare(date, endDate) <= 0)
                        {
                            result.Add(trip);
                        }
                    }
                }
                return result;
            }
            else throw new ServiceException("Start date is posterior to end date, please choose other dates.");
        }


        void AssignTripToTruck(string plateNumber)
        {
            Truck t = dal.GetById<Truck>(plateNumber);
            if (t != null)
            {
                Trip temp = new Trip(t);
                t.Trips.Add(temp);
                Commit();
            }
            else throw new ServiceException("Truck with Id " + plateNumber + " does not exist.");
        }


        public void AddCrateToTrip(Product product, Double Weigth, String workerId, Parcel parcel, String plate)
        {

            //Checks if parcel exists
            Parcel parcell = FindParcelById(parcel.CadastralReference);

            //Checks if person exists
            Person worker = FindPersonById(workerId);

            //Group where worker with dni provided is working in selected parcel
            Group workingGroup = worker.BelongsToGroupInParcel(parcell);
            if (workingGroup == null)
                throw new ServiceException("Worker with Id " + workerId + "is not working on any group of the parcel " + parcell.Name);

            //Check active contract (But this is also checked when cheking the group)
            Contract activeContract = worker.LastActiveContract();
            if (activeContract == null || !activeContract.IsInGroup(workingGroup))
                throw new ServiceException("Worker with Id " + workerId + "has not any active contract in a group of parcel " + parcell.Name);

            //Check plate number
            Truck truck = FindTruckById(plate);

            //Check if after adding the crate, we do not overflow maximum weigth of the truck
            //Trip lastTrip = GetTruckTrips(plate, DateTime.Today, DateTime.Today).Last();
            Trip lastTrip = truck.GetLastTrip();
            if (lastTrip.ParcelExit == null) throw new ServiceException("No parcelExit time assigned to trip");
            if (lastTrip.CarriedWeight + Weigth > truck.MaximunWeight && DateTime.Compare((DateTime)lastTrip.ParcelExit, DateTime.Today) >= 0)
            {
                throw new ServiceException("Weigth of the product added to the trip exceeds the maxmimum capacity of truck");
            }
            else
            {
                Crate crateToAdd = new Crate(product, Weigth, activeContract, workingGroup, lastTrip);
                AddCrate(crateToAdd);

                //Make the insertion consistent
                lastTrip.Crates.Add(crateToAdd);
                activeContract.Crates.Add(crateToAdd);
                workingGroup.Crates.Add(crateToAdd);
            }

        }

        public void CheckNoGroupSameDayAndParcel(Group toInsert)
        {

            ICollection<Group> groupsInParcel = toInsert.Parcel.Groups;

            foreach (Group g in groupsInParcel)
            {
                if (g.Date == toInsert.Date) { throw new ServiceException("A group in parcel" + toInsert.Parcel.Name + " and date " + toInsert.Date + " already exists"); }
            }

        }

        public void CheckNoRepeatedWorkersInGroup(Group toInsert)
        {

            List<Contract> tmp = new List<Contract>();

            foreach (Contract c in toInsert.Members)
            {
                if (tmp.Contains(c)) { throw new ServiceException("Worker " + c.Id + " already belongs to the group"); }
                tmp.Add(c);
            }

        }

        public void CheckNoWorkerInOtherGroups(Group toInsert)
        {

            foreach (Contract c in toInsert.Members)
            {
                foreach (Group g in c.Groups)
                {
                    if (g.Date == toInsert.Date && !g.Equals(toInsert)) { throw new ServiceException("Worker " + c.Id + " is already in a group on day " + toInsert.Date); }
                }
            }
        }

    }
}
