          .globl __start
          .data 0x10000000
M:        .space 4
Q:        .space 4
R:        .space 4

          .text 0x00400000
__start:  li $a0,'M'
          la $a1, M
          jal InputV

          li $a0,'Q'
          la $a1, Q
          jal InputV

          la $a0, M
          la $a1, Q
          la $a2, R
          jal MultV

          la $a0 'R'
          la $a1 R
          jal OutputV
          

          # Terminar el proceso
          li $v0,10
          syscall

InputV:   li $v0, 11
          syscall
          li $v0, 11
          li $a0, '='
          syscall
          li $v0, 5
          syscall
          sw $v0, 0($a1)
          jr $ra

OutputV:   # Output acepta parámetros directamente en registros:
          #   void Output (char $a0, int $a1)
          # Hay que cambiarla por OutputV, que opera sobre variables en memoria:
          #   void OutputV (char $a0, int *$a1)

          li $v0, 11        # print_char
          syscall           # Imprime $a0
          li $v0, 11        # print_char
          li $a0, '='       # Carácter ‘=’ en $a0
          syscall           # Imprime ‘=’

          lw $s1, 0($a1)    # Loading the value from the pointer


          li $v0, 1         # print_int
          move $s1, $a1     # Entero a imprimir en $s1
          syscall           # Imprime $s1

          li $v0, 11        # print_char
          li $a0, 10        # Carácter ASCII ‘\n’ en $a0
          syscall           # Imprime ‘\n’
          jr $ra            # Retorno de Output

MultR:    # MultR acepta parámetros y retorna el resultado directamente en registros:
          #    int Mult (int $a0, int $a1)
          # Hay que cambiarla por MultV, que opera sobre variables en memoria:
          #    void MultV (int *$a0, int *$a1, int *$a2)

          lw $s0, 0($a0)
          lw $s1, 0($a1)
          lw $s2, 0($a2)

          mult $s0, $s1     # Multiplica argumentos
          mflo $v0          # Resultado en $v0
          sw $s2, 0($v0)
          jr $ra            # Retorno de Mult
