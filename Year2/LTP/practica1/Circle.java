package practica1;


/**
 * class Circle.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class Circle extends Figure {
    private double radius;

    public Circle(double x, double y, double r) {
        super(x, y);
        radius = r;
    }

    public String toString() {
        return "Circle:\n\t" +
            super.toString() +
            "\n\tRadius: " + radius;
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof Circle)) { return false; }
        Circle f = (Circle) o;
        return super.equals(f) && radius == f.radius;
    }
    
    public double area() {
        double area;
        return area = Math.PI * radius * radius;    
    }
    
    public double perimeter() {
        double per;
        return per = Math.PI * radius * 2;    
    }
}