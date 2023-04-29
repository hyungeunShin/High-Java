package basicCollection;

import java.util.HashSet;
import java.util.Objects;

public class EqualsHashCodeTest {
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setNum(1);
		p1.setName("홍길동");

		Person p2 = new Person();
//		p2.setNum(2);
//		p2.setName("일지매");
		p2.setNum(1);
		p2.setName("홍길동");

		Person p3 = p1;

		System.out.println(p1 == p2);
		System.out.println(p1.equals(p2));
		// equals는 Object의 메소드, Object내부에서는 == 과 똑같다(참조비교)
		// String은 재정의를 했기때문에 true
		
		HashSet<Person> set = new HashSet<>();
		set.add(p1);
		set.add(p2);
		System.out.println(set.size());
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		System.out.println(p3.hashCode());
		//HashCode가 재정의 되지 않으면 2개 저장, 서로 다른 값을 반환
		
	}
}

//extends Object 생략되어있음
class Person {
	private int num;
	private String name;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//유일성을 보장(참조값을 기반으로 생성)
	@Override
	public int hashCode() {
		return Objects.hash(name, num);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Person))
			return false;
		Person other = (Person) obj;
		return Objects.equals(name, other.name) && num == other.num;
	}
	//해시코드로 1차 비교 equals로 2차비교
	
	
	@Override
	public String toString() {
		return "Person [num=" + num + ", name=" + name + "]";
	}
	
	/*
	@Override
	public int hashCode() {
		return Objects.hash(name, num);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(name, other.name) && num == other.num;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		// 여기까지가 같은 종류의 객체인지를 확인

		// 여기까지 오면 같은 종류이므로 형변환이 가능
		Person other = (Person) obj;
		if (this.name == null && other.name != null) {
			return false;
		}
		if (this.num == other.num && this.name == other.name) {
			return true;
		}
		if (this.num == other.num && this.name.equals(other.name)) {
			return true;
		}
		return false;
	}*/
}
