

module Practica4 where

	import Data.Char (ord)
	import Data.List

	numCbetw2 :: (Char, Char) -> Int
	
	-- Exercice 1 function

	{-	Write a function numCbetw2 that returns the amount of characters that
		are between two given characters in the alphabet (without including
		them). For instance:
		> numCbetw2 ’a’ ’c’
		1
		> numCbetw2 ’e’ ’a’
		3
		> numCbetw2 ’a’ ’b’
		0
		> numCbetw2 ’x’ ’x’
		0

	-}

	numCbetw2 (a,b)
		   | a == b  = 0
		   | ord a > ord b = ((ord a) - (ord b)) -1 
           | otherwise = ((ord b) - (ord a)) -1 

	-- Exercice 2 function
	{-	Write a recursive function that returns the sum from one given integer
		to another one (both of them are included in the sum).
		> addRange 5 5
		5
		> addRange 5 10
		45
		> addRange 10 5
		45
	-}

	
	addRange a b
			| (a == b) = a
			| otherwise =   if a < b then sumatorio a (b - 1) + b else sumatorio (a-1) b  + a 
			

	-- Exercice 3 function
	{-	Define a binary (receives two arguments) function max’ that returns
		the maximum of two given integer arguments. Example:
		> max’ 5 50
		50
		> max’ 10 1
		10
	-}

	max' a b
		   | a > b  = a
		   | b > a = b 
           | otherwise = a 

    -- Exercice 4 function
    {-	Write a function leapyear that determines if a given year is a leap
		year or not. A year is a leap year if it is evenly divisible by 4. An
		exception to this rule are years that are also evenly divisible by 100
		unless they were also evenly divisible by 400. Example:
		> leapyear 1992
		True
		> leapyear 1900
		False

    -}
	
	leapyear a
		   | (mod a 100) == 0 && (mod a 400) /= 0 = False
		   | (mod a 100) == 0 && (mod a 400) == 0 = False
    	   | mod a 4 == 0 = True   	   		   
           | otherwise = False

    -- Exercice 5 function
    {-	Write a function daysAMonth that computes the number of days in a
		given month and year. Take into account the leap years for February.
		Example:
		> daysAmonth 2 1800
		28
		> daysAmonth 2 2000
		29
		8
		> daysAmonth 10 2015
		31

    -}
    

	list = [0,31,0,31,30,31,30,31,31,30,31,30,31]
	
	daysAmonth :: Int -> Int -> Int
	daysAmonth m y 
		| (m == 2) = if (leapyear y) then 29 else 28 
		| otherwise = list!!m

	-- Exercise 6 function
	{-	Write a function remainder that returns the remainder of the division
	of two integers using subtraction, which means that you cannot use
	div, mod, rem,. . . Example:
	> remainder 20 7
	6
	-}

	remainder a b
		| a < b = a
		| otherwise = remainder (a-b) b


	-- Exercise 7 a b
	{-	Using the previous functions, define a function sumFacts that computes the sum of the factorials till a given number n. Otherwise stated,
		sumFacts n = fact 1 + · · · + fact n. Example:
		> sumFacts 5
		153

	-}

	fact :: Int -> Int
	fact 0 = 1
	fact n = n * fact (n-1)

	sumFacts :: Int -> Int
	sumFacts 0 = 0
	sumFacts n = fact n + sumFacts (n - 1) 







    