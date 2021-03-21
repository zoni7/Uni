.data 0x10011000

dimension:	.space 4
vector1: 	.space 400
vector2: 	.space 400
vector3:	.space 400

.globl __start
 .text 0x00400000
__start:
 		li $a0, 'D'
 		la $a1, dimension
		jal InputDimension

		# Ask for how many vectors of A
		lw $t0, 0($a1)
		li $t1, 0
		li $t2, 'A'
		la $a2, vector1
loop:	jal InputVector
		addi $t1, $t1, 1
		addi $a2, $a2, 4
		bne $t1, $t0, loop

		# Ask for how many vectors of B
		lw $t0, 0($a1)
		li $t1, 0
		li $t2, 'B'
		la $a2, vector2
loop2:	jal InputVector
		addi $t1, $t1, 1
		addi $a2, $a2, 4
		bne $t1, $t0, loop2

		# Addition of the vectors
		lw $t0, 0($a1)
		li $t1, 0
		li $t2, 'C'
		la $a2, vector1
		la $a3, vector2
		la $t4, vector3
loop3: 	jal AddVector
		addi $t1, $t1, 1
		addi $a2, $a2, 4
		addi $a3, $a3, 4
		addi $t4, $t4, 4
		bne $t1, $t0, loop3
          
        # Terminar el proceso
        li $v0,10
        syscall

InputDimension: 
		li $v0, 11
	 	syscall

	 	li $v0, 11
	  	li $a0, '='
	  	syscall

	  	li $v0,5
	  	syscall

	  	sw $v0, 0($a1)
	  	jr $ra

InputVector:
		# Print
		li $v0, 11
		move $a0, $t2
	 	syscall
	 	li $v0, 11
	  	li $a0, '['
	  	syscall
	  	li $v0, 1
	  	move $a0, $t1
	  	syscall
	  	li $v0, 11
	  	li $a0, ']'
	  	syscall
	  	li $v0, 11
	  	li $a0, '='
	  	syscall
	  	# Ask
	  	li $v0, 5
	  	syscall
	  	sw $v0, 0($a2)
	  	jr $ra

AddVector:
		li $s4, 0
		
		lw $s2, 0($a2)
		lw $s3, 0($a3)
		add $s4, $s2, $s3
		sw $s4, 0($t4)

		li $v0, 11
		move $a0, $t2
	 	syscall
	 	li $v0, 11
	  	li $a0, '['
	  	syscall
	  	li $v0, 1
	  	move $a0, $t1
	  	syscall
	  	li $v0, 11
	  	li $a0, ']'
	  	syscall
	  	li $v0, 11
	  	li $a0, '='
	  	syscall
	  	li $v0, 1
	  	move $a0, $s4
	  	syscall
	  	li $v0, 11
	  	li $a0, 10
	  	syscall
	  	jr $ra

