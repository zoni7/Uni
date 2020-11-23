/**ej2_fork.c **/
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main(void)
{ 
  pid_t res;
  
  printf("Process %ld creates another process\n",
         (long)getpid());
  
  res = fork();
  
  printf("Process %ld with parent %ld return value: %ld\n",
         (long)getpid(), (long)getppid(), (long) res);
  
  sleep(10);
  return 0;
}