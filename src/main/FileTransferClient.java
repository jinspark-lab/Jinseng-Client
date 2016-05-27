package main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileTransferClient {

	public void Process(){
		
		try {
			System.out.println("Go Go Go");
			InetAddress host = InetAddress.getLocalHost();
			Socket socket = new Socket(host.getHostName(), 12345);
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject("FILE|temp.txt");							//Send File name (Sample Protocol => FILE|<FileName>
			oos.flush();
			
				
			BufferedOutputStream bufferOut = new BufferedOutputStream(socket.getOutputStream());
			BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(new File("temp.txt")));
			byte[] buffer = new byte[8192];
			int byteRead = 0;
			
			while((byteRead = fileIn.read(buffer)) != -1){
				bufferOut.write(buffer);
			}
			fileIn.close();
			bufferOut.close();

			oos.close();
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
