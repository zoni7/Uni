#include <stdio.h>

#define PI 3.1416

float areaC(int radius) {
	float area = PI * (radius*radius);
	return area;
}

int main() {   
   float area, radius;
   radius = 10;   
   area = areaC(radius);
   printf("Area of circle with radius %f is %f \n", radius, area); 
}




