package basicJDBC;

import java.util.ResourceBundle;

/*
 * ResourceBundle ==> 파일의 확장자가 .properties 인 파일의 내용을 읽어와서 
 * key값과 value값을 분리해서 정보를 가져온다 (key값=value값 형태로 작성되어 있어야 함)
 */

public class ResourceBundleTest {
	public static void main(String[] args) {
		//RresourceBundle 객체 생성
		
		//읽어올 파일을 지정할때 매개변수는 "패키지명.파일명"까지만 적고 확장자는 지정하지 않는다
		//이 객체 자체가 properties로만 사용 => 확장자는 항상 .properties 이기 때문에
		
		//객체 생성이 되면 파일내용을 읽어와 저장까지 작업완료
		ResourceBundle bundle = ResourceBundle.getBundle("config.dbinfo");
		
		//읽은자료 출력
		System.out.println("driver  : " + bundle.getString("driver"));
		System.out.println("url     : " + bundle.getString("url"));
		System.out.println("user    : " + bundle.getString("user"));
		System.out.println("pw      : " + bundle.getString("pw"));
	}
}
