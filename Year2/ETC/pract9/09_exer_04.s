.data 0x10011000
text : .asciiz "hello world"
char : .word 4

.globl __start
 .text 0x00400000
__start:
 
	la $a0, text
	li $a1, 2
	la $a2, char
	jal StrChar

		  li $v0, 4
          move $a0, $a2
          syscall
          
           # Terminar el proceso
          li $v0,10
          syscall

 StrChar: 
	 loop:	lbu $t0, 0($a0)
		 	addi $a1, $a1, -1
		 	addiu $a0, $a0, 1
		 	bne $a1, $zero, loop
		 	sw $t0, 0($a2)
		 	jr $ra