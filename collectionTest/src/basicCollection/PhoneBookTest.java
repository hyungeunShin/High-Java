package basicCollection;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTest {

	Scanner s = new Scanner(System.in);
	Map<String, Phone> map = new HashMap<>();
	
	public static void main(String[] args) {
		new PhoneBookTest().start();
	}

	public void start() {
		while(true) {
			int num = Integer.parseInt(display());
			switch(num) {
			case 1:
				insert();
				break;
			case 2:
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				select();
				break;
			case 5:
				selectAll();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다");
				return;
			default:
				System.out.println("잘못 입력하셨습니다");
			}
		}
	}

	public String display() {
		System.out.println();
		System.out.println("=========================================");
		System.out.println("            전화번호 프로그램            ");
		System.out.println("=========================================");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("=========================================");
		System.out.print("메뉴선택 >> ");
		return s.nextLine();
	}

	public void insert() {
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요");
		System.out.print("이름>> ");
		String name = s.nextLine();
		if(map.containsKey(name)) {
			System.out.println(name + "은 이미 등록된 사람입니다.");
			return;
		}
		System.out.print("전화번호>> ");
		String tel = s.nextLine();
		System.out.print("주소>> ");
		String addr = s.nextLine();
		map.put(name, new Phone(name, tel, addr));
		System.out.println("등록되었습니다");
	}
	
	public void update() {
		System.out.print("이름>> ");
		String name = s.nextLine();
		if(map.containsKey(name)) {
			System.out.print("수정할 전화번호>> ");
			map.get(name).setTel(s.nextLine());
		} else {
			System.out.println("없는 이름입니다");
		}
	}
	
	public void delete() {
		System.out.print("삭제할 이름>> ");
		String name = s.nextLine();
		if(map.containsKey(name)) {
			System.out.println(map.remove(name).getName() + "이 삭제되었습니다");
		} else {
			System.out.println("없는 이름입니다");
		}
	}
	
	public void select() {
		System.out.print("검색할 이름>> ");
		String name = s.nextLine();
		if(map.containsKey(name)) {
			System.out.println(map.get(name));
		} else {
			System.out.println("없는 이름입니다");
		}
	}
	
	public void selectAll() {  
		System.out.println("--------------------------------------------------------------");
		System.out.println("번호\t 이름\t  전화번호\t주소");
		System.out.println("--------------------------------------------------------------");
		Set<String> set = map.keySet();
		int cnt = 1;
		for (String key : set) {
			Phone p = map.get(key);
			System.out.println(" " + cnt++ + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
		}
		System.out.println("--------------------------------------------------------------");
	}
}

class Phone {
	private String name;
	private String tel;
	private String addr;

	public Phone(String name, String tel, String addr) {
		this.name = name;
		this.tel = tel;
		this.addr = addr;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
	}
}
