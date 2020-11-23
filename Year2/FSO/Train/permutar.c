#include <stdio.h>

void permutar(double *x, double *i)
 {
  double temp;
  temp = *x; 
  *x = *i; 
  *i = temp;
}
int main(void) {
	double a=1.0, b=2.0; 
	printf("a = %f, b = %f\n", a ,b);
	permutar(&a, &b);
	printf("a = %f, b = %f\n", a , b);
}
