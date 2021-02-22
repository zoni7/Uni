public class BoundedBuffer {
        private int first, last;
        private int numItems,capacity;
        private long items[];
    public BoundedBuffer(int size){
        capacity = size;
        items = new long[size];
        numItems = first = last = 0;
    }
    public synchronized void put(long item){
        if (numItems == capacity)
            try{wait();} catch(Exception e){};
        items[last] = item;
        last = (last + 1) % capacity;
        numItems++;
        notify();
    }
    public synchronized long get() {
        long valor;
        if (numItems == 0)
            try{wait();} catch(Exception e){};
        valor = items[first];
        first = (first + 1) % capacity;
        numItems--;
        notify();
        return valor;
    }
   }