module Practica5 where

	decBin:: Int -> [Int]

	decBin 0 = [0]
	decBin 1 = [1]
	decBin x = (mod x 2):decBin (div x 2)

	binDec :: [Int] -> Int

	binDec (x:[]) = x
	binDec (x:y) = x + binDec y * 2

	divisors :: Int -> [Int]
	divisors n = [x | x <- [1..n], (n `mod` x) == 0]

	member :: Int -> [Int] -> Bool
	member _ [] = False
	member x [y] = x==y
	member x(y:ys) = if x ==y then True else member x ys

	isPrime :: Int -> Bool
	isPrime x = if (length (divisors x) <= 2)  then True else False

	primes :: Int -> [Int]
	primes n = take n [x | x <- [1..], isPrime x]

	selectEven :: [Int] -> [Int]
	selectEven x = [y | y <-x, even y]

	selectEP :: [Int] -> [Int]
	selectEP xs = [xs !! i | i<-[0..(length xs)], even i]

	-- Exercise 8 --

	ins :: Int -> [Int] -> [Int]
	ins x [] = [x]
  	ins x (y:ys)
		| x<=y = (x:y:ys)
		| otherwise = y : (ins x ys)

	iSort :: [Int] -> [Int]
	iSort [] = []
	iSort (x:xs) = ins x (iSort xs)

	-- Exercise 9 --

	doubleAll :: [Int]->[Int]
	doubleAll [] = []
	doubleAll (x:xs) = map (*2) (x:xs) 

	-- Exercise 10 --
	map' :: (a -> b) -> [a] -> [b]
	map' f [] = []
	map' f xs = [f x | x <-xs]

	filter' :: (a -> Bool) -> [a] -> [a]
	filter' f [] = []
	filter' f xs = [x | x <-xs, f x]



 