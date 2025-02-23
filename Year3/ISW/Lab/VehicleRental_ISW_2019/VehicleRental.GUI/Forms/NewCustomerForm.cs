﻿using System;
using VehicleRental.Services;
using VehicleRental.Domain;

using System.Windows.Forms;

namespace VehicleRental.Presentation
{
    class NewCustomerForm : NewPersonForm
    {
        private Label label7;
        private Label label8;
        private Label label9;
        private DateTimePicker expCCdateTimePicker;
        private Label label10;
        private TextBox cvcCCtextBox;
        private Label label11;
        private TextBox typeCCtextBox;
        private Button searchButton;
        private TextBox numberCCtextBox;

        public NewCustomerForm() : base()
        {
            InitializeComponent();
        }

        public NewCustomerForm(NewReservationForm newReservationForm, IVehicleRentalService service) : base(newReservationForm, service)
        {
            InitializeComponent();
        }
        public override void Clear()
        {
            base.Clear();
            numberCCtextBox.Clear();
            cvcCCtextBox.Clear();
            typeCCtextBox.Clear();
        }

        protected override bool fieldsOK()
        {
            int result;
            return
                base.fieldsOK() &&
                !string.IsNullOrEmpty(numberCCtextBox.Text) &&
                !string.IsNullOrEmpty(cvcCCtextBox.Text) &&
                !string.IsNullOrEmpty(typeCCtextBox.Text) &&
                int.TryParse(cvcCCtextBox.Text, out result);

        }
        protected override void addButton_Click(object sender, EventArgs e)
        {
            if (fieldsOK())
            {
                /*
                Person person = service.findPersonByDni(dnitextBox.Text);
                if (person is Customer)
                {
                    MessageBox.Show("Customer with this DNI already exists", "Error");
                    return;
                }
                CreditCard cc = new CreditCard(numberCCtextBox.Text, expCCdateTimePicker.Value.Month, expCCdateTimePicker.Value.Year,
                    Convert.ToInt32(cvcCCtextBox.Text), typeCCtextBox.Text);
                if (service.findCreditCardByNumber(numberCCtextBox.Text) != null)
                {
                    MessageBox.Show("Credit Card Number already exists", "Error");
                    return;
                }
                Customer customer;
                if (person != null) customer = service.migratePersonToCustomer(person, DateTime.Now, cc);
                else
                {
                    customer = new Customer(dnitextBox.Text, nametextBox.Text, addresstextBox.Text, citytextBox.Text, postalcodetextBox.Text,
                        driverLicensedateTimePicker.Value, DateTime.Now, cc);
                    service.addPerson(customer);
                }
                newReservationForm.addCustomerFormConfirmed(customer);
                this.Close();
                */
                
                if (service.findCustomerByDni(dnitextBox.Text) != null)
                    MessageBox.Show("Customer with this DNI already exists", "Error");
                else
                if(service.findCreditCardByNumber(numberCCtextBox.Text)!= null)
                    MessageBox.Show("Credit Card Number already exists", "Error");
                else
                {
                    Customer cust = new Customer(dnitextBox.Text, nametextBox.Text, addresstextBox.Text, citytextBox.Text, postalcodetextBox.Text, 
                    driverLicensedateTimePicker.Value, DateTime.Now, new CreditCard(numberCCtextBox.Text, expCCdateTimePicker.Value.Month, 
                    expCCdateTimePicker.Value.Year, Convert.ToInt32(cvcCCtextBox.Text), typeCCtextBox.Text));
                    newReservationForm.addCustomerFormConfirmed(cust);

                }
                
            }
            else
            {
                int result;

                if (!int.TryParse(cvcCCtextBox.Text, out result))
                    MessageBox.Show("CVC must be a number", "Error");
                else
                    MessageBox.Show("Missing Fields", "Error");
            }
        }

