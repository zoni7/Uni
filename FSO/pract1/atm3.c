
#include <stdio.h>
#include <string.h>
#include <stdlib.h>


//#define InitBalance 1000
float Balance;




int main()
{ 

  int operation;
  float income, withdraw;
  char saldoQuest[20];
  char name[20];
  char surName[20];
  char passdword[20];
  
  char InitBalance[20];
  
  FILE * fpointerR = fopen("registers.txt", "r");
  
  
  
  printf("\nWelcome to the FSO ATM\n");
  // IDENTIFICATION
  printf("\nFirst of all, who are you ?\n Tell us your name\n ");
  scanf("%s",name);
  printf("\n And your surname \n");
  scanf("%s",surName);
  printf("\n Your passdword is ... \n");
  scanf("%s",passdword);

// ask for the username
  if ((strcmp(name, "Joan") == 0) && (strcmp(surName, "Matarredona") == 0) && (strcmp(passdword, "passdword") == 0)) {

    fgets(InitBalance, 255, fpointerR);
    Balance= atof(InitBalance);
    operation=0;

    printf("\nIndicate operation to do:\n");
    printf(" 1.Cash Income\n 2.Cash Withdrawal\n 3.Balance Enquiry\n");
    printf(" 4.Account Activity\n 5.Change PIN\n 6.Exit\n\n");
    printf(" Operation:");
    // Coloquem un scanf per a que es torne a preguntar al acabar l'acció i poder eixir del bucle
    scanf("%d",&operation);
    


    while (operation != 6) {
      
      switch(operation) {

        case 1 :
          printf(" Cash Income\n");
          printf("\n Enter the amount to deposit:");   
           scanf("%f",&income);
           Balance=Balance+income;  
          printf(" Successful income\n");

          

          break;
         
        case 2 :
          printf(" Cash Withdrawal\n");
          printf("\n Enter the amount to withdraw:");
          scanf("%f",&income);
          
           if(Balance>income){
             Balance=Balance-income;
           }else{
             printf(" Operation does not allowed\n");
             printf("   Not enough cash\n");
           }

           

           break;

        case 3 :
         printf(" Balance Enquiry\n");

         break;
        
        case 4: // same as 5

        case 5:
         printf(" This operation is not implemented");
        
          break;              

        default :
         printf(" ERROR: This opertaion does not applied\n");
         
         break;

        
      }
      
      // Funció saldo
      printf("Would you like to see the current balance: say yes or no : \n");
      scanf("%s",saldoQuest);

      while (!(strcmp(saldoQuest, "yes") == 0) && !(strcmp(saldoQuest, "no") == 0)){
        scanf("%s",saldoQuest);
      }
        
      if (strcmp(saldoQuest, "yes") == 0) { 
        printf("\n\n Current Balance: %.2f Euros", Balance);
      } 
      //fi funció saldo


      
      // Write in the file
      FILE * fpointerW = fopen("registers.txt", "w");
      fprintf(fpointerW, "%f\n",Balance);
      fclose(fpointerW);

      
      printf("\n\n Thanks \n\n");

      //Repeat the operations quiz
      printf("\nIndicate operation to do:\n");
      printf(" 1.Cash Income\n 2.Cash Withdrawal\n 3.Balance Enquiry\n");
      printf(" 4.Account Activity\n 5.Change PIN\n 6.Exit\n\n");
      printf(" Operation:");
      // Coloquem un scanf per a que es torne a preguntar al acabar l'acció i poder eixir del bucle
      scanf("%d",&operation);
    
    }
  } else {
    printf("Wrong username or passdword\n");
  }


 
  
  // Indiquem que el programa s'acaba al finalitzar el bucle
  printf(" EXIT\n"); 

  // close the file to read
  fclose(fpointerR);

  return(0);

  
}
