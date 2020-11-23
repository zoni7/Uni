.globl __start
          .text 0x00400000
__start:
		li $a1, 1
		li $a2, 10  
	  
	li $a0, 'M'	  
	    jal Input
	    move $s0,$v0	  
  
repeat:	move $a0,$s0
	 
    	move $t0, $a1
        jal Mult
        move $a3,$v0
        jal Output

        beq $a1, $a2, fi
        addi $a1, $a1, 1 
	    j repeat
	   
fi:
        li $v0,10
        syscall

Mult:     li $v0, 0
          beqz $t0, MultRet
MultFor:  add $v0, $v0, $a0
          addi $t0, $t0, -1
          bne $t0, $zero, MultFor
MultRet:  jr $ra

Input:	  
	  li $v0, 11
	  syscall
	  
	  li $v0, 11
	  li $a0, '='
	  syscall

	  li $v0,5          
	  syscall
	  
	  jr $ra

Output:	li $v0, 1
		syscall

		li $v0, 11
		li $a0, 'x'
		syscall

		li $v0, 1
		move $a0, $a1
		syscall

		li $v0, 11
		li $a0, '='
		syscall

		li $v0, 1
		move $a0, $a3
		syscall

		li $v0, 11
		li $a0, 10
		syscall

		jr $ra


