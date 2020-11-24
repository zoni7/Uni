package solutionP3;


/**
 * class Figure.
 * 
 * @author LTP 
 * @version 2020-21
 */

public abstract class Figure implements Comparable<Figure> {
    private double x;
    private double y;
    
    public Figure(double x, double y) {
        this.x = x; 
        this.y = y; 
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof Figure)) { return false; }
        Figure f = (Figure) o;
        return x == f.x && y == f.y;
    }
    
    public String toString() {
        return "Position: (" + x + ", " + y + ")"; 
    }
    
    public abstract double area();

    //public abstract double perimeter();
    /*
    public int compareTo(Object o) {
        if (!(o instanceof Figure)) throw new ClassCastException();
        double difArea = this.area() - ((Figure)o).area();
        if (difArea == 0) return 0;
        else if (difArea < 0) return -1;
        else return 1; 
        //return (difArea < 0) ?  -1 : (difArea == 0) ? 0 : 1;
    }
    */
    public int compareTo(Figure f) {      
        double areaF = f.area();
        double areaThis = this.area(); 
        if (areaThis < areaF) return -1;   
        if (areaThis > areaF) return +1; 
        return 0; 
        // Alternativa:
        // double difArea = this.area() - f.area();
        // return (difArea < 0) ?  -1 : (difArea == 0) ? 0 : 1;
    }
}