
import org.jmlspecs.annotation.*;

public class ATM {

  //@ private invariant 0.0 <= balance;
  //@ spec_public
  private double balance;

  //@ private invariant 0.0 <= deuda && deuda <= 1000000.0;

  private double deuda;

  //@ ensures balance == 0.0;
  public ATM () {
    balance = 0.0;
  }

  //@ requires dinero > 0.0 && dinero <= balance;
  public void sacarDinero(double dinero) {
    balance = balance - dinero;
  }

 //@ requires dinero > 0.0;
  public void meterDinero(double dinero) {
    balance = balance + dinero;
  }


  public double mostrarBalance() {
    return balance;
  }

  public static void main(String[] args){

    ATM o = new ATM();
    o.meterDinero(30.0);
    o.sacarDinero(2.0);
    double balance = o.mostrarBalance();
  }
}
