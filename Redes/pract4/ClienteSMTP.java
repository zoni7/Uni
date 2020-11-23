import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClienteSMTP {

        static void error(String cadena) {
		System.out.println(cadena);
		System.exit(0);
	}

	public static void main(String args[]) {
	try{
		System.setProperty ("line.separator","\r\n");
		
		Socket s=new Socket("serveis-rdc.redes.upv.es", 25);	
		//Socket s=new Socket("smtp.gmail.com", 587);
		System.out.println("Conectado al servidor SMTP de serveis-rdc");
		PrintWriter salida = new PrintWriter(s.getOutputStream(),true);
		Scanner entrada=new Scanner(s.getInputStream());
		String respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("220")) {error(respuesta);}

		salida.println("HELO serveis-rdc.redes.upv.es");
		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("250")) {error(respuesta);}

		salida.println("MAIL FROM: <redes21@redes.upv.es>");
		//salida.println("MAIL FROM: <joanmatarredona@gmail.com>");
		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("250")) {error(respuesta);}

		salida.println("RCPT TO:<redes21@redes.upv.es>");
		//salida.println("RCPT TO:<joanmatarredona@gmail.com>");
		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("250")) {error(respuesta);}

		salida.println("DATA");
		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("354")) {error(respuesta);}

		// **completar** completado
		// Aqui van varios println con todo el correo 
		// electronico incluidas las cabeceras
		
		salida.println("TO: redes21@redes.upv.es");
		salida.println("From: Joan Matarredona <redes21@redes.upv.es>");
		salida.println("Subject: prueba numero 1");
		salida.println("Date: Thu, 1Oct 2020 12:30:23 +0200");
		salida.println("User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:68.0) Gecko/20100101 Thunderbird/68.10.0");
		salida.println("MINE-Version: 1.0");
		salida.println("Content-Type: text/plain; charset=utf-8; format=flowed");
		salida.println("Content-Transfer-Encoding: 7bit");
		salida.println("Content-Language: es-ES");
		salida.println("");
		salida.println("Hello there ;)");
		salida.println(".");
		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("250")) {error(respuesta);}

		salida.println("QUIT");
		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("221")) {error(respuesta);}

		s.close();
		System.out.println("Desconectado");

	} catch (UnknownHostException e) {
		System.out.println("Host desconocido");
		System.out.println(e);
	} catch (IOException e) {
		System.out.println("No se puede conectar");
		System.out.println(e);
	}
	}
}