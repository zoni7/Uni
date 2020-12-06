module Queue (Queue, empty, enqueue, dequeue, first, isEmpty, size,fromList ,toList) where
	data Queue a = EmptyQueue | Item a (Queue a)
	empty = EmptyQueue
	enqueue x EmptyQueue = Item x EmptyQueue
	enqueue x (Item a q) = Item a (enqueue x q)
	dequeue (Item _ q) = q
	first (Item a _) = a
	isEmpty EmptyQueue = True
	isEmpty _ = False
	size EmptyQueue = 0
	size (Item _ q) = 1 + size q

	-- Function fromList
	fromList [] = empty
	fromList (x:xs) =enqueue x (fromList xs)
	-- Function toList
	toList EmptyQueue = []
	toList (Item x y) = [x] ++ toList(y) 
	
	

	instance (Show a) => Show (Queue a) where
		show EmptyQueue = " <- "
		show (Item x y) = " <- " ++ (show x) ++ (show y)

	-- instance Eq ...
	--instance (Eq a) => Eq (Queue a) where
	--	eq (Item a q) (Item b m)
	--		| (a == b) = True
	--		| otherwise = False 