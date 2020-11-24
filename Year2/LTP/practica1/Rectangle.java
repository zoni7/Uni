package practica1;


/**
 * Write a description of class Rectangle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rectangle extends Figure
{
    // instance variables - replace the example below with your own
    private double base; 
    private double height;

    /**
     * Constructor for objects of class Rectangle
     */
    public Rectangle(double x, double y, double b, double h)
    {
        super(x, y);
        base = b;
        height = h;
    }
    
    public String toString() {
        return "Rectangle:\n\t" +
            super.toString() +
            "\n\tBase: " + base +
            "\n\tHeight: " + height;
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof Rectangle)) { return false; }
        Rectangle f = (Rectangle) o;
        return super.equals(f) && base == f.base && height == f.height;
    }
    
    public double area() {
        double area;
        return area = base * height;
    }
    
    public double perimeter() {
        double per;
        return per = (base * height) * 2;    
    }
}
