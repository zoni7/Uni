// ej4_fork.c
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main(void)
{
    pid_t pid=fork();

    switch (pid) {
    case -1:
        printf("Could not create child process \n");
        break;

    case 0:
        printf("I am the child with PID %d and my parent is %d \n", 
                  getpid(), getppid());
        break;

    default:
        printf("I am the parent with PID %d and my child is %d \n", 
                  getpid(), pid);
    }

    sleep(5);
    return 0;
}
