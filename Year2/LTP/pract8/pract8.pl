
% Exercise 1 and 2
countTo(1,[1]).
countTo(2,[1,2]).
countTo(3,[1,2,3]).
countTo(4,[1,2,3,4]).


% Exercise 4
%--------------------------------
% mymember(E,[E|_]) :- !. 
% mymember(E,[_H|L]) :- mymember(E,L).  
% THIS VERSION AVOIDS THE OTHER 
% BRANCHES IF THE FIRST ELEMENT SUCESS
%--------------------------------

mymember(E,[E|_]).
mymember(E,[_H|L]) :- mymember(E,L).


% Exercisi 5
myappend([],L,L):- islist(L2).
myappend([E|L1],L2,[X|L3]) :- islist(L2), X = E, myappend(L1,L2,L3).




% last(L,U), U is the last element of the list L
last([U],U).
last([_|L],U) :- last(L,U).


% prefix(P,L), P is a prefix of the list L
prefix(P,L) :- islist(L), append(P,_,L).

% suffix(P,L), P is a suffix of the list L
suffix(P,L) :- islist(L), append(_,P,L).

% sublist(S,L), S is a sublist of the list L
sublist(S,L) :- prefix(S,L1), suffix(L1,L).


% Exercise 8
mistery([],0).
mistery([_|T],N) :- mistery(T,M), N is M+1.

% Exercise 10
sorted([]).
sorted([_]).
sorted([X,Y|Ys]) :- X =< Y, sorted([Y|Ys]).

% Exercise 11

remove(_,[],[]).
remove(C,[X|R],L) :- X == C, remove(C,R,L).
remove(C,[X|R],W) :- X \== C,remove(C,R,L), W = [X|L].
