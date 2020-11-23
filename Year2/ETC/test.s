.data 0x10011000
H:        .word 400
I:        .word 100
J:        .word 200
K:        .word 300

.globl __start
 .text 0x00400000
__start:
 
 la $t0,I
          lw $t2,-4($t0)
          li $t1,-1        # -1 = 0xFFFFFFFF
          sw $t1,4($t0)