package basicJDBC;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesTest {
	public static void main(String[] args) {
		//읽어온 정보를 저장할 properties객체 생성
		Properties pro = new Properties();
		
		//읽어올 파일명을 이용한 File객체 생성
		File f = new File("res/config/dbinfo.properties");
		FileInputStream fin = null;
		
		try {
			//파일 내용 읽어오기
			fin = new FileInputStream(f);
			
			//입력 스트림을 이용해서 내용 가져와서 properties에 저장
			pro.load(fin);
			// => 파일 내용을 읽어와 key값과 value값을 분류한 후
			//    분류된 정보를 properties객체에 추가
			
			//읽어온 정보 출력
			System.out.println("드라이버 : " + pro.getProperty("driver"));
			System.out.println("U  R  L : " + pro.getProperty("url"));
			System.out.println("U S E R : " + pro.getProperty("user"));
			System.out.println("P     W : " + pro.getProperty("pw"));
		} catch (Exception e) {
		} finally {
			try {
				if(fin != null) fin.close();
			} catch (Exception e2) {
			}
		}
	}
}
