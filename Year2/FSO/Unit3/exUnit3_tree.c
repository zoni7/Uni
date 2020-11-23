// ej8_waitpid.c
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <unistd.h>


#define NPROCESSES_1 3
#define NPROCESSES_2 2

/* Second method to generate the 4th generation 
*/
void generation4() {
    pid_t pidC3;
    int status3;

    pidC3 = fork();
    if (pidC3 == 0) {
        printf("Level 3: child: %d, father: %d\n",getpid(), getppid() );
        sleep(5);
        exit(0);

    } else if (pidC3 > 0){
        wait(&status3);

    } else {
        printf("Error creating the childreen\n");
    }
    

}

/* Second method to generate the 3th generation 
*/
void generation3() {
    pid_t pidC2[NPROCESSES_2];
    int status2;

    for (int i = 0; i < NPROCESSES_2; ++i) {
        
        pidC2[i] = fork();

        if (pidC2[i] == 0) {
            printf("Level 2: child: %d, father: %d\n",getpid(), getppid() );
            sleep(5);
            generation4();
            exit(0);

        } else if (pidC2[i] > 0){
            wait(&status2);

        } else {
            printf("Error creating the childreen\n");
        }
    }
}


int main(void)
{
    pid_t pidC[NPROCESSES_1];
    int status;

    for(int i = 0; i < NPROCESSES_1; i++) { 
        pidC[i] = fork();
        if (pidC[i] == 0) {
            printf("Level 1: child: %d, father: %d\n",getpid(), getppid() );
            sleep(5);
            generation3();
            exit(0);

        } else if (pidC[i] > 0){
            wait(&status);

        } else {
            printf("Error creating the childreen\n");
        }
    }
   
    return 0;
}