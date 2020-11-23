#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>

#define NPROCESSES 5

int main(void)
{
    pid_t pid;
    int i;

    for (i=0; i<NPROCESSES; i++) {
        pid=fork();
        if (pid==0){
            
            printf("child created in iteration = %d\n",i );

            exit(i);
            
        }       

    }
    sleep(10);
    for (i=0; i < 5; i++) wait();
    exit(0);
}