package basicIO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


/*
 1) 6. 전화번호 저장 메뉴 추가하고 구현
 (저장파일명 : phoneData.bin)
 
 2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 map에 저장
 
 3) 프로그램을 종료할 때 map의 데이터의 변화가 있으면(데이터의 추가,수정, 삭제 후)
    자료를 저장한 후 종료되도록 한다.
 */

public class PhoneBookTest {
	Scanner s = new Scanner(System.in);
	Map<String, Phone> map;
	private String fileName = "d:/d_other/phoneData.bin";
	private boolean flag = false;
	
	public static void main(String[] args) {
		new PhoneBookTest().start();
	}

	public void start() {
		map = load();
		if(map == null) {
			map = new HashMap<>();
		}
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
			case 6:
				save();
				break;
			case 0:
				if(flag) {
					save();
				}
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
		System.out.println("6. 전화번호 저장");
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
		flag = true;
	}
	
	public void update() {
		System.out.print("이름>> ");
		String name = s.nextLine();
		if(map.containsKey(name)) {
			System.out.print("수정할 전화번호>> ");
			map.get(name).setTel(s.nextLine());
			flag = true;
		} else {
			System.out.println("없는 이름입니다");
		}
	}
	
	public void delete() {
		System.out.print("삭제할 이름>> ");
		String name = s.nextLine();
		if(map.containsKey(name)) {
			System.out.println(map.remove(name).getName() + "이 삭제되었습니다");
			flag = true;
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
	
	//방법 1
	/* public void load() {
		FileInputStream fin = null;
		BufferedInputStream bin = null;
		ObjectInputStream oin = null;
		
		System.out.println("전화번호를 불러옵니다.");
		
		try {
			fin = new FileInputStream(fileName);
			bin = new BufferedInputStream(fin);
			oin = new ObjectInputStream(bin);
			
			Object obj;
			
			//map = (HashMap<String, Phone>) obj;
			
			while( (obj=oin.readObject()) != null ) {
				Phone phone = (Phone) obj;
				map.put(phone.getName(), phone);
			}
				
			System.out.println("불러오기 완료");
		} catch (Exception e) {
			System.out.println("불러오기 실패");
		} finally {
			if(oin != null) {
				try {
					oin.close();
				} catch (IOException e) {
				}
			}
		}
	} */
	
	//방법 2
	@SuppressWarnings("unchecked")
	public HashMap<String, Phone> load() {
		HashMap<String, Phone> p = null;
		File f = new File(fileName);
		
		if(!f.exists()) {
			return null;
		}
		
		ObjectInputStream oin = null;
		try {
			oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			
			p = (HashMap<String, Phone>) oin.readObject();
			
			
		} catch (Exception e) {
		} finally {
			if(oin != null) {
				try {
					oin.close();
				} catch (IOException e) {
				}
			}
		}
		
		return p;
	}
	
	public void save() {
		FileOutputStream fout = null;
		BufferedOutputStream bout = null;
		ObjectOutputStream oout = null;
		try {
			fout = new FileOutputStream(fileName);
			bout = new BufferedOutputStream(fout);
			oout = new ObjectOutputStream(bout);
			
			// 폰객체를 저장하던가 map을 저장하던가
			
			oout.writeObject(map);
			
//			for (Phone value : map.values()) {
//				oout.writeObject(value);
//			}
			oout.writeObject(null);
			
			System.out.println("저장 완료");
			
			flag = false;
		} catch (Exception e) {
			System.out.println("저장 실패");
		} finally {
			if(oout != null) {
				try {
					oout.close();
				} catch (IOException e) {
				}
			}
		}
	}
}

@SuppressWarnings("serial")
class Phone implements Serializable {
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
