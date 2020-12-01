                ##########################################################
                # Segmento de datos
                ##########################################################

                .data 0x10000000
reloj:          .word 0                # HH:MM:SS (3 bytes de menor peso)

cad_asteriscos: .asciiz "\n  **************************************"
cad_horas:      .asciiz "\n   Horas: "
cad_minutos:    .asciiz " Minutos: "
cad_segundos:   .asciiz " Segundos: "
cad_reloj_en_s: .asciiz "\n   Reloj en segundos: "

                ##########################################################
                # Segmento de c�digo
                ##########################################################

                .globl __start
                .text 0x00400000

__start:        
                la $a0, reloj
                 li $a1, 0x00173b3b # Hora 23:59:59
                 jal inicializa_reloj
                 la $a0, reloj
                 jal imprime_reloj

                 la $a0, reloj
                 jal pasa_segundo # Increase 1 second
                 jal pasa_segundo # Increase 1 second
                 la $a0, reloj
                 jal imprime_reloj

             
salir:          li $v0, 10              # C�digo de exit (10)
                syscall                 # �ltima instrucci�n ejecutada
                .end

inicializa_reloj:
                                li $t0, 0x001F3F3F # 1 in fields HH:MM:SS
                                and $t0, $a1, $t0 # makes 0 the rest of bits
                                sw $t0, 0($a0) # Stores reloj
                                jr $ra

inicializa_reloj_alt:
                                sb $a3, 0($a0) # Field SS first byte
                                sb $a2, 1($a0) # Field MM second byte
                                sb $a1, 2($a0) # Field HH third byte
                                jr $ra

devuelve_reloj_en_s:
                                li $t0, 0
                                lb $a3, 0($a0) # Field SS first byte
                                lb $a2, 1($a0) # Field MM second byte
                                lb $a1, 2($a0) # Field HH third byte
                                li $s4, 3600
                                li $s5, 60
                                # Transform the hours in seconds
                                mult $a1, $s4
                                mflo $s2
                                add $t0, $t0, $s2
                                
                                # Transform the minutes in seconds
                                mult $a2, $s5
                                mflo $s3
                                add $t0, $t0, $s3

                                # Add the three variables in seconds
                                add $t0, $a3, $t0
                                add $v0, $t0, $0
                                jr $ra

devuelve_reloj_en_s_sd:
                lbu $a3, 0($a0) # Field SS first byte
                lbu $a2, 1($a0) # Field MM second byte
                lbu $a1, 2($a0) # Field HH third byte

                # Transform the hours in seconds
                sll $v0, $a1, 11
                sll $t0, $a1, 10
                addu $v0, $v0, $t0
                sll $t0, $a1, 9
                addu $v0, $v0, $t0
                sll $t0, $a1, 4
                addu $v0, $v0, $t0

                # Transform the minutes in seconds
                sll $v1, $a2, 5
                sll $t0, $a2, 4
                addu $v1, $v1, $t0
                sll $t0, $a2, 3
                addu $v1, $v1, $t0
                sll $t0, $a2, 2
                addu $v1, $v1, $t0

                # Add the three variables in seconds
                addu $v0, $v0, $a3
                addu $v0, $v0, $v1
                jr $ra

 inicializa_reloj_en_s:
                                #Obtein seconds and the rest in minutes
                                beq $a1 , $zero, isZero
                                li $s0, 60
                                div $a1, $s0
                                mfhi $t3
                                mflo $t2
                                #Obtein minutes and the rest in hours
                                beq $t2 , $zero, isZero
                                div $t2, $s0
                                mflo $t1
                                mfhi $t2
                
                                sb $t3, 0($a0) # Field SS first byte
                                sb $t2, 1($a0) # Field MM second byte
                                sb $t1, 2($a0) # Field HH third byte
                                jr $ra

                isZero: sb $t3, 0($a0) # Field SS first byte
                                sb $t2, 1($a0) # Field MM second byte
                                sb $0, 2($a0) # Field HH third byte
                                jr $ra

pasa_segundo:   lb $a3, 0($a0) # Field SS first byte
                lb $a2, 1($a0) # Field MM second byte
                lb $a1, 2($a0) # Field HH third byte

                # increasse_seconds
                addi $a3, $a3, 1
                li $v1, 60 
                beq $a3, $v1, yes_ss
                sb $a3, 0($a0) # Field SS first byte
                jr $ra

                yes_ss: addi $a2, $a2, 1
                

                # increasse_minutes
                li $a3, 0
                lb $a2, 1($a0) # Field MM second byte
                addi $a2, $a2, 1
                li $v1, 60 
                beq $a2, $v1, yes_mm
                sb $a2, 1($a0) # Field MM second byte
                jr $ra

                yes_mm: addi $a1, $a1, 1
            

                # increasse_hours
                li $a2, 0
                lb $a1, 2($a0)
                addi $a3, $a3, 1
                li $v1, 24
                blt $a1, $v1, yes_hh
                li $a1, 0 
                jr $ra

        yes_hh: sb $a1, 0($a0)
                jr $ra

        

        

        

                ########################################################## 
                # Subrutina que imprime el valor del reloj
                # Entrada: $a0 con la direcci�n de la variable reloj
                ########################################################## 

imprime_reloj:  move $t0, $a0
                la $a0, cad_asteriscos  # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall

                la $a0, cad_horas       # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall

                lbu $a0, 2($t0)         # Lee el campo HH
                li $v0, 1               # C�digo de print_int
                syscall

                la $a0, cad_minutos     # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall

                lbu $a0, 1($t0)         # Lee el campo MM
                li $v0, 1               # C�digo de print_int
                syscall

                la $a0, cad_segundos    # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall

                lbu $a0, 0($t0)         # Lee el campo SS
                li $v0, 1               # C�digo de print_int
                syscall

                la $a0, cad_asteriscos  # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall
                jr $ra

                ########################################################## 
                # Subrutina que imprime los segundos calculados
                # Entrada: $a0 con los segundos a imprimir
                ########################################################## 

imprime_s:      move $t0, $a0
                la $a0, cad_asteriscos  # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall


                la $a0, cad_reloj_en_s  # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall

                move $a0, $t0           # Valor entero a imprimir
                li $v0, 1               # C�digo de print_int
                syscall

                la $a0, cad_asteriscos  # Direcci�n de la cadena
                li $v0, 4               # C�digo de print_string
                syscall
                jr $ra
                
                ########################################################## 
                # Subrutina que incrementa el reloj en una hora
                # Entrada: $a0 con la direcci�n del reloj
                # Salida: reloj incrementado en memoria
                # Nota: 23:MM:SS -> 00:MM:SS
                ########################################################## 
                
pasa_hora:      lbu $t0, 2($a0)         # $t0 = HH
                addiu $t0, $t0, 1       # $t0 = HH++
                li $t1, 24
                beq $t0, $t1, H24       # Si HH==24 se pone HH a cero
                sb $t0, 2($a0)          # Escribe HH++
                j fin_pasa_hora
H24:            sb $zero, 2($a0)        # Escribe HH a 0
fin_pasa_hora:  jr $ra

