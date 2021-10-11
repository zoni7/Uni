

(defglobal ?*gen-nod* = 0)

(defrule right
 ?f<-(puzzle $?x 0 ?y $?z level ?level movement ?mov fact ?)
 (max-depth ?depth)
 (test (and (<> (length$ $?x) 2) (<> (length$ $?x) 5)))
 (test (neq ?mov left))
 (test (< ?level ?depth))
=>
 (assert (puzzle $?x ?y 0 $?z level (+ ?level 1) movement right fact ?f))
 (bind ?*gen-nod* (+ ?*gen-nod* 1)))


(defrule down
 ?f<-(puzzle $?x 0 ?a ?b ?c $?z level ?level movement ?mov fact ?)
(max-depth ?depth)
(test (neq ?mov up))
(test (< ?level ?depth))
=>
(assert (puzzle $?x ?c ?a ?b 0 $?z level (+ ?level 1) movement down fact ?f))
(bind ?*gen-nod* (+ ?*gen-nod* 1)))


(defrule down
 ?f<-(puzzle $?x 0 ?a ?b ?c $?z level ?level movement ?mov fact ?)
(max-depth ?depth)
(test (neq ?mov up))
(test (< ?level ?depth))
=>
(assert (puzzle $?x ?c ?a ?b 0 $?z level (+ ?level 1) movement down fact ?f))
(bind ?*gen-nod* (+ ?*gen-nod* 1)))


(defrule no_solution
 (declare (salience -99))
 (puzzle $? level ?n $?)
=>
 (printout t "SOLUTION NOT FOUND" crlf)
 (printout t "GENERATED NODES: " ?*gen-nod* crlf)
 (halt))



(deffunction start ()
(reset)
(printout t "Maximum depth:= " )
(bind ?depth (read))
(printout t "Search strategy " crlf "1.- Breadth" crlf 
 "2.- Depth" crlf )
(bind ?a (read))
(if (= ?a 1)
 then (set-strategy breadth)
 else (set-strategy depth)))
 (assert (puzzle 2 8 3 1 6 4 7 0 5 level 0 movement null fact 0))
 (assert (max-depth ?depth))
)



