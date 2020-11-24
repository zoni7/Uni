package practica3;


/**
 * class Circle.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class Circle extends Figure implements Printable{
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
    
    public void print(char c) {
        int n = (int)radius;
        for (int j = 0; j <= n * 2; j++) {
            for (int i = 0; i <= n * 2; i++) {
                if (Math.pow(i - n, 2.0) + Math.pow(j - n, 2.0) 
                    <= (int)Math.pow(n, 2)) {
                    System.out.print(c);
                } 
                else {
                    System.out.print(" "); 
                }
            }
            System.out.println();
        }
    }
}