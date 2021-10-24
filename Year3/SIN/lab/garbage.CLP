(deffacts base
	
	(garbage G 1 3 G 2 1 G 4 3 G 6 1 G 4 4 G 1 4 G 7 4 G 7 1 G 7 3) 
	(grid_size 8 5)
	(robot 2 3 cans C 2 2 C  level 0 movement null)  ;; Dynamic fact

)

;;
;; | RULES |
;; |	   |
;;

(defrule move_up
	?fn <- (robot ?x ?y cans $?can_list level ?lev movement ?mov) ;; get robot x and y
	(grid_size ?* ?max_y) ;; get max Y
	(test ( < (+ ?y 1) ?max_y) ) ;; new position is within bounds
	(garbage $?G_list) ;; get garbage list
	(test (not (member (create$ G ?x (+ ?y 1) ) $?G_list) ) ) ;; new position is not the position of a garbage
	(test (not (member (create$ C ?x (+ ?y 1) ) $?can_list) ) ) ;; new position is not the position of a can
	(test (neq ?mov down)) ;; not undoing last move
	
=>

	(assert (robot ?x (+ ?y 1) cans $?can_list level (+ ?lev 1) movement up )) ;; update position
)

(defrule move_down
?fn <- (robot ?x ?y cans $?can_list level ?lev movement ?mov) ;; get robot x and y
(test ( >  (- ?y 1) 0) ) ;; new position is within bounds
(garbage $?G_list) ;; get garbage list
(test (not (member (create$ G ?x (- ?y 1) ) $?G_list) ) ) ;; new position is not the position of a garbage
(test (not (member (create$ C ?x (- ?y 1) ) $?can_list) ) ) ;; new position is not the position of a can
(test (neq ?mov up)) ;; not undoing last move

=>

(assert (robot ?x (- ?y 1) cans $?can_list level (+ ?lev 1) movement down )) ;; update position
)

(defrule move_left
?fn <- (robot ?x ?y cans $?can_list level ?lev movement ?mov) ;; get robot x and y
(test ( > (- ?x 1) 0) ) ;; new position is within bounds
(garbage $?G_list) ;; get garbage list
(test (not (member (create$ G (- ?x 1) ?y ) $?G_list) ) ) ;; new position is not the position of a garbage
(test (not (member (create$ C (- ?x 1) ?y ) $?can_list) ) ) ;; new position is not the position of a can
(test (neq ?mov right)) ;; not undoing last move

=>

(assert (robot (- ?x 1) ?y cans $?can_list level (+ ?lev 1) movement left )) ;; update position
)

(defrule move_right
?fn <- (robot ?x ?y cans $?can_list level ?lev movement ?mov) ;; get robot x and y
(grid_size ?max_x ?*) ;; get max X
(test ( <  (+ ?x 1) ?max_x ) ) ;; new position is within bounds
(garbage $?G_list) ;; get garbage list
(test (not (member (create$ G (+ ?x 1) ?y ) $?G_list) ) ) ;; new position is not the position of a garbage
(test (not (member (create$ C (+ ?x 1) ?y ) $?can_list) ) ) ;; new position is not the position of a can
(test (neq ?mov left)) ;; not undoing last move

=>

(assert (robot (+ ?x 1) ?y cans $?can_list level (+ ?lev 1) movement right)) ;; update position
)

;;
;;	|PUSH|
;;

(defrule push_up
	(declare (salience 50) ) ;; priority over move
	?fn <- (robot ?x ?y cans $?cans_list_pre C ?cx ?cy $?cans_list_post level ?lev movement ?*) ;; get robot x and y and match with a can
	(grid_size ?* ?max_y) ;; get max Y
	(test  	(= ?x ?cx)) 
	(test  	(= (+ ?y 1) ?cy))
	 ;; new position is the position of the can
	(test (not (member (create$ C ?x (+ ?y 2))$?cans_list_pre) ) )
	(test (not (member (create$ C ?x (+ ?y 2))$?cans_list_post) ) )
	 ;; Not two cans in a row
	(test (< (+ ?y 2) ?max_y) ) ;; The pushed can does not fall to the void

=>

	(assert (robot ?x (+ ?y 1) cans $?cans_list_pre C ?cx (+ ?cy 1) $?cans_list_post level (+ ?lev 1) movement up))

)

