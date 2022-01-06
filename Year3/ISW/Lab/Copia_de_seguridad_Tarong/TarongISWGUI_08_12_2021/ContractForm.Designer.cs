using System;

namespace TarongISWGUI
{
    partial class ContractForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.panel1 = new System.Windows.Forms.Panel();
            this.finalDate_dateP = new System.Windows.Forms.DateTimePicker();
            this.salary_numb = new System.Windows.Forms.NumericUpDown();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.cancel_b = new System.Windows.Forms.Button();
            this.create_b = new System.Windows.Forms.Button();
            this.permanent_radiob = new System.Windows.Forms.RadioButton();
            this.temporary_radiob = new System.Windows.Forms.RadioButton();
            this.label4 = new System.Windows.Forms.Label();
            this.ssn_txtb = new System.Windows.Forms.TextBox();
            this.bankAccount_txtb = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.initialDate_dateP = new System.Windows.Forms.DateTimePicker();
            this.label2 = new System.Windows.Forms.Label();
            this.newPerson_b = new System.Windows.Forms.Button();
            this.dni_cb = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.panel2 = new System.Windows.Forms.Panel();
            this.label5 = new System.Windows.Forms.Label();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.salary_numb)).BeginInit();
            this.panel2.SuspendLayout();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.panel1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(0)))), ((int)(((byte)(64)))));
            this.panel1.Controls.Add(this.finalDate_dateP);
            this.panel1.Controls.Add(this.salary_numb);
            this.panel1.Controls.Add(this.label7);
            this.panel1.Controls.Add(this.label6);
            this.panel1.Controls.Add(this.cancel_b);
            this.panel1.Controls.Add(this.create_b);
            this.panel1.Controls.Add(this.permanent_radiob);
            this.panel1.Controls.Add(this.temporary_radiob);
            this.panel1.Controls.Add(this.label4);
            this.panel1.Controls.Add(this.ssn_txtb);
            this.panel1.Controls.Add(this.bankAccount_txtb);
            this.panel1.Controls.Add(this.label3);
            this.panel1.Controls.Add(this.initialDate_dateP);
            this.panel1.Controls.Add(this.label2);
            this.panel1.Controls.Add(this.newPerson_b);
            this.panel1.Controls.Add(this.dni_cb);
            this.panel1.Controls.Add(this.label1);
            this.panel1.Location = new System.Drawing.Point(0, 75);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(478, 493);
            this.panel1.TabIndex = 0;
            // 
            // finalDate_dateP
            // 
            this.finalDate_dateP.Enabled = false;
            this.finalDate_dateP.Location = new System.Drawing.Point(188, 377);
            this.finalDate_dateP.Name = "finalDate_dateP";
            this.finalDate_dateP.Size = new System.Drawing.Size(200, 20);
            this.finalDate_dateP.TabIndex = 16;
            // 
            // salary_numb
            // 
            this.salary_numb.Location = new System.Drawing.Point(188, 344);
            this.salary_numb.Maximum = new decimal(new int[] {
            -1593835521,
            466537709,
            54210,
            0});
            this.salary_numb.Name = "salary_numb";
            this.salary_numb.Size = new System.Drawing.Size(120, 20);
            this.salary_numb.TabIndex = 15;
            this.salary_numb.ValueChanged += new System.EventHandler(this.salary_numb_ValueChanged);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label7.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.label7.Location = new System.Drawing.Point(81, 378);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(82, 20);
            this.label7.TabIndex = 14;
            this.label7.Text = "Final Date";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.label6.Location = new System.Drawing.Point(105, 341);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(53, 20);
            this.label6.TabIndex = 13;
            this.label6.Text = "Salary";
            // 
            // cancel_b
            // 
            this.cancel_b.Location = new System.Drawing.Point(371, 436);
            this.cancel_b.Name = "cancel_b";
            this.cancel_b.Size = new System.Drawing.Size(75, 23);
            this.cancel_b.TabIndex = 12;
            this.cancel_b.Text = "Cancel";
            this.cancel_b.UseVisualStyleBackColor = true;
            this.cancel_b.Click += new System.EventHandler(this.cancel_b_Click);
            // 
            // create_b
            // 
            this.create_b.Location = new System.Drawing.Point(281, 436);
            this.create_b.Name = "create_b";
            this.create_b.Size = new System.Drawing.Size(75, 23);
            this.create_b.TabIndex = 11;
            this.create_b.Text = "Create";
            this.create_b.UseVisualStyleBackColor = true;
            this.create_b.Click += new System.EventHandler(this.create_b_Click);
            // 
            // permanent_radiob
            // 
            this.permanent_radiob.AutoSize = true;
            this.permanent_radiob.Checked = true;
            this.permanent_radiob.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.permanent_radiob.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.permanent_radiob.Location = new System.Drawing.Point(188, 244);
            this.permanent_radiob.Name = "permanent_radiob";
            this.permanent_radiob.Size = new System.Drawing.Size(105, 24);
            this.permanent_radiob.TabIndex = 10;
            this.permanent_radiob.TabStop = true;
            this.permanent_radiob.Text = "Permanent";
            this.permanent_radiob.UseVisualStyleBackColor = true;
            this.permanent_radiob.CheckedChanged += new System.EventHandler(this.permanent_radiob_CheckedChanged);
            // 
            // temporary_radiob
            // 
            this.temporary_radiob.AutoSize = true;
            this.temporary_radiob.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.temporary_radiob.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.temporary_radiob.Location = new System.Drawing.Point(188, 283);
            this.temporary_radiob.Name = "temporary_radiob";
            this.temporary_radiob.Size = new System.Drawing.Size(102, 24);
            this.temporary_radiob.TabIndex = 9;
            this.temporary_radiob.TabStop = true;
            this.temporary_radiob.Text = "Temporary";
            this.temporary_radiob.UseVisualStyleBackColor = true;
            this.temporary_radiob.CheckedChanged += new System.EventHandler(this.temporary_radiob_CheckedChanged);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.label4.Location = new System.Drawing.Point(78, 183);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(85, 20);
            this.label4.TabIndex = 8;
            this.label4.Text = "Initial Date";
            // 
            // ssn_txtb
            // 
            this.ssn_txtb.Location = new System.Drawing.Point(188, 122);
            this.ssn_txtb.Name = "ssn_txtb";
            this.ssn_txtb.Size = new System.Drawing.Size(100, 20);
            this.ssn_txtb.TabIndex = 7;
            // 
            // bankAccount_txtb
            // 
            this.bankAccount_txtb.Location = new System.Drawing.Point(188, 89);
            this.bankAccount_txtb.Name = "bankAccount_txtb";
            this.bankAccount_txtb.Size = new System.Drawing.Size(187, 20);
            this.bankAccount_txtb.TabIndex = 6;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.label3.Location = new System.Drawing.Point(121, 122);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(42, 20);
            this.label3.TabIndex = 5;
            this.label3.Text = "SSN";
            // 
            // initialDate_dateP
            // 
            this.initialDate_dateP.Location = new System.Drawing.Point(188, 182);
            this.initialDate_dateP.Name = "initialDate_dateP";
            this.initialDate_dateP.Size = new System.Drawing.Size(200, 20);
            this.initialDate_dateP.TabIndex = 4;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.label2.Location = new System.Drawing.Point(50, 87);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(105, 20);
            this.label2.TabIndex = 3;
            this.label2.Text = "BankAccount";
            // 
            // newPerson_b
            // 
            this.newPerson_b.Location = new System.Drawing.Point(336, 57);
            this.newPerson_b.Name = "newPerson_b";
            this.newPerson_b.Size = new System.Drawing.Size(52, 23);
            this.newPerson_b.TabIndex = 2;
            this.newPerson_b.Text = "New ";
            this.newPerson_b.UseVisualStyleBackColor = true;
            // 
            // dni_cb
            // 
            this.dni_cb.FormattingEnabled = true;
            this.dni_cb.Location = new System.Drawing.Point(188, 59);
            this.dni_cb.Name = "dni_cb";
            this.dni_cb.Size = new System.Drawing.Size(121, 21);
            this.dni_cb.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.label1.Location = new System.Drawing.Point(121, 60);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(37, 20);
            this.label1.TabIndex = 0;
            this.label1.Text = "DNI";
            // 
            // panel2
            // 
            this.panel2.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.panel2.BackColor = System.Drawing.Color.Teal;
            this.panel2.Controls.Add(this.label5);
            this.panel2.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.panel2.Location = new System.Drawing.Point(0, 0);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(478, 77);
            this.panel2.TabIndex = 1;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(26, 21);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(330, 37);
            this.label5.TabIndex = 0;
            this.label5.Text = "Create a new contract";
            // 
            // ContractForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
            this.AutoValidate = System.Windows.Forms.AutoValidate.EnableAllowFocusChange;
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Center;
            this.ClientSize = new System.Drawing.Size(478, 568);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.panel2);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "ContractForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "ContractForm";
            this.Load += new System.EventHandler(this.ContractForm_Load);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.salary_numb)).EndInit();
            this.panel2.ResumeLayout(false);
            this.panel2.PerformLayout();
            this.ResumeLayout(false);

        }

        

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.DateTimePicker initialDate_dateP;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button newPerson_b;
        private System.Windows.Forms.ComboBox dni_cb;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox ssn_txtb;
        private System.Windows.Forms.TextBox bankAccount_txtb;
        private System.Windows.Forms.RadioButton permanent_radiob;
        private System.Windows.Forms.RadioButton temporary_radiob;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Button cancel_b;
        private System.Windows.Forms.Button create_b;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.DateTimePicker finalDate_dateP;
        private System.Windows.Forms.NumericUpDown salary_numb;
    }
}