        private void InitializeComponent()
        {
            this.label7 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.numberCCtextBox = new System.Windows.Forms.TextBox();
            this.label9 = new System.Windows.Forms.Label();
            this.expCCdateTimePicker = new System.Windows.Forms.DateTimePicker();
            this.label10 = new System.Windows.Forms.Label();
            this.cvcCCtextBox = new System.Windows.Forms.TextBox();
            this.label11 = new System.Windows.Forms.Label();
            this.typeCCtextBox = new System.Windows.Forms.TextBox();
            this.searchButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // dnitextBox
            // 
            this.dnitextBox.Margin = new System.Windows.Forms.Padding(6, 8, 6, 8);
            // 
            // nametextBox
            // 
            this.nametextBox.Margin = new System.Windows.Forms.Padding(6, 8, 6, 8);
            // 
            // addresstextBox
            // 
            this.addresstextBox.Margin = new System.Windows.Forms.Padding(6, 8, 6, 8);
            // 
            // citytextBox
            // 
            this.citytextBox.Margin = new System.Windows.Forms.Padding(6, 8, 6, 8);
            // 
            // postalcodetextBox
            // 
            this.postalcodetextBox.Margin = new System.Windows.Forms.Padding(6, 8, 6, 8);
            // 
            // addPersonButton
            // 
            this.addPersonButton.Location = new System.Drawing.Point(318, 729);
            this.addPersonButton.Margin = new System.Windows.Forms.Padding(6, 8, 6, 8);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(14, 446);
            this.label7.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(89, 20);
            this.label7.TabIndex = 13;
            this.label7.Text = "Credit Card";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(56, 491);
            this.label8.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(65, 20);
            this.label8.TabIndex = 14;
            this.label8.Text = "Number";
            // 
            // numberCCtextBox
            // 
            this.numberCCtextBox.Location = new System.Drawing.Point(130, 486);
            this.numberCCtextBox.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.numberCCtextBox.Name = "numberCCtextBox";
            this.numberCCtextBox.Size = new System.Drawing.Size(163, 26);
            this.numberCCtextBox.TabIndex = 15;
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(40, 548);
            this.label9.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(79, 20);
            this.label9.TabIndex = 16;
            this.label9.Text = "Exp. Date";
            // 
            // expCCdateTimePicker
            // 
            this.expCCdateTimePicker.Location = new System.Drawing.Point(130, 538);
            this.expCCdateTimePicker.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.expCCdateTimePicker.Name = "expCCdateTimePicker";
            this.expCCdateTimePicker.Size = new System.Drawing.Size(298, 26);
            this.expCCdateTimePicker.TabIndex = 17;
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(80, 603);
            this.label10.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(42, 20);
            this.label10.TabIndex = 18;
            this.label10.Text = "CVC";
            // 
            // cvcCCtextBox
            // 
            this.cvcCCtextBox.Location = new System.Drawing.Point(130, 598);
            this.cvcCCtextBox.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.cvcCCtextBox.Name = "cvcCCtextBox";
            this.cvcCCtextBox.Size = new System.Drawing.Size(46, 26);
            this.cvcCCtextBox.TabIndex = 19;
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(45, 662);
            this.label11.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(81, 20);
            this.label11.TabIndex = 20;
            this.label11.Text = "Card Type";
            // 
            // typeCCtextBox
            // 
            this.typeCCtextBox.Location = new System.Drawing.Point(130, 657);
            this.typeCCtextBox.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.typeCCtextBox.Name = "typeCCtextBox";
            this.typeCCtextBox.Size = new System.Drawing.Size(148, 26);
            this.typeCCtextBox.TabIndex = 22;
            // 
            // searchButton
            // 
            this.searchButton.Location = new System.Drawing.Point(303, 43);
            this.searchButton.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.searchButton.Name = "searchButton";
            this.searchButton.Size = new System.Drawing.Size(112, 35);
            this.searchButton.TabIndex = 23;
            this.searchButton.Text = "Search";
            this.searchButton.UseVisualStyleBackColor = true;
            this.searchButton.Click += new System.EventHandler(this.searchButton_Click);
            // 
            // NewCustomerForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.ClientSize = new System.Drawing.Size(486, 783);
            this.Controls.Add(this.searchButton);
            this.Controls.Add(this.typeCCtextBox);
            this.Controls.Add(this.label11);
            this.Controls.Add(this.cvcCCtextBox);
            this.Controls.Add(this.label10);
            this.Controls.Add(this.expCCdateTimePicker);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.numberCCtextBox);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.label7);
            this.Margin = new System.Windows.Forms.Padding(6, 8, 6, 8);
            this.Name = "NewCustomerForm";
            this.Text = "New Customer";
            this.Controls.SetChildIndex(this.dnitextBox, 0);
            this.Controls.SetChildIndex(this.nametextBox, 0);
            this.Controls.SetChildIndex(this.addresstextBox, 0);
            this.Controls.SetChildIndex(this.citytextBox, 0);
            this.Controls.SetChildIndex(this.postalcodetextBox, 0);
            this.Controls.SetChildIndex(this.driverLicensedateTimePicker, 0);
            this.Controls.SetChildIndex(this.addPersonButton, 0);
            this.Controls.SetChildIndex(this.label7, 0);
            this.Controls.SetChildIndex(this.label8, 0);
            this.Controls.SetChildIndex(this.numberCCtextBox, 0);
            this.Controls.SetChildIndex(this.label9, 0);
            this.Controls.SetChildIndex(this.expCCdateTimePicker, 0);
            this.Controls.SetChildIndex(this.label10, 0);
            this.Controls.SetChildIndex(this.cvcCCtextBox, 0);
            this.Controls.SetChildIndex(this.label11, 0);
            this.Controls.SetChildIndex(this.typeCCtextBox, 0);
            this.Controls.SetChildIndex(this.searchButton, 0);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        private void searchButton_Click(object sender, EventArgs e)
        {
            string dni = dnitextBox.Text;

            //CODIGO SEARCH
            if (!string.IsNullOrEmpty(dni))
            {
                Person p = service.findPersonByDni(dni);
                if (p!=null) //It is a person who is not a customer yet
                {
                    if (p is Customer)
                    {
                        MessageBox.Show("A Customer already exists with this DNI", "Error");
                    }
                    else
                    {
                        nametextBox.Text = p.Name;
                        addresstextBox.Text = p.Address;
                        citytextBox.Text = p.City;
                        postalcodetextBox.Text = p.PostalCode;
                        driverLicensedateTimePicker.Value = p.DateDriverLicense;
                    }
                }
                else
                {
                    MessageBox.Show("No Person found with this DNI", "Info");
                }
            }
        }
    }
}
