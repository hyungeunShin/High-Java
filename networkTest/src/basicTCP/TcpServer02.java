package basicTCP;

import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("서버가 준비 중입니다...");
		
		Socket socekt = server.accept();
		
		//클라이언트가 접속하면 접속한 Socket객체를
		//메시지 보내는 스레드와 받는 스레드에 넣어서
		//각각의 스레드 객체를 생성 및 실시
		
		Sender sender = new Sender(socekt);
		Receiver receiver = new Receiver(socekt);
		
		sender.start();
		receiver.start();
		
		System.out.println("서버종료");
		server.close();
	}
}
