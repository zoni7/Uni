.globl __start
          .text 0x00400000
__start:
		   li $a2, 10
		   li $v0, 5
		   syscall
		   move $a1, $v0
		   jal PrintChar

		   li $v0, 10
		   syscall

PrintChar:
		   bnez $a1, case2
		   li $v0, 11
		   li $a0, 0
		   syscall
		   b fi

	case2: bne $a1, $a2, case3
		   li $v0, 11
		   li $a0, 10
		   syscall
		   b fi

	case3: li $v0, 11
		   move $a0, $a1
		   syscall

	fi:   
	       jr $ra
		   


