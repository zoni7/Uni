package solutionP3;


/**
 * class Rectangle.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class Rectangle extends Figure implements Printable, ComparableRange<Figure> {
    private double base; 
    private double height;

    public Rectangle(double x, double y, double b, double h) {
        super(x, y);
        base = b;
        height = h;
    }
    
    public String toString() {
        String t = "Rectangle";
        if (base == height) t = "Square";
        return t + ":\n\t" +
            super.toString() +
            "\n\tBase: " + base +
            "\n\tHeight: " + height;
    }
    
    public boolean equals(Rectangle o) { 
        if (!(o instanceof Rectangle)) { return false; }
        Rectangle r = (Rectangle) o;
        return super.equals(r) && 
              base == r.base && 
              height == r.height;
    }
    
    public double area() {
        return base * height;
    }
    
    public double perimeter() {
        return 2 * (base + height);
    }
    
    public int compareToRange(Figure o) {
        if (!(o instanceof Rectangle)) throw new ClassCastException();
        Rectangle r = (Rectangle)o;
        double sumArea = this.area() + r.area();
        double difArea = Math.abs(this.area() - r.area());
        //if (sumArea * 0.01 >= difArea) return 0;
	if (difArea <= sumArea * 0.01) return 0;
        else return this.compareTo(o);
    }

    public void print(char c) {
        int b = (int) base;
        int h = (int) height;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < b; j++) {
                System.out.print(c);
            }
            System.out.println();
        }                
    }
}