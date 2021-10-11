(deffacts data
      (list 15 2 4 6 5 3 7 8 15)
      (index 1))

  (defrule R1
    ?f1 <- (list $?x ?y $?w)
	   
    ?f2 <- 
	   (index ?c)
	   (test (!= ?y ?c)) 
           (test (!= ?y 0)) 
            
	=>
	   (retract ?f1)
	   (retract ?f2)
	   (assert (index (+ ?c 1) ))
	   (assert (list $?x 0 $?w)))

	   