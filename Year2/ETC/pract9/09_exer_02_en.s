          .globl __start
          .data 0x10000000
string:   .space 80
respon:   .asciiz "Write something: "
answer:   .asciiz "You have written: "

          .text 0x00400000
__start:
          la $a0, respon
          la $a1, string
          li $a2, 80
          jal InputS

          la $a0, answer
          la $a1, string
          jal OutputS

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
