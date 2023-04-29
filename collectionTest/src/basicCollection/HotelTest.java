package basicCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HotelTest {
	Scanner s = new Scanner(System.in);
	Map<Integer, Room> map = new HashMap<>();

	public HotelTest() {
		for (int i = 1; i < 10; i++) {
			map.put(200 + i, new Room(200 + i, "싱글룸 ", "-"));
		}
		for (int i = 1; i < 10; i++) {
			map.put(300 + i, new Room(300 + i, "더블룸 ", "-"));
		}
		for (int i = 1; i < 10; i++) {
			map.put(400 + i, new Room(400 + i, "스위트룸", "-"));
		}
	}

	public static void main(String[] args) {
		new HotelTest().start();
	}

	public void start() {
		System.out.println();
		System.out.println("********************************");
		System.out.println("호텔문을 열었습니다. 안녕하세요.");
		System.out.println("********************************");
		while (true) {
			int choice = Integer.parseInt(display());
			switch (choice) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				checkRoom();
				break;
			case 4:
				System.out.println("********************************");
				System.out.println("     호텔문을 닫았습니다.");
				System.out.println("********************************");
				return;
			}
		}
	}

	public String display() {
		System.out.println("================================");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인");
		System.out.println("2.체크아웃");
		System.out.println("3.객실상태");
		System.out.println("4.업무종료");
		System.out.println("================================");
		System.out.print("선택>> ");
		return s.nextLine();
	}

	public void checkIn() {
		System.out.println();
		System.out.println("================================");
		System.out.println("체크인 작업");
		System.out.println("================================");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.println("================================");
		System.out.print("방 번호 입력>> ");
		int num = Integer.parseInt(s.nextLine());
		if (!map.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		}

		if (!"-".equals(map.get(num).getClient())) {
			System.out.println(num + "호 객실은 이미 손님이 있습니다.");
			return;
		}

		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력>> ");
		map.get(num).setClient(s.nextLine());
		System.out.println("체크인이 완료되었습니다");
	}

	public void checkOut() {
		System.out.println();
		System.out.println("================================");
		System.out.println("체크아웃 작업");
		System.out.println("================================");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방 번호 입력>> ");
		int num = Integer.parseInt(s.nextLine());

		if ("-".equals(map.get(num).getClient())) {
			System.out.println(num + "호 객실에는 체크인 한 사람이 없습니다");
			return;
		}

		System.out.println(num + "호 객실의 " + map.get(num).getClient() + "님 체크아웃을 완료하였습니다.");
		map.get(num).setClient("-");
	}

	public void checkRoom() {
		System.out.println("================================");
		System.out.println("현재 객실 상태");
		System.out.println("================================");
		System.out.println("방 번호\t방 종류\t투숙객 이름");
		System.out.println("================================");
		List<Integer> list = new ArrayList<>(map.keySet());
		Collections.sort(list);
		for (int key : list) {
			System.out.println(key + "\t" + map.get(key).getType() + "\t" + map.get(key).getClient());
//			System.out.println(map.get(key).getClient()==null ? "-" : map.get(key).getClient());
		}
	}
}

class Room {
	private int num;
	private String type;
	private String client;

	public Room(int num, String type, String client) {
		this.num = num;
		this.type = type;
		this.client = client;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}
	
	@Override
	public String toString() {
		return String.format("%d\t%s\t%s\n", num, type, client);
	}
}