#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>


/* Funcion f(x) de la cual se quiere calcular la integral */
double f(double x)
{
//   return pow(10*x-14.0/3.0,3)-150*x+120;
   return M_PI/2.0*sin(x*M_PI);
}

/* Calculo de la integral de la funcion f. Variante 1 */
double calcula_integral1(double a, double b, int n)
{
   double h, s=0, result;
   int i;

   h=(b-a)/n;
  #pragma omp parallel for reduction(+:s)
   for (i=0; i<n; i++) {
      s+=f(a+h*(i+0.5));
   }

   result = h*s;
   return result;
}

/* Calculo de la integral de la funcion f. Variante 2 */
double calcula_integral2(double a, double b, int n)
{
   double x, h, s=0, result;
   int i;

   h=(b-a)/n;
  #pragma omp parallel for private(x) reduction(+:s)
   for (i=0; i<n; i++) {
      x=a;
      x+=h*(i+0.5);

      s+=f(x);
   }

   result = h*s;
   return result;
}

int main(int argc, char *argv[])
{
   // variables to mesure the speedup
   double t1, t2;
   double a, b, result;
   int n, variante;

   if (argc<2) {
      fprintf(stderr, "Numero de argumentos incorrecto\n");
      return 1;
   }
   if (argc>2) n=atoi(argv[2]);
   else n=100000;
   a=0;
   b=1;
   t1 = omp_get_wtime();
   variante=atoi(argv[1]);
   switch (variante) {
      case 1:
      //Threads for printing the number
     #pragma omp parallel
     {
        //Print number of threads
        //#pragma omp single
        printf("Number of threads: %d\n", omp_get_num_threads());
     } 
         result = calcula_integral1(a,b,n);
         break;
      case 2:
      #pragma omp parallel
      {
        //Print number of threads
        #pragma omp single
        printf("Number of threads: %d\n", omp_get_num_threads());
     } 
         result = calcula_integral2(a,b,n);
         break;
      default:
         fprintf(stderr, "Numero de variante incorrecto\n");
         return 1;
   }

   printf("Valor de la integral = %.12f\n", result);

   // t2
   t2 = omp_get_wtime();
   // print the execution time
   printf("Elapsed time: %f\n", t2-t1);
   return 0;
}
