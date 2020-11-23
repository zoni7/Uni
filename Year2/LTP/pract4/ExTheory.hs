module ExTheory where
	import Data.Char (ord)
	import Data.Char (chr)

	import Data.Time.Clock
	import Data.Time.Calendar

	import Data.List

	--ordered::[Int]->Bool
	--ordered (x:xs)
	--	|x <= ordered(xs) = True
	--	|otherwise = ordered(xs)


	nextLetter:: Char->Char
	nextLetter a
		
		| a == 'Z' = 'A'
		| a == 'z' = 'a'
		| otherwise =chr(ord(a)+1)
main = do
	now <- getCurrentTime
	let (year, month, day) = toGregorian $ utctDay now

	years (d,m,a) = show year - a

		
