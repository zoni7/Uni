import qualified Triangle
import qualified Circle

main = do
	putStrLn ("The area of the triangle is: " ++ show (Triangle.area 4 5)
		++ "\nThe area of the circle is: " ++ show (Circle.area 2))