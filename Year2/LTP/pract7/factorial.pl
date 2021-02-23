

fact(0,1).

fact(N, Salida):- T is N-1, fact(T, S1), Salida is N*S1.


tab(0).
tab(N) :- put_code(32),M is N - 1, tab(M) .

