.globl __start
 .text 0x00400000
__start:
 li $s0, 0
 li $s1, 0

loop:
 li $v0, 1
 addi $a0, $s1, 1
 syscall
	
 li $v0,11
 li $a0, '>'
 syscall
	
 li $v0,5
 syscall

 beq $v0 $0 end

 addu $s0,$s0,$v0
 addi $s1,$s1,1
 li $v0,1
 move $a0,$s0
 syscall
 li $v0,11
 li $a0,10
 syscall

 b loop

end:
 li  $v0, 11
 li $a0, 0x0000003D
 syscall

 li $v0,1
 move $a0,$s0
 syscall

 li $v0,11
 li $a0,10
 syscall

 li  $v0, 11 
 li $a0, 0x0000006E
 syscall

 li  $v0, 11
 li $a0, 0x0000003D
 syscall

 li $v0,1
 move $a0,$s1
 syscall

 li $v0,10
 syscall