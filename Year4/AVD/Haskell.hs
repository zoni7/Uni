module Increment where
import Data.Char

-- Exercise 1
increment :: Int -> Int
increment x = x + 1

-- Exercise 2
equals :: Int -> Int -> Bool
equals x y = if x == y then  True else  False

-- Exercise 3-4
equals0 :: Int -> Bool
equals0 x 
    | x == 0 = True
    | otherwise = False

-- Exercise 5
position :: [Char] -> Int -> Char
position (x:xs) y = (x:xs) !! y
           

-- Exercise 6 , inputs are strings
asciiConverter :: [Char] -> [Int]
asciiConverter (x:xs) =  ord x : map ord xs