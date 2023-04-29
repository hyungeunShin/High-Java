package basicTCP;

import java.io.DataInputStream;
import java.net.Socket;

//소켓통신에서 메시지를 받아서 출력하는 역할

public class Receiver extends Thread {
	private Socket socket;
	private DataInputStream din;
	
	public Receiver(Socket socket) {
		this.socket = socket;
		
		try {
			this.din = new DataInputStream(this.socket.getInputStream());
		} catch (Exception e) {
		}
	}
	
	@Override
	public void run() {
		while(din != null) {
			try {
				//메시지 받아서 출력
				System.out.println(din.readUTF());
			} catch (Exception e) {
			}
		}
	}
}
