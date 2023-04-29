package basicTCP;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//TCP방식의 통신 프로그램으로 서버 역할을 한다.
//클라이언트와 연결이 완료되면 연결된 클라이언트에게 환영 메시지를 보낸다

public class TcpServer01 {
	public static void main(String[] args) throws Exception {
		//TCP소켓 통신을 위해 ServerSocket 객체를 생성
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("접속을 기다립니다...");
		
		//accept() => 클라이언트의 연결 요청이 올 때까지 기다린다
		//         => 클라이언트의 연결 요청이 오면 새로운 Socket객체를 생성해서
		//            클라이언트의 Socket객체와 연결
		
		Socket socket = server.accept();
		
		
		
		//*********************************************************************
		//이 accept()메소드 명령을 기술한 이후의 내용은 클라이언트와
		//연결된 후에 처리할 내용들을 작성
		System.out.println("클라이언트와 연결되었습니다...");
		System.out.println();
		
		System.out.println("접속한 고객 정보");
		System.out.println("IP 주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("Port 번호 : " + socket.getPort());
		System.out.println();
		
		System.out.println("접속이 완료된 현재 컴퓨터 정보");
		System.out.println("현재 컴퓨터의 IP주소 : " + socket.getLocalAddress());
		System.out.println("현재 컴퓨터의 Port번호 : " + socket.getLocalPort());
		System.out.println();
		System.out.println("-------------------------------------------------------------------");
		
		//클라이언트에 메시지 보내기
		//Socket객체의 OutputStream 객체를 구해서 데이터를 전송
	
		OutputStream out = socket.getOutputStream();
		DataOutputStream dout = new DataOutputStream(out);
		
		//메시지 전송 => 출력 스트림을 이용해서 출력 작업
		dout.writeUTF("환영합니다. 어서오세요...");
		System.out.println("메시지를 보냈습니다");
		
		//소켓과 스트림 닫기
		dout.close();
		socket.close();
		server.close();
	}
}
