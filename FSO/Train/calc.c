#include <stdio.h>
#include <math.h>



int main() 
{

	char operator;
	int num1;
	int num2;

	printf("Witch operator do yo want to use ? \nYou have between these options /, *, -, +");
	scanf("%c" , &operator);
	printf("enter the fisrt num : ");
	scanf("%d", &num1);
	printf("enter the last num : ");
	scanf("%d", &num2);


	// here goes the different operations
	if (operator == '/') {
		printf("%d", num1 / num2);

	} else if (operator == '*') {
		printf("%d", num1 * num2);

	} else if (operator == '-') {
		printf("%d", num1 - num2);

	} else if (operator == '+') {
		printf("%d", num1 + num2);
	} else if (operator == 'p') {
		printf("%f", pow(num1,num2));
	} else {
		printf("invalid input");
	}

	


}
