package basicTCP;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

// 소켓통신에서 메시지를 보내는 역할

public class Sender extends Thread {
	private Socket socket;
	private DataOutputStream dout;
	private String name;
	private Scanner s;
	
	public Sender(Socket socket) {
		this.socket = socket;
		s = new Scanner(System.in);
		
		System.out.print("이름 입력>> ");
		this.name = s.nextLine();
		
		try {
			dout = new DataOutputStream(this.socket.getOutputStream());
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		while(dout != null) {
			try {
				dout.writeUTF(name + " : " + s.nextLine());
			} catch (Exception e) {
			}
		}
	}
}
