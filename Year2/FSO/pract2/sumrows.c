#include <stdio.h> 

#define SIZE_ROW 100
#define NUM_ROWS 10

struct ROW {
    float data[SIZE_ROW];
    float addition;
};
// A) define a vector “rows” of structures ROW with size NUM_ROWS
struct ROW rows[NUM_ROWS];
 
	
void sum_row (struct ROW *pf) {
// B) Implement sum_row
	int i = 1;
	int j = 0;
	float res;
    // adding the data
	while (i < NUM_ROWS) {
		while(j < SIZE_ROW) {
		res =  res + pf[i].data[j];
		j++;
		}
		// assigning to .addition the result of the sumatory
		pf[i].addition = res;
		
		i++;
	}
	
  
}

// Initilize rows with value i * j
void init_rows() {
    int i, j;
    for (i = 0; i < NUM_ROWS; i++) {
        for (j = 0; j < SIZE_ROW; j++) {
            rows[i].data[j] = (float)i*j;
        }
    }
}

void main() { 
    int i;
    float total_sum;
    
    init_rows ();
    
    // C) Complete the loop
    total_sum = 0;
    for (i = 0; i < NUM_ROWS; i++) {
        // calling the sumatory
        sum_row(&(rows[i]));
        printf("Row %u addition result is %f\n", i,  rows[i].addition);
        // update total_sum with the actual row
        total_sum = total_sum + rows[i].addition;
    }

    printf("Final addition result is %f\n", total_sum); 
} 
