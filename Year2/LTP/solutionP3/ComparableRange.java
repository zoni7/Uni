package solutionP3;


/**
 * interface ComparableRange.
 * 
 * @author LTP 
 * @version 2020-21
 */

public interface ComparableRange<T> extends Comparable<T> {
    // son iguales si el area se diferencia un 10%
    int compareToRange(T o);
}