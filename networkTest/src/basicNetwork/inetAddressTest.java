package basicNetwork;

import java.net.InetAddress;

public class inetAddressTest {
	public static void main(String[] args) throws Exception {
		//InetAddress 클래스 => IP주소를 다루기 위한 클래스
		
		//www.naver.com
		InetAddress ip = InetAddress.getByName("www.naver.com");
		
		System.out.println("호스트 이름 : " + ip.getHostName());
		System.out.println("아이피 주소 : " + ip.getHostAddress());
		System.out.println("toString() : " + ip);
		
		System.out.println("==================================");
		
		//내 컴퓨터
		InetAddress localip = InetAddress.getLocalHost();
		
		System.out.println("호스트 이름 : " + localip.getHostName());
		System.out.println("아이피 주소 : " + localip.getHostAddress());
		System.out.println("toString() : " + localip);
		
		System.out.println("==================================");
		//IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] iparr = InetAddress.getAllByName("www.naver.com");
		
		for (InetAddress i : iparr) {
			System.out.println("호스트 이름 : " + i.getHostName());
			System.out.println("아이피 주소 : " + i.getHostAddress());
		}
	}
}
