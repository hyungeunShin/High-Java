package basicTCP;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

//TCP소켓 방식의 클라이언트 역할
//서버 접속을 시도하고 접속이 완료되어 서버에서 보낸 환영메시지를 받는다

public class TcpClient01 {
	public static void main(String[] args) throws Exception {
		/*
		  현재의 컴퓨터를 나타내는 방법
		  1. 원래의 IP 주소 :     예)192.168.146.77
		  2. 지정된 IP 주소 :     127.0.0.1
		  3. 원래의 컴퓨터 이름 : 예)DESKTOP-62TL3B4
		  4. 지정된 컴퓨터 이름 : localhost
		 */
		
		//TCP소켓 방식으로 서버에 연결하기 위해서 Socket객체를 생성한다
		
		System.out.println("서버에 연결 중입니다.");
		System.out.println();
		
		//Socket socket = new Socket("상대방 IP주소",상대방 포트번호)
		Socket socket = new Socket("localhost",7777);
		
		
		//*********************************************************************
		//서버와 연결이 완료된 상태에서 실행되는 코드
		System.out.println("서버에 연결되었습니다.");
		
		//서버에서 보낸 데이터 받기
		InputStream in = socket.getInputStream();
		DataInputStream din = new DataInputStream(in);
		
		//메시지를 받아서 화면에 출력하기
		System.out.println("서버에서 보내온 메시지 : " + din.readUTF());
		
		//닫기
		din.close();
		socket.close();
	}
}
