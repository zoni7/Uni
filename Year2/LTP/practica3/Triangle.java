package practica3;


/**
 * class Triangle.
 * 
 * @author LTP
 * @version 2020-21
 */

public class Triangle extends Figure {
    private double base; 
    private double height;
    private double side1;
    private double side2;

    public Triangle(double x, double y, double b, double h) {
        super(x, y);
        base = b;
        height = h;
    }

    public String toString() {
        return "Triangle:\n\t" +
            super.toString() +
            "\n\tBase: " + base +
            "\n\tHeight: " + height;
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof Triangle)) { return false; }
        Triangle f = (Triangle) o;
        return super.equals(f) && base == f.base && height == f.height;
    }
    
    public double area() {
        double area;
        return area = base * height / 2;
    }
    
    public double perimeter() {
        double per;
        return per = side1 + side2 + base;    
    }
}