package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpClient {

	public UdpClient(){
		
	}
	
	public void execute(){
		
		try {
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress address = InetAddress.getByName("localhost");
			
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			
			BufferedReader bufin = new BufferedReader(new InputStreamReader(System.in));
			String sline = bufin.readLine();
			sendData = sline.getBytes();

			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 12345);
			clientSocket.send(sendPacket);
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			String fixed  = new String(receivePacket.getData());
			
			System.out.println("received : " + fixed);
			
			clientSocket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
