package main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;


public class ChatRoomClient {

	public InetAddress host;
	public Socket socket;
	public static byte[] ByteTrim(byte[] bytes){
		int i = bytes.length - 1;
		while(i >= 0 && bytes[i] == 0){
			--i;
		}
		return Arrays.copyOf(bytes, i+1);
	}
	private class recvMsg implements Runnable{
		public void run(){
			try {

				Scanner scans = new Scanner(socket.getInputStream());
				System.out.println("Scan - ");
				while(scans.hasNextLine()){
					String msg = scans.nextLine();
					
					System.out.println(msg);
				}
				System.out.println("Read Done- ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public ChatRoomClient(){

		try {
			host = InetAddress.getLocalHost();
			socket = new Socket(host.getHostName(), 12345);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Process(){
		
		try {
			System.out.println("Go Go Go");

			Thread a = new Thread(new recvMsg());
			a.start();
			
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			Scanner scan = new Scanner(System.in);

			while(scan.hasNextLine()){
				String msg = scan.nextLine();

				System.out.println("Console In : " + msg);

				pw.println(msg);
				
				if(msg.equals("end")){
					break;
				}
			}
			
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
