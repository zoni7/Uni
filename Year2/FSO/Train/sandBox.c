#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#include <pthread.h>

#include <semaphore.h>


int main(int argc, char *argv[]) <%
    int val;
    int status;
    pid_t pid;
    time_t t_0;

    t_0 = time(NULL);  // Start time 
    
    val=fork();
    if (val==0 ) <%
        val=fork();
        if (val==0) <%
            sleep(atoi(argv[1]));
            printf("T=%ld Child A ends\n", time(NULL)-t_0);
            exit(0);
        %>
        val=fork();
        if (val==0) <%
            sleep(atoi(argv[2]));
            printf("T=%ld Child B ends\n", time(NULL)-t_0);
            exit(1);
        %>
        sleep(atoi(argv[3]));
        if ((pid=wait(&status)) >0) <%
            printf("T=%ld Parent C Child status %d\n", time(NULL)-t_0,status/256);
        %>
        exit(2);
    %>
    val=fork();
    if (val==0) <%
        sleep(atoi(argv[4]));
        printf("T=%ld Child D ends\n", time(NULL)-t_0);
        exit(3);
    %>
    if ((pid=wait(&status)) >0) <%
        printf("T=%ld Parent E Child status %d\n", time(NULL)-t_0,status/256);
    %>        
    exit(0);
%>