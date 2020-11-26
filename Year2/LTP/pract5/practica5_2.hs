
module Practica5_2 where

	data Color = Red | Blue | Green

	foo :: Color -> String
	foo Red = "red"
	foo Blue = "blue"
	foo Green = "green"



	type Person = String
	type Book = String
	type Database = [(Person,Book)]

	exampleBase :: Database
	exampleBase = [("Alicia","El nombre de la rosa"),
		("Juan","La hija del canibal"),("Pepe","Odesa"),
		("Alicia","La ciudad de las bestias")]

	obtain :: Database -> Person -> [Book]
	obtain dBase thisPerson =
		[book | (person,book) <- dBase, person == thisPerson]


	-- Exercise 11 --

	borrow :: Database -> Book -> Person -> Database
	borrow dBase book person = dBase ++ [(person, book)]
	return' :: Database -> (Person,Book) -> Database
	return' dBase (thisPerson, thisBook) = [(person,book) | (person,book) <- dBase, person == thisPerson && book == thisBook]

	-- Exercise 12 --

	--data TreeInt = Leaf Int | Branch TreeInt TreeInt
	data Tree a = Leaf a | Branch (Tree a) (Tree a) deriving Show
	data BinTreeInt = Void | Node Int BinTreeInt BinTreeInt deriving Show

	symmetric :: Tree a -> Tree a
	symmetric (Leaf x) = (Leaf x)
	symmetric (Branch a b) = (Branch (symmetric b) (symmetric a))

	-- Exercise 13 --

	listToTree :: [a] -> Tree a
	listToTree [] = error "empty lists not allowed"
	listToTree [x] = Leaf x
	listToTree (x:xs) = Branch (Leaf x) (listToTree (xs))

	treeToList :: Tree a -> [a]
	treeToList (Leaf a) = [a]
	treeToList (Branch a b) = (treeToList a) ++ (treeToList b)

	-- Exercise 14 --

	insTree :: Int -> BinTreeInt -> BinTreeInt
	insTree i (Void) = Node i Void Void
	insTree i (Node a b c) 
		|i <= a = Node a (insTree i b) c
		|otherwise = Node a b (insTree i c) 

	-- Exercise 15 --
	-- FALTA ORDENAR LA LLISTA
	creaTree :: [Int] -> BinTreeInt
	creaTree [x] = Node x Void Void
	creaTree (x:y:xs) 
		| x <= y = (Node x Void (creaTree (y:xs)))
		| otherwise = (Node x (creaTree (y:xs)) Void)

	-- Exercise 16 --

	treeElem :: Int -> BinTreeInt -> Bool
	treeElem i (Void) = False
	treeElem i (Node a b c)
		| i == a = True
		| i < a = treeElem i b
		|otherwise = treeElem i c

	
		




