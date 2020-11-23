#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>

#define MAX_PROC 4

int main() {
	int i;
	pid_t val_return;
	int final_state, stat_loc;
	for (i=0; i<MAX_PROC; i++) {
		val_return = fork();
		if (val_return == 0) {
			printf("Child %ld created in iteration %d \n",(long)getpid(),i);
			
        	
			
		} else {
			wait(&stat_loc);
			printf("Parent %ld, iteration %d \n", (long)getpid(), i);
			printf("I have created a child %ld with this exit status: %d\n", (long)val_return, stat_loc);
			break;
		}
	}

	while (wait(&final_state)>0) {
		printf("Parent %ld iteration %d \n", (long)getpid(), i);
		printf("My child said %d \n", WEXITSTATUS(final_state));
		printf("My child said %d \n", final_state/256);
	}
	sleep(10);
	exit(i);
}