(defrule push_down
	(declare (salience 50) ) ;; priority over move
	?fn <- (robot ?x ?y cans $?cans_list_pre C ?cx ?cy $?cans_list_post level ?lev movement ?*) ;; get robot x and y and match with a can
	(test  	(= ?x ?cx)) 
	(test  	(= (- ?y 1) ?cy))
	 ;; new position is the position of the can
	(test (not (member (create$ C ?x (- ?y 2))$?cans_list_pre) ) )
	(test (not (member (create$ C ?x (- ?y 2))$?cans_list_post) ) )
	 ;; Not two cans in a row
	(test (> (- ?y 2) 0) ) ;; The pushed can does not fall to the void

=>

	(assert (robot ?x (- ?y 1) cans $?cans_list_pre C ?cx (- ?cy 1) $?cans_list_post level (+ ?lev 1) movement down))

)

(defrule push_right
	(declare (salience 50) ) ;; priority over move
	?fn <- (robot ?x ?y cans $?cans_list_pre C ?cx ?cy $?cans_list_post level ?lev movement ?*) ;; get robot x and y and match with a can
	(grid_size ?max_x ?*) ;; get max X
	(test  	(= ?y ?cy)) 
	(test  	(= (+ ?x 1) ?cx))
	 ;; new position is the position of the can
	(test (not (member (create$ C (+ ?x 2) ?y)$?cans_list_pre) ) )
	(test (not (member (create$ C (+ ?x 2) ?y)$?cans_list_post) ) )
	 ;; Not two cans in a row
	(test (< (+ ?x 2) ?max_x) ) ;; The pushed can does not fall to the void

=>

	(assert (robot (+ ?x 1) ?y cans $?cans_list_pre C (+ ?cx 1) ?cy $?cans_list_post level (+ ?lev 1) movement right))

)

(defrule push_left
	(declare (salience 50) ) ;; priority over move
	?fn <- (robot ?x ?y cans $?cans_list_pre C ?cx ?cy $?cans_list_post level ?lev movement ?*) ;; get robot x and y and match with a can
	(test  	(= ?y ?cy)) 
	(test  	(= (- ?x 1) ?cx))
	 ;; new position is the position of the can
	(test (not (member (create$ C (- ?x 2) ?y)$?cans_list_pre) ) )
	(test (not (member (create$ C (- ?x 2) ?y)$?cans_list_post) ) )
	 ;; Not two cans in a row
	(test (> (- ?x 2) 0) ) ;; The pushed can does not fall to the void

=>

	(assert (robot (- ?x 1) ?y cans $?cans_list_pre C (- ?cx 1) ?cy $?cans_list_post level (+ ?lev 1) movement right))

)

(defrule are_you_winning_son
(declare (salience 9999))
?fn<-(robot ?x ?y cans level ?lev movement ?*);; cans is empty!!
=>
(printout t "Solution found at level" ?lev)
(halt)

)


;;
;;	|on hold|
;;
(defrule clean_garbage
	(declare (salience 100) ) ;; always check for cans to clear first
	?fn <- (robot $?robot cans $?cans_list_pre C ?cx ?cy $?cans_list_post level ?lev movement ?mov) ;; match with can x and y 
	(garbage $?G_list) ;;get garbage list
	(test (member (create$ G ?cx ?cy ) $?G_list) ) ;; the matched can is on a garbage spot

=>

	(retract ?fn) ;; retract so branches with cans on garbages are ignored
	(assert (robot $?robot cans $?cans_list_pre $?cans_list_post level ?lev movement ?mov))

)

