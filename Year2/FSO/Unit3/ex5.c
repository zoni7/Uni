// ej5_proc_chain.c

#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

#define NPROCESSES 4

int main(void)
{
    pid_t pid;
    int i;

    for (i=0; i<NPROCESSES; i++) {
        pid=fork();
        if (pid!=0)
            break;
        printf("I am the child with PID %d my parent is %d\n", 
                  getpid(), getppid());
    }

    sleep(5);
    return 0;
}
