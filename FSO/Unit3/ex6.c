// ej6a_exec.c
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>

int main(void)
{
    int status;
    pid_t pid=fork();

    char* arguments [] = { "ps", "aux", 0 };
    
    switch (pid) {
    case -1:
        printf("The child  process could not be created \n");
        break;
    case 0:
        printf("I am the child with PID %d, the current directory content is: \n",    
                  getpid());
        if (execvp("ps", arguments)==-1){
            printf("Error in exec\n");
            exit(0);
        }
          break;
    default:
        printf("I am the parent process with PID %d and my child is %d.\n", 
                  getpid(), pid);
                  
    }
    return 0;
}