#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {   
     // To be completed
     int i = 0;
     while (i < argc) {
          // strcmp() funtion compares two strings
     	if (strcmp(argv[i], "-c" ) == 0) {
     		printf("Argument %d is: %s\n", i, "Compile");
     	} else if(strcmp(argv[i], "-E" ) == 0){
     		printf("Argument %d is: %s\n", i, "Preprocess");
     	} else if (strcmp(argv[i], "-i" ) == 0){
     		printf("Argument %d is: %s\n", i, "Include /includes");
     		
     	} else {
     		printf("Argument %d is: %s\n", i, argv[i]);
     	}
     	i++;
     }

}