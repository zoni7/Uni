package test;

// Command :
// sudo ./openjml -esc /mnt/c/Users/USUARIO/Desktop/Uni-Odin/Year3/MFI/openjml/A.java
import org.jmlspecs.annotation.*;

public class A {

	//@ requires k <= 0;
	//@ ensures \result == k;
	static int m(int k) {
		return -k;
	}

	//+ESC@ ensures \fresh(null);
	public static void main(@NonNull String... args) {
		System.out.println("START");
		//@ assert false;

		m(-1);
		m(0);
		m(1);

		//org.jmlspecs.utils.Utils.showStack = true;

		//@ assert args.length > 0 : "Has no arguments at all";
		System.out.println("END");
	}
}
