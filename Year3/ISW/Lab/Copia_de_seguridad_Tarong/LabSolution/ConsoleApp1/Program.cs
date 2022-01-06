using System;
using System.Collections.Generic;


namespace ConsoleApp1
{
    class Program
    {
        private static List<string> names;
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");


            names = new List<string>() {
                "Joan", "Jorge", "Alberto", "Dani"
            };



            foreach (string n in names) {
                Console.WriteLine("Hola " + n);

            }
        }
    }
}
