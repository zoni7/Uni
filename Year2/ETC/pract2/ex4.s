.globl __start
 .text 0x00400000
__start:
 li $s0, 32
 li $s1, 126
 li $s2, 4
 li $s3,1

loop:
 	sub $s2,$s2 ,$s3

	li $v0,1
	move $a0,$s0
 	syscall

	li  $v0, 11 
 	li $a0, 9
 	syscall
 
 	li  $v0, 11 
 	move $a0, $s0
 	syscall

 	bgtz $s2, yes
	
	li $s2,4
	
	li $v0,11
 	li $a0,10
 	syscall
	
	b continue

	yes:
		li  $v0, 11 
 		li $a0, 9
 		syscall

	continue:
		add $s0,$s0,$s3
		beq $s0, $s1, end
	b loop
 	

end:
 li $v0,10
 syscall