package basicNetwork;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {
	public static void main(String[] args) throws Exception {
		//URLConnection => 애플리케이션과 URL간의 통신연결 위한 클래스
		//              => URL객체를 통해서 구할 수 있음
		
		//특정 서버의 정보와 파일 내용을 가져와 출력
		URL url = new URL("https://www.ddit.or.kr/index.php");
		
		//URLConnection 객체
		URLConnection con = url.openConnection();
		
		//Header 정보 가져오기
		Map<String, List<String>> header = con.getHeaderFields();
		
		//header정보 출력
		for (String s : header.keySet()) {
			System.out.println(s + " : " + header.get(s));
		}
		System.out.println();
		System.out.println();
		
		
		
		//해당 문서의 내용 가져오기(index.php) => html 소스 가져오기
		
		// 1 -> URLConnection 객체 이용
		//파일의 내용을 읽어오기 위한 Stream 생성
		InputStream in = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(in,"utf-8");
		BufferedReader br = new BufferedReader(isr);
		
		//자료 읽고 출력
		while(true) {
			String str = br.readLine();
			if(str == null) {
				break;
			}
			System.out.println(str);
		}
		br.close();
		
		// 2 -> URL객체의 openStream() 이용
		InputStream in2 = url.openStream();
		InputStreamReader isr2 = new InputStreamReader(in2,"utf-8");
		BufferedReader br2 = new BufferedReader(isr2);
		
		//자료 읽고 출력
		while(true) {
			String str = br2.readLine();
			if(str == null) {
				break;
			}
			System.out.println(str);
		}
		br2.close();
	}
}
