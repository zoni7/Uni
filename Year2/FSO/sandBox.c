#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <semaphore.h>

void *Print(void *ptr) {
    char *message; 
    message = (char *) ptr; 
    write (1,message,strlen(message)); 
} 

int main() { 
    pthread_t thread1, thread2; 
    pthread_attr_t attr; 
    pthread_attr_init(&attr); 
    pthread_create(&thread1, &attr, Print, "I am thread 1\n"); 
    pthread_create(&thread2, &attr, Print, "I am thread 2\n"); 
    pthread_join(thread2, NULL); 
    pthread_exit(0); 
}