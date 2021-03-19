.globl __start
.data 0x10000000
string:   .space 80
respon:   .asciiz "Write something: "
answer:   .asciiz "You have written: "
ltext:    .asciiz "The string length is: "


.text 0x00400000
__start:
          la $a0, respon
          la $a1, string
          li $a2, 80
          jal InputS

          la $a0, answer
          la $a1, string
          jal OutputS

          la $a0, string
          jal StrLength


          li $v0, 4
          la $a0, ltext
          syscall

          li $v0, 1
          move $a0, $s0
          syscall

          li $v0,10
          syscall

InputS:   li $v0, 4
          syscall
          li $v0, 8
          move $a0, $a1
          move $a1, $a2
          syscall
          jr $ra

OutputS:  li $v0, 4
          syscall

          li $v0, 4
          move $a0, $a1
          syscall
          jr $ra

StrLength:li $s0 , 0 # contador
          li $s3, 10 # line feed (LF) character
    loop:
          lb $s1, 0($a0)
          addiu $a0, $a0, 1
          addi $s0,$s0,1 # incrementar el contador
          lb $s1, 0($a0)
          bne $s1, $s3, loop
          jr $ra
