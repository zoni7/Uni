(deffacts data
      (list 2 3 7 6 2 9 4 2 3 7 6 9 4 4))
     
	

  (defrule R1
    ?f1 <- (list $?x ?y $?w ?u $?p)   
	   (test (= ?y ?u))          
	=>
	   (retract ?f1)	
	   (assert (list $?x $?w ?u $?p)))
	   