(deffacts data
      (winning-board 2 5 8 13 24 35)
      (chosen-numbers 3 5 15 24 26 37)
      (cont 0))
	

  (defrule R1
    ?f1 <- (winning-board $?x ?y $?w)  
           (chosen-numbers $?f ?u $?q) 
	   (test (= ?y ?u ))
    ?f3 <- (cont ?c)            
	=>
	   (retract ?f3)	
	   (assert (cont(+ ?c 1))))