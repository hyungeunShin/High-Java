package basicIO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIOTest {
	public static void main(String[] args) {
		Member m1 = new Member("홍길동", 20, "대전");
		Member m2 = new Member("홍길서", 21, "서울");
		Member m3 = new Member("홍길남", 22, "경기");
		Member m4 = new Member("홍길북", 23, "인천");
		
		try {
			//객체를 파일에 저장
			FileOutputStream fout = new FileOutputStream("d:/d_other/memObj.bin");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			
			//쓰기(저장) 
			System.out.println("객체 저장 시작");
			
			oout.writeObject(m1);
			oout.writeObject(m2);
			oout.writeObject(m3);
			oout.writeObject(m4);
			oout.writeObject(null);  //마지막에 null을 저장하면 읽어올때 EOFException 방지
			
			System.out.println("객체 저장 완료");
			
			oout.close();
			
			System.out.println("===================================");
			
			//저장된 객체를 읽어와 그 내용을 화면에 출력
			ObjectInputStream oin = new ObjectInputStream(
					new BufferedInputStream(
					new FileInputStream("d:/d_other/memObj.bin")));
			
			Object obj;
			
			System.out.println("객체 읽기 작업 시작");
			System.out.println("-------------------------------------");
			
			//readObject()는 데이터가 끝까지 다 읽으면 EOFException 발생
			while( (obj=oin.readObject()) != null ) {
				//읽어온 데이터를 원래의 객체형으로 형변환 후 사용
				Member mem = (Member) obj;
				
				System.out.println(mem);
				System.out.println("-------------------------------------");
			}
			
			oin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

@SuppressWarnings("serial")
class Member implements Serializable {
	private String name;
	private transient int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "이름: " + name + ", 나이: " + age + ", 주소: " + addr;
	}
}