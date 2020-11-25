package ex3;

/**
***** OJO!! el diÃ¡logo no es con el servidor sino con los otros clientes 
*/
import java.io.*;
import java.net.*;
import java.util.*;

class IMServer_v3 extends Thread {
	Scanner in;
	PrintWriter out;
	static ArrayList<PrintWriter> pool = new ArrayList<PrintWriter>();
	Socket s;

	public IMServer_v3 (Socket s) throws IOException {
		in = new Scanner(s.getInputStream());
		out = new PrintWriter(s.getOutputStream(),true);
		pool.add(out);
		this.s = s;
		start();
	}

	public static void main(String args[]) throws IOException {
		ServerSocket ss = new ServerSocket(7777);
		while(true) {
			Socket s = ss.accept();
			new IMServer_v3(s);
			s.getInetAddress().getHostName();
			System.out.println("New client connected (IP Address:Port): " + s.getInetAddress() + ":" + s.getPort());
		}
	}

	public void broadcast(String l, Socket s ) {
		for(PrintWriter output : pool) 
			if ( !output.equals(out) ) output.println("Client in "+ s.getInetAddress() + ":" + s.getPort() + " says: " + l);
	}

	public void run() { 
		broadcast("NEW CLIENT", s); // or System.out.println
		try {
			while(true) {
				String line = in.nextLine();
				if (line.equalsIgnoreCase("quit")) break; else broadcast(line,s);
			}
		} catch (Exception e) {}
		pool.remove(out); 
		out.close();
		broadcast("CLIENT GONE",s);
	}
}