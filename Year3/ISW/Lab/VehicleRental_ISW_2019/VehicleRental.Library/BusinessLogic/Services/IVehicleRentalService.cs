using System;
using System.Collections.Generic;
using VehicleRental.Domain;

namespace VehicleRental.Services
{
    public interface IVehicleRentalService
    {
        void addBranchOffice(BranchOffice br);
        void addCategory(Category cat);
        void addPerson(Person person);
        void addReservation(Reservation reservation);
        void addReservation(Customer customer, BranchOffice pickUpOffice, DateTime pickupDate, BranchOffice returnOffice, DateTime returnDate, Category cat, IEnumerable<Person> drivers);
        IList<BranchOffice> findAllBranchOffices();
        IList<Category> findAllCategories();
        IList<Customer> findAllCustomers();
        IList<Person> findAllPersons();
        CreditCard findCreditCardByNumber(string number);
        Customer findCustomerByDni(string dni);
        Person findPersonByDni(string dni);
        IList<Reservation> findReservationsbyCustomerID(string customerDNI);
        Customer migratePersonToCustomer(Person person, DateTime registrationDate, CreditCard cc);
        void removeAllData();
    }
}