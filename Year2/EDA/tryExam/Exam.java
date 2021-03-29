class Exam {

    public static  int posicionAdicional(int[] a, int[] b) {
        // Hipótesis: el elemento adicional es el último de a
        int res = a.length - 1;
        if (!(a[b.length - 1] == b[b.length - 1])) { 
        // Si la hipótesis NO es cierta, se busca la posición
        // del elemento adicional en 2 subarrays de igual tamaño
        // COMPLETAR:
        res = posicionAdicional(a, b, 0, b.length - 1);
        }
        return res;
       }
       protected static int posicionAdicional(int[] a, int[] b, int i, int f) {
        int x = (i + f) /2;
        boolean res;
        res = (a[x] ==b[x]);
        if (!res) {
            if( x == 0 ||a[x -1] == b[x -1]) return x;
            else { return posicionAdicional(a, b, i, x - 1);}
        } else { return posicionAdicional(a, b, x + 1, f);}

    }
    /*
    public static int metodo(int[] v, int x) {
        return metodo(v, x, 0, v.length - 1);
      }
    
      public static int metodo(int[] v, int x, int ini, int fin) {
        int mit = (ini + fi) / 2
        if (v[mit] == x]) return v[mit];
        int near = comparar(v, v[ini], v[fin], x);  
        if (fi - ini + 1 >= 3) {
          if (near == v[ini]) 
            return metodo(v, x, ini, mit - 1);
          if (near == v[fin]) 
            return metodo(v, x, mit + 1, fin);
        } 
        
        return near;
      }
      */
    
    public static void main(String args[]) {
        System.out.println("hola");
        int[] a = {2, 4, 16, 8, 3, 9};
        int[] b ={2, 4, 16, 8, 3};
        int c = posicionAdicional(a, b);
        System.out.println(c);

    }
}
