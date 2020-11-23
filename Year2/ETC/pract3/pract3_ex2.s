          .globl __start
          .text 0x00400000
__start:  
	  li $a0, 'M'	  
	  jal Input
	  move $s0,$v0	  
	  

	  li $a0, 'Q'	 
          jal Input
	   

          move $a1,$v0
	  move $a0,$s0
          jal Mult

          move $a1,$v0
	  li $a0, 'R'
          jal Output

          li $v0,10
          syscall
Mult:     li $v0, 0
          beqz $a1, MultRet
MultFor:  add $v0, $v0, $a0
          addi $a1, $a1, -1
          bne $a1, $zero, MultFor
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

Output:	  li $v0, 11
	  syscall
	  
	  li $v0, 11	  
	  li $a0, '='
	  syscall

	  li $v0, 1
	  move $a0, $a1
	  syscall

	  li $v0, 11
	  li $a0, 10
	  syscall

	  jr $ra


	  
	  