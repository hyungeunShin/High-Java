package basicUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

//송신할 메시지를 입력받아 상대방에게 송신하고, 상대방이 보낸 메시지를 받아서 화면에 출력
public class UdpClient {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		//송신용, 수신용 패킷 객체 변수 선언
		DatagramPacket inpacket, outpacket;
		
		//수신 받은 데이터가 저장될 byte형 배열 
		byte[] bMsg = new byte[512];
		
		try {
			DatagramSocket socket = new DatagramSocket();
			
			//접속할 상대방의 주소 정보 객체 생성
			InetAddress address = InetAddress.getByName("localhost");
			
			while(true) {
				//전송할 메시지 입력
				System.out.print("보낼 메시지 입력 >> ");
				String msg = s.nextLine();
				
				//송신용 패킷 객체 생성
				outpacket = new DatagramPacket(msg.getBytes("utf-8"), msg.getBytes("utf-8").length, address, 8888);
				
				//전송
				socket.send(outpacket);
				
				if("/end".equals(msg)) {
					break;
				}
				
				//======================================================================================================================
				
				//서버가 보낸 데이터 받아서 출력
				
				//수신용 패킷 생성
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				//수신
				socket.receive(inpacket);
				String receiveMsg = new String(inpacket.getData(), 0, inpacket.getLength(), "utf-8"); 
				System.out.println("서버의 메시지 : " + receiveMsg);
				System.out.println();
			}
			System.out.println("통신 끝...");
			socket.close();
			s.close();
		} catch (Exception e) {
		}
	}
}
