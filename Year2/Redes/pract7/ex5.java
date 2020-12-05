import java.net.*;
import java.io.*;
import java.util.Date;
public class ex5{
   public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(7777);
        byte[] receiveDataBuffer = new byte[1024];
        byte[] sendDataBuffer = new byte[1024];
        while(true){
           DatagramPacket receivePacket = new DatagramPacket(receiveDataBuffer, receiveDataBuffer.length);
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
        }
   }
}