// ej7_wait.c
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <errno.h>

int main(void)
{
    int status;
    pid_t pid=fork();
    
    switch (pid) {
    case -1:
        printf(" The child  process could not be created \n");
        break;
    case 0:
        printf("I am the child process with PID %d and my parent is %d \n", 
                  getpid(), getppid());
        sleep(20);
        printf("I have finished \n");
        break;
    default:
        printf("I am the parent process with PID %d and my child is %d.                   Waiting ...\n", getpid(), pid);
        if (wait(&status) != -1)
            printf("My child has ended ok \n");         
    }
    return 0;
}