#include <stdio.h>
#include <math.h>
#include <limits.h>
#include <omp.h>

typedef unsigned long long Entero_grande;
#define ENTERO_MAS_GRANDE  ULLONG_MAX

int primo(Entero_grande n)
{
  volatile int p;
  int myid, numt;
  Entero_grande i, s;

  p = (n % 2 != 0 || n == 2);

  if (p) {  	  	  	  	  	      
	  s = sqrt(n);
    #pragma omp parallel 
	  {
      myid = omp_get_thread_num();
	    numt = omp_get_num_threads();
	    for (i = 3+2*myid; p && i <= s -(numt- myid); i += (2*numt)) {
	      if (n % i == 0) p = 0;	 
	    }  	  
  	}
  }

  return p;
  
}

int main()
{
double t1, t2;
  Entero_grande n;
  t1 = omp_get_wtime();    
  for (n = ENTERO_MAS_GRANDE; !primo(n); n -= 2) {
    /* NADA */
  }

  printf("El mayor primo que cabe en %lu bytes es %llu.\n",
         sizeof(Entero_grande), n);
 t2 = omp_get_wtime();
 printf("Elapsed time: %f\n", t2-t1);

  return 0;
}
