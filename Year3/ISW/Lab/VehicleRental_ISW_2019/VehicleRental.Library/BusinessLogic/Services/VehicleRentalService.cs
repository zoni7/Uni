using System;
using System.Collections.Generic;
using VehicleRental.Domain;
using VehicleRental.Persistence;

namespace VehicleRental.Services
{

    public class VehicleRentalService : IVehicleRentalService
    {
        private readonly IDAL dal;
        public VehicleRentalService(IDAL dal)
        {
            this.dal = dal;
        }

        public void removeAllData()
        {
            dal.Clear<BranchOffice>();
            dal.Clear<Category>();
            dal.Clear<Reservation>();
            dal.Clear<Person>();
            dal.Clear<Customer>();
            dal.Clear<CreditCard>();
            dal.Commit();
        }

        # region Reservation
        public IList<Reservation> findReservationsbyCustomerID(string customerDNI)
        {
            List<Reservation> reservations = new List<Reservation>();
            foreach (Reservation res in dal.GetAll<Reservation>())
            {
                if (res.Customer.Dni.Equals(customerDNI))
                {
                    reservations.Add(res);
                }
            }
            return reservations;
        }

        public void addReservation(Reservation reservation)
        {
            dal.Insert(reservation);
            dal.Commit();
        }
        public void addReservation(Customer customer, BranchOffice pickUpOffice, DateTime pickupDate, 
            BranchOffice returnOffice, DateTime returnDate, Category cat, IEnumerable<Person> drivers)
        {
            Reservation res = new Reservation(customer, pickUpOffice, pickupDate, returnOffice, returnDate, cat, drivers);
            //Adding reservation to DB
            addReservation(res);
        }
        #endregion

        #region Customer
        public IList<Customer> findAllCustomers()
        {
            return new List<Customer>(dal.GetAll<Customer>());
        }

        public Customer findCustomerByDni(string dni)
        {
            return dal.GetById<Customer>(dni);
        }
        
        public Customer migratePersonToCustomer(Person person, DateTime registrationDate, CreditCard cc)
        {

            Customer customer = new Customer(person.Dni, person.Name, person.Address, person.City, person.PostalCode,
                person.DateDriverLicense, registrationDate, cc);
            foreach (Reservation reservation in person.Reservations)
                customer.Reservations.Add(reservation);
            dal.Delete(person);
            dal.Insert(customer);
            dal.Commit();
            return customer;
        }
        #endregion

        #region Person
        public IList<Person> findAllPersons()
        {
            return new List<Person>(dal.GetAll<Person>());
        }
        
        public Person findPersonByDni(string dni)
        {
            return dal.GetById<Person>(dni);
        }

        public void addPerson(Person person)
        {
            if (dal.GetById<Person>(person.Dni) == null)
            {
                dal.Insert<Person>(person);
                dal.Commit();
            }
            else throw new ServiceException("Person already exists.");
        }
        #endregion

        #region BranchOffice
        public IList<BranchOffice> findAllBranchOffices()
        {
            return new List<BranchOffice>(dal.GetAll<BranchOffice>());
        }

        public void addBranchOffice(BranchOffice br)
        {
            dal.Insert<BranchOffice>(br);
            dal.Commit();
        }
        #endregion

        #region Category
        public IList<Category> findAllCategories()
        {
            return new List<Category>(dal.GetAll<Category>());
        }

        public void addCategory(Category cat)
        {
            dal.Insert<Category>(cat);
            dal.Commit();
        }
        #endregion

        #region CreditCard
        public CreditCard findCreditCardByNumber(string number)
        {
            return dal.GetById<CreditCard>(number);
        }
        #endregion
    }
}
