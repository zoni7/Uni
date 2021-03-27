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
    public static void main(String args[]) {
        System.out.println("hola");
        int[] a = {2, 4, 16, 8, 3, 9};
        int[] b ={2, 4, 16, 8, 3};
        int c = posicionAdicional(a, b);
        System.out.println(c);

    }
}
