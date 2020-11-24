package practica3;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;


/**
 * class FiguresGroup.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class FiguresGroup {
    private static final int NUM_FIGURES = 10;
    private Figure[] figuresList = new Figure[NUM_FIGURES];
    private int numF = 0;
    
    public void add(Figure f) {
        if (this.found(f) == false){
            figuresList[numF++] = f; 
        }
    }
    
    public String toString() {
        String s = "";
        for (int i = 0; i < numF; i++) {
            s += "\n" + figuresList[i];
        }
        return s;
    }

    private boolean found(Figure f) {
        for (int i = 0; i < numF; i++) {
            if (figuresList[i].equals(f)) return true;
        }
        return false;
    }

    private boolean included(FiguresGroup g) {
        for (int i = 0; i < g.numF; i++) {
            if (!found(g.figuresList[i])) return false;
        }
        return true;
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof FiguresGroup)) { return false; }
        FiguresGroup f = (FiguresGroup) o;
        return this.included(f) && f.included(this);
    }
    
    public double area() {
        double sum = 0;
        for (int i = 0; i < this.numF; i++) {
            sum = sum + figuresList[i].area(); 
        }
        return sum;
    }
    
    public Figure greatestFigure() {
        Figure f = null;
        double bigArea = 0;
        
        for (int i = 0; i < this.numF; i++) {
            if (figuresList[i].area() > bigArea ) {
                bigArea = figuresList[i].area();
                f = figuresList[i];
            }           
        }
        
        return f;
    }
    
    public List<Figure> orderedList() {
        // we create a new LinkedList<Figure> or ArrayList<Figure> 
        // to include the ordered items
        // at the end this list will be the ordered one
        List<Figure> list = new LinkedList<Figure>();
        if (numF > 0) {
            list.add(figuresList[0]);
            for (int i=1; i < numF; i++) {
                Figure x =figuresList[i];
                int j=list.size() - 1;
                while (j >= 0 && x.compareTo(list.get(j)) < 0) {                 
                        j--;
                    }
                list.add(j + 1, x);
                }
        }                
        // In this method we return the array ordered but we don't order it
        return list;
    }
    
    public void print(char c) {
        for (int i = 0; i < numF; i++) {
            // not every figure can be printed, for instance the tiangle
            if (figuresList[i] instanceof Printable) {
                ((Printable)figuresList[i]).print(c);
            }
        }
    }
}