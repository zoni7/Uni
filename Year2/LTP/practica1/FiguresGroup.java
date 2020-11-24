package practica1;


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
        // Cheking that the new figure is not already in the group
        if (this.found(f) == false){
            // increasing numF and adding at the end the new Figure
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
            // If we get a biger area, 
            // we upload the bigArea value and the figure to be returned
            if (figuresList[i].area() > bigArea ) {
                bigArea = figuresList[i].area();
                f = figuresList[i];
            }           
        }
        
        return f;
    }
}