package basicCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSortTest02 {
	public static void main(String[] args) {
		List<Member> list = new ArrayList<>();
		
		list.add(new Member(1,"홍길동","010-1111-1111"));
		list.add(new Member(2,"이순신","010-2222-2222"));
		list.add(new Member(3,"성춘향","010-3333-3333"));
		list.add(new Member(4,"강감찬","010-4444-4444"));
		list.add(new Member(5,"일지매","010-5555-5555"));
		list.add(new Member(6,"변학도","010-6666-6666"));
		
		System.out.println("정렬 전");
		for (Member member : list) {
			System.out.println(member);
		}
		
		System.out.println("----------------------------------------------------------------");
		
		Collections.sort(list);
		System.out.println("정렬 후");
		for (Member member : list) {
			System.out.println(member);
		}
		
		
		//회원번호를 기준으로 내림차순 정렬
		//=> 외부정렬 기준 클래스를 작성하여 처리(클래스 명 : SortNumDesc)
		Collections.sort(list, new SortNumDesc());
		System.out.println("정렬 후");
		for (Member member : list) {
			System.out.println(member);
		}
	}
}

class SortNumDesc implements Comparator<Member> {
	@Override
	public int compare(Member o1, Member o2) {
//		if(o1.getNum() > o2.getNum()) {
//			return -1;
//		} else if(o1.getNum() == o2.getNum()) {
//			return 0;
//		} else {
//			return 1;
//		}
		
		return o2.getNum()-o1.getNum();  //num이 양수일때만 가능, 비추천
		
		
		//Wrapper클래스를 이용하는 방법
		//return -new Integer(o1.getNum()).compareTo(o2.getNum());
		//return -Integer.compare(o1.getNum(), o2.getNum());
	}
}

//member 클래스의 회원이름을 기준으로 오름차순이 되도록 내부 정렬 기준을 추가
//=> Comparable 인터페이스를 구현
class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;

	public Member(int num, String name, String tel) {
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
	
	//이름 오름차순
	@Override
	public int compareTo(Member o) {
		return this.getName().compareTo(o.getName());
	}
}