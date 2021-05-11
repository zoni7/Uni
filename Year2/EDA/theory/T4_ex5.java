// exercise 5
// The private method
protected int enQueNivel(E e, NodoABB<E> actual, int nivelActual) {
  int res = -1;
  if(actual != null) {
    int resC = actual.dato.compareTo(e);
    if (resC == 0) { res = nivelActual; }
    else if (resC > 0 { res = recuperar(e, actual.izq, nivelActual + 1); })
    else {res = recuperar(e,actual.der, nivelActual + 1);}
  }
}

// Exercise 6
// O(x)  Ω(log(x))
protected int contarMayoresQue(E e, NodoABB<E> actual) {
  int res = 0;
  if (actual != null) {
    int resC = actual.dato.compareTo(e);
    if (resC = 0) {res += contarMayoresQue(e, actual.der);}
    else if (resC < 0) { res = contarMayoresQue(e, actual.der); }
    else {
      res += 1 + contarMayoresQue(e, actual.der)
               + contarMayoresQue(e, actual.izq);
    }
  }
}
// O(logx)  Ω(1)
protected int contarMayoresQue(E e, NodoABB<E> actual) {
  int res = 0;
  if (actual != null) {
    int resC = actual.dato.compareTo(e);
    if (resC = 0) {res += talla(actual.der);}
    else if (resC < 0) { res = contarMayoresQue(e, actual.der); }
    else {
      res += 1 + talla(actual.der);
               + contarMayoresQue(e, actual.izq);
    }
  }
}
