import Queue
main = do
   putStrLn (show (enqueue 7 (Queue [2,3] [4,1])))


-- instance Show ...
instance (Show a) => Show (Queue a) where
    show (Queue [] x) = ""
    show (Queue (x:xs) (y:ys)) = (show x) ++ " <- " ++ (show (Queue xs (y:ys))) ++ (show ys) 
    -- ++ " <- " ++ (show (Queue (x:xs) ys))
   