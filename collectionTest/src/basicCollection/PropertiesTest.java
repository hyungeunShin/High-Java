package basicCollection;

import java.util.Properties;

/*
 Properties객체는 Map 비슷하지만 보다 축소된 기능의 객체
 
 Map        : key값과 value값에 모든 종류의 객체를 사용할 수 있지만
 Properties : key와 value에 String만 사용 가능
 
 Map        : put(), get()
 Properties : setProperty(), getProperty() 사용
 
 Properties객체는 데이터를 파일로 입출력 할 수 있다 
 */

public class PropertiesTest {
	public static void main(String[] args) {
		Properties p = new Properties();
		
		p.setProperty("name", "홍길동");
		p.setProperty("age", "20");
		int age = 25;
		p.setProperty("age2", String.valueOf(age));
		p.setProperty("age3", ""+age);
		p.setProperty("tel", "010-1111-1111");
		p.setProperty("addr", "대전");
		
		System.out.println(p.get("name"));
		System.out.println(p.getProperty("name"));
		System.out.println(p.getProperty("age"));
		int myage = Integer.parseInt(p.getProperty("age"));
		System.out.println(myage);
		System.out.println(p.getProperty("tel"));
		System.out.println(p.getProperty("addr"));
	}
}
