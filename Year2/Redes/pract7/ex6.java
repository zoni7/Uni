import java.net.*;
import java.io.*;
import java.util.Date;
public class ex6{
   public static void main(String[] args) throws IOException{
        DatagramSocket serverSocket = new DatagramSocket(7777);
        byte[] receiveDataBuffer = new byte[1024];
        byte[] sendDataBuffer = new byte[1024];
        while(true){
           DatagramPacket receivePacket = new DatagramPacket(receiveDataBuffer, receiveDataBuffer.length);
           try {
           serverSocket.setSoTimeout(5000); // waiting time of 5 s
        
        
           serverSocket.receive(receivePacket);
           // second part
           // receiving
           String sentence = new String(receivePacket.getData());
           // getting IPAdress and port
           InetAddress IPAddress = receivePacket.getAddress();
           int port = receivePacket.getPort();
           // stablishing what we want to send
           Date now = new Date();
           String name = now.toString() + "\n";          
           sendDataBuffer = name.getBytes();
           // creating datagram to send to client
           DatagramPacket sendPacket = new DatagramPacket(sendDataBuffer,sendDataBuffer.length, IPAddress, port);
           serverSocket.send(sendPacket);
           } catch (SocketTimeoutException e) {
               System.out.println("s'ha acabat el temps");
               serverSocket.close();
           }
        }
   }
}