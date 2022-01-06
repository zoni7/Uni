using System;
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
            if (!person.Id.CheckDni())) throw new ServiceException("DNI is not correct.");
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
            if (!dal.GetWhere<Parcel>(x => x.Name == parcel.Name).Any())
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

        #region Permanent
        public void AddPermanent(Permanent perm)
        {
            // If person doesn't exist -> create a new one
            if (!personExists(perm.Hired.Id)) AddPerson(perm.Hired);
            // Check if there is non-valid information
            if (perm.CheckBanckAccount() && perm.CheckSSN() && perm.CheckSalary())
            {
                // Check if the person has an active contract
                if (dal.GetById<Permanent>(perm.Id) == null)
                {
                    dal.Insert<Permanent>(perm);
                    Commit();
                }
                else throw new ServiceException("Permanent contract already exists.");

            }
            else throw new ServiceException("Permanent contract data is not correct.");
        }
        public void AddPermanent(string bankAccount, DateTime initialDate, string SSN, Person hired, double salary)
        {
            Permanent perm = new Permanent(bankAccount, initialDate, SSN, hired, salary);
            AddPermanent(perm);
        }
        #endregion
        #region Temporary
        public void AddTemporary(Temporary temp)
        {
            // If person doesn't exist -> create a new one
            if (!personExists(temp.Hired.Id)) AddPerson(temp.Hired);
            // Check if there is non-valid information
            if (temp.CheckBanckAccount() && temp.CheckSSN() && temp.CheckFinalDate())
            {
                // Check if the person has an active contract
                if (dal.GetById<Temporary>(temp.Id) == null)
                {
                    dal.Insert<Temporary>(temp);
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
        #endregion

        // Verify if a person exixts by providing its dni
        private bool personExists(string dni)
        {            

            bool res = dal.Exists<Person>(dni);
            if (res == false)
            {
                return false;
            }
            else return true;

        }

        
    }
}
