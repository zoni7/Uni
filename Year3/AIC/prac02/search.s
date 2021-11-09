        .data
a:      .dword  9,8,0,1,0,5,3,1,2,0
tam:    .dword 10       ; Tamaño del vector
cont:   .dword 0        ; Número de componentes == 0

        .text
start:	dadd r1,$gp,a   ; Puntero
        ld r4,tam($gp)  ; Tamaño lista
        dadd r10,r0,r0  ; Contador de ceros

loop:
	    lb r2, 0(r1)
	    dsub r4, r4, 1
        daddi r1, r1, 1 ; update pointer
        bnez r2 , else
        daddi r10 , r10, 1
        
else:
        bne r4, r0, loop ; End loop ?
        trap #0


