package basicCollection;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class LottoTest {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		Set<Integer> set = new HashSet<>();
		boolean run = true;
		
		while(run) {
			System.out.println("===========================================");
			System.out.println("               Lotto 프로그램              ");
			System.out.println("-------------------------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("===========================================");
			System.out.print("메뉴 선택>> ");
			int num = Integer.parseInt(s.nextLine());
			
			switch(num) {
			case 1:
				System.out.println("Lotto 구입");
				System.out.println("1000원에 로또번호 하나입니다");
				System.out.print("금액입력 : ");
				int price = Integer.parseInt(s.nextLine());
				if(price < 1000) {
					System.out.println("금액이 너무 적습니다. 로또 구입 실패!!");
					break;
				} 
				if(price >= 101000) {
					System.out.println("금액이 너무 많습니다. 로또 구입 실패!!");
					break;
				}
				for (int i = 1; i < price/1000 + 1; i++) {
					set.clear();
					while(set.size()<6) {
						set.add(r.nextInt(45)+1);
					}
					System.out.println(i + "로또번호 : " + set);
				}
				System.out.printf("받은 금액은 %d원이고 거스름돈은 %d원입니다\n", price, price%1000);
				break;
			case 2:
				System.out.println("감사합니다");
				run=false;
			}
		}
		s.close();
	}
}
