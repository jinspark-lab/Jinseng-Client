package main;


public class Main {
	
	public static void testChat(){

		ChatRoomClient crc = new ChatRoomClient();
		crc.Process();
		
	}
	
	public static void testFileTransfer(){

		FileTransferClient ftc = new FileTransferClient();
		ftc.Process();
		
	}
	
	public static void main(String[] args){
		

		testChat();
	}

}
