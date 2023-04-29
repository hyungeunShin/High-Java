package basicTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {
	public static void main(String[] args) {
		new MultiChatClient().clientStart();
	}
	
	public void clientStart() {
		Socket socket = null;
		try {
			socket = new Socket("localhost",7777);
			System.out.println("서버에 연결되었습니다...");
			System.out.println();
			
			//스레드===========================================================
			ClientSend t1 = new ClientSend(socket);
			ClientReceive t2 = new ClientReceive(socket);
			
			t1.start();
			t2.start();
		} catch (Exception e) {
		}
	}
	
	//전송용 스레드
	class ClientSend extends Thread {
		private Socket socket;
		private DataInputStream din;
		private DataOutputStream dout;
		private String name;
		private Scanner s;
		
		public ClientSend(Socket socket) {
			this.socket = socket;
			s = new Scanner(System.in);
			
			try {
				din = new DataInputStream(this.socket.getInputStream());
				dout = new DataOutputStream(this.socket.getOutputStream());
				
				while(true) {
					System.out.print("이름 >> ");
					String name = s.nextLine();
					dout.writeUTF(name);
					
					String result = din.readUTF();
					
					if("대화명중복".equals(result)) {
						System.out.println(name + "은 중복됩니다");
						System.out.println("다른 대화명 입력하세요");
					} else {
						this.name = name;
						System.out.println(name + "로 입장합니다");
						break;
					}
				}
			} catch (Exception e) {
				
			}
		}
		
		@Override
		public void run() {
			try {
				while(dout != null) {
					//입력한 메시지 서버로 전송
					System.out.print("대화 입력>> ");
					dout.writeUTF(this.name + " : " + s.nextLine());
				}
			} catch (Exception e) {
			}
		}
	}
	
	//수신용 스레드
	class ClientReceive extends Thread {
		private Socket socket;
		private DataInputStream din;
		
		public ClientReceive(Socket socket) {
			this.socket = socket;
			
			try {
				din = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
			}
		}
		
		@Override
		public void run() {
			try {
				while(din != null) {
					//서버에서 받은 메시지 화면에 출력
					System.out.println(din.readUTF());
				}
			} catch (Exception e) {
			}
		}
	}
}