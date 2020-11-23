
// ej3_fork.c
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
     pid_t ret;
     int var = 0;
     
     printf("PID before fork(): %ld\n", (long) getpid());
     ret = fork();
     if (ret > 0) {  //parent code
       printf("Parent PID: %ld\n", (long) getpid());
       var++;
     } else {   // child code
       printf("Child PID: %ld\n", (long) getpid());
     }
     printf("Process [%ld]-> var=%d ret: %d\n", (long) getpid(), var, ret);
     sleep(2);
     return 0;
}