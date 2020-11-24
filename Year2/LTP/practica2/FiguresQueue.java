package practica2;

import practica1.Figure;
import practica2.librerias.implementaciones.QueueAL;

/**
 * class FiguresQueue.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class FiguresQueue<T extends Figure> extends QueueAL<T> { 
    
    QueueAL<Figure> queue = new QueueAL<Figure>();
    public double area() {
        double sumArea = 0.0;
        QueueAL<Figure> aux = new QueueAL<Figure>();
        aux = queue;
        for(int i = 0; i < queue.size();i++) {
            sumArea = sumArea + aux.dequeue().area();
            
        }
        return sumArea;
    }
}