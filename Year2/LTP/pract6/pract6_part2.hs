-- EXERCISE 6
{-
type Side = Float
type Apothem = Float
type Radius = Float

data Shape = Pentagon Side Apothem |
			Circle Radius
			deriving (Eq, Show)

perimeter :: Shape -> Float
perimeter (Pentagon s a) = 5 * s
perimeter (Circle r) = 2 * pi * r

area :: Shape -> Float
area (Pentagon s a) = (5* s * a) / 2
area (Circle r) = pi * r * r

-}

type Side = Float
type Apothem = Float
type Radius = Float
data Pentagon = Pentagon Side Apothem deriving (Eq, Show)
data Circle = Circle Radius deriving (Eq, Show)

type Height = Float
type Volume = Float

class (Eq a, Show a) => Shape a where

	perimeter :: a -> Float
	area :: a -> Float

instance Shape Pentagon where
		perimeter (Pentagon s a) = 5 * s
		area (Pentagon s a) = (5* s * a) / 2
		
instance Shape Circle where
	perimeter (Circle r) = 2 * pi * r
	area (Circle r) = pi * r * r


	
	
volumePrism :: (Shape a) => a -> Height -> Volume
volumePrism base height = (area base) * height
surfacePrism :: (Shape a) => a -> Height -> Float
surfacePrism base height = ((perimeter base) * height) + (2 * (area base))

