// redir_output.c
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main (int argc,char *argv[]) {
    int fd;
    char *arch = "ls_output.txt";
    mode_t fd_mode = S_IRWXU;// file premissions

    fd = open(arch,O_RDONLY,fd_mode); // added the O_RDONLY
    if (dup2(fd,STDIN_FILENO) == -1) { // added the STDIN_FILEMO
        printf("Error calling dup2\n");
        exit(-1);
    }

    fprintf(stdout,execl("/bin/cat","cat",NULL));
    close(fd);
    return(0);
}
