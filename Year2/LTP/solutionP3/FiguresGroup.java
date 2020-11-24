package solutionP3;

import java.util.*;

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
    
    public void add(Figure f) { figuresList[numF++] = f; }
    
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
        if (!(o instanceof FiguresGroup)) return false;
        FiguresGroup g = (FiguresGroup) o;
        return this.included(g) && g.included(this);
    }
    
    public double area() {
        double a = 0.0;
        for (int i = 0; i < numF; i++) {
            a += figuresList[i].area();
        }
        return a;
    }
    
    public Figure greatestFigure() {
        if (numF == 0) return null;
        Figure f = figuresList[0];
        double a = figuresList[0].area();
        for (int i = 1; i < numF; i++) {
            double b = figuresList[i].area();
            if (a < b) {
                f = figuresList[i];
                a = b;
            }
        }
        return f;
    }
    
    // @SuppressWarnings("unchecked") 
    public List<Figure> orderedList() { //insercion directa
        List<Figure> l = new LinkedList<Figure>(); 
        // List<Figure> l = new ArrayList<Figure>();
        Figure x;
        if (numF > 0) l.add(figuresList[0]);        
        for (int i = 1; i < numF; i++) { //insertar los elementos de figureList
            int j = l.size() - 1;
            x = figuresList[i];
            while (j >= 0 && x.compareTo(l.get(j)) < 0) {
                j--;
            }
            l.add(j + 1, x);            
        } 
        
        return l;
        
        
    }
    
    public List<Figure> orderedList2 () {
       List<Figure> list = new LinkedList<Figure>();
       for (int i=0; i<numF; i++) {
           list.add(figuresList[i]);
       }
       Collections.sort(list);
       //System.out.println(list);
       return list;
    
}

    public void print(char c) {
        for (int i = 0; i < numF; i++) {
            if (figuresList[i] instanceof Printable) {
                ((Printable)figuresList[i]).print(c);
            }
        }
    }
}