package basicCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Lotto {
	private Scanner s = new Scanner(System.in);
	private Random r = new Random();
	
	public static void main(String[] args) {
		new Lotto().lottoStart();
	}
	
	public void lottoStart() {
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1:
				buyLotto();
				break;
			case 2:
				System.out.println("감사합니다");
				return;
			default:
				System.out.println("잘못 입력 하셨습니다");
			}
		}
	}
	
	public int displayMenu() {
		System.out.println();
		System.out.println("=========================================");
		System.out.println("             Lotto 프로그램              ");
		System.out.println("=========================================");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("=========================================");
		System.out.print("메뉴선택 >> ");
		return s.nextInt();
	}
	
	public void buyLotto() {
		System.out.println();
		System.out.println("Lotto 구입 시작");
		System.out.println();
		System.out.println("1000원에 로또번호 하나입니다");
		System.out.print("금액 입력>> ");
		int money = s.nextInt();
		System.out.println();
		
		if(money < 1000) {
			System.out.println("금액이 너무 적습니다. 구입 실패!!");
			return;
		} else if(money >= 101000) {
			System.out.println("금액이 너무 많습니다. 구입 실패!!");
			return;
		}
		
		Set<Integer> set = new HashSet<>();
		System.out.println("행운의 로또번호는 아래와 같습니다");
		
		for (int i = 1; i <= money/1000; i++) {
			while(set.size() < 6) {
				set.add(r.nextInt(45)+1);
			}
			List<Integer> list = new ArrayList<>(set);
			Collections.sort(list);
			System.out.print("로보또번호: "+i+" : ");
			for (int j = 0; j < list.size(); j++) {
				if(j>0) System.out.print(", ");
				System.out.print(list.get(j));
			}
			System.out.println();
			
			set.clear();
		}
		
		System.out.println("받은 금액은 "+money+"원이고 거스름돈은 "+money%1000+"원 입니다");
	}
}
