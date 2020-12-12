// a_pipe.c
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main (int argc,char *argv[]) {
    // Arguments for printing in a file
    int fd;
    char *arch = "result.txt";
    mode_t fd_mode = S_IRWXU;// file premissions


    int i;
    char* arguments1 [] = { "ls", "-la", 0 };
    char* arguments2 [] = { "wc", "-l", 0 };
    char* arguments3 [] = { "grep", "a_pipe", 0 };
    int fildes[2];
    int fildes2[2];
    pid_t pid, pid2;
    // Parent process creates the first pipe
    if ((pipe(fildes)==-1)) { 
        fprintf(stderr,"Pipe failure  \n");
        exit(-1);
    }
    // Parent process creates the second pipe
    if ((pipe(fildes2)==-1)) { 
        fprintf(stderr,"Pipe2 failure  \n");
        exit(-1);
    }
    for (i=0;i<3;i++) {	
        pid=fork(); // Creates a child process
        if ((pid==0) && (i==0)) {
            // Child process redirects its output to the pipe
                if (dup2(fildes[1],STDOUT_FILENO) == -1) { 
                    printf("Error calling dup2\n");
                    exit(-1);
                }
            
            // Child process closes pipe descriptors 
            close(fildes[0]);
            close(fildes[1]);
            close(fildes2[0]);
            close(fildes2[1]);
  
            // Child process changes its memory image
            if ( execvp("ls",arguments1)<0) { 
                fprintf(stderr,"ls not found \n");
                exit(-1);
            }
        } else if ((pid==0) && (i==1)) {
            // Child process redirects its input to the pipe
            if (dup2(fildes[0], STDIN_FILENO) == -1) { 
                    printf("Error calling dup2\n");
                    exit(-1);
                }
            // Child process redirects its output to the pipe
            if (dup2(fildes2[1], STDOUT_FILENO) == -1) { 
                    printf("Error calling dup2\n");
                    exit(-1);
                }
            // Child process closses pipe descriptors
            close(fildes[0]);
            close(fildes[1]);
            close(fildes2[0]);
            close(fildes2[1]);

            // Child process changes its memory image
            if (execvp("grep",arguments3)<0) {
                fprintf(stderr,"grep not found \n");
                exit(-1);
            }   
          
        } else if ((pid==0) && (i==2)) {
            // Child process redirects its input to the pipe
            if (dup2(fildes2[0], STDIN_FILENO) == -1) { 
                    printf("Error calling dup2\n");
                    exit(-1);
                }
            // Child process redirects its output to the result.txt    
            fd = open(arch,O_RDWR | O_CREAT,fd_mode);
            if (dup2(fd,STDOUT_FILENO) == -1) { 
                printf("Error calling dup2\n");
                exit(-1);
            }
            // Child process closses pipe descriptors
            close(fildes[0]);
            close(fildes[1]);
            close(fildes2[0]);
            close(fildes2[1]);

            // Child process changes its memory image
            fprintf(stdout,execl("/bin/wc","wc","-l",NULL));
            close(fd); 


            
              
        }
    }

    

    // Parent process closes pipe descriptors
    close(fildes[0]);
    close(fildes[1]);
    close(fildes2[0]);
    close(fildes2[1]);
    for (i = 0; i < 3; i++) wait(NULL);
    return(0);
}