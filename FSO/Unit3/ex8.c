// ej8_waitpid.c
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <unistd.h>


#define NPROCESSES 4

int main(void)
{
    pid_t pid[NPROCESSES];
    int i, status;

    for (i=0; i<NPROCESSES; i++) {
        pid[i]=fork();
        if (pid[i]==0){
            printf("I am the child %d my parent is %d\n", 
                       getpid(), getppid());
            sleep(10);
            printf("Process %d is exiting ... \n", getpid());
            exit(0);
        }
    }
    // Now wait for the third child
    if (waitpid(pid[2],&status,0)==pid[2])
        printf(" My child with pid %d has finished  \n", pid[2]);
    return 0;
}