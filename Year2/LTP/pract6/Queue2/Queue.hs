module Queue where
	data Queue a = Queue [a] [a]
	empty = Queue [] []
	enqueue y (Queue xs ys) = Queue xs (y:ys)
	dequeue (Queue (x:xs) ys) = Queue xs ys
	dequeue (Queue [] ys) = dequeue(Queue (reverse ys) [])
	first (Queue (x:xs) ys) = x
	first (Queue [] ys) = head (reverse ys)
	isEmpty (Queue [] []) = True
	isEmpty _ = False
	size (Queue a b) = length a + length b

	-- instance Show ...
	instance (Show a) => Show (Queue a) where
		show (Queue [] []) = "<-"
		show (Queue (x:xs) []) = "<-" ++ (show x)++ (show (Queue xs []))
		show (Queue [] x) = "<-"++ show(first (Queue [] x)) ++ (show(dequeue(Queue [] x)))
		show (Queue (x:xs) (y:ys)) = "<-" ++ (show x)++ (show (Queue xs (y:ys)))
