
import org.jmlspecs.annotation.*;

public class ATM {

  //@ spec_public
  private double balance;

  //@ public normal_behavior
  //@   ensures this.balance == 0;
  public ATM() {
    this(0);
  }

  //@ public normal_behavior
  //@   requires balance >= 0;
  //@   ensures this.balance == balance;

  public ATM(double balance) {
    this.balance = balance;
  }
  

  //@ ensures this.balance >= 0;  
  //@ ensures \result >= 0;
  public double sacarDinero(double dineroSacar) {
    //@ assume 0 < dineroSacar <= this.balance;
    if(balance - dineroSacar >= 0){
        balance = balance - dineroSacar; 
        //@ assert this.balance >= 0;
        this.balance = -1;
        return dineroSacar;      
    }      
    //@ assert this.balance >= 0;    
    return 0;    
  }

  //@ requires dineroMeter > 0.0;
  public void meterDinero(double dineroMeter) {
    this.balance = this.balance + dineroMeter;
  }


  public double mostrarBalance() {
    return balance;
  }



  public static void main(String[] args){

    ATM o = new ATM();
    o.meterDinero(-1.0);    
    double miExtraccion=o.sacarDinero(5.0);
    double balance = o.mostrarBalance();
    
  }
}
