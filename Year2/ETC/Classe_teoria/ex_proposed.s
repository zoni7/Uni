	.text 0x00400000
	__ Start:
	addi $t0, $zero, 8
	lui $t1, 0x1080
	ori $t1, $t1, 0xA0
loop:
	lw $t2, 0($t1)
	addi $t1, $t1,4
	addi $t2, $t2, -1
	srl $t2, $t2, 1
	sw $t2, -4($t1)
	bnez $t0, loop