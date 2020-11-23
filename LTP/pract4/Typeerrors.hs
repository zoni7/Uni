module Typeerrors where

  convert :: (Char, Int) -> String
  convert (c,i) = [c] ++ show i

  -- main = convert (2,'a') -- is wrong
  main = convert ('a',2)