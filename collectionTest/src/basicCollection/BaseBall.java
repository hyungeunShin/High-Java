package basicCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class BaseBall {
	public static void main(String[] args) {
		new BaseBall().gameStart();
	}
	
	private ArrayList<Integer> list;
	private ArrayList<Integer> userlist;
	private int strike;
	private int ball;
	
	Scanner s = new Scanner(System.in);
	
	public void gameStart() {
		System.out.println("***********************");
		System.out.println("숫 자 야 구 게 임 시 작");
		System.out.println("***********************");
		System.out.println("1 ~ 9 사이의 서로 다른 숫자 3개를 입력하세요");
		createNum();
		System.out.println(list);
		int cnt = 0;
		do {
			cnt++;
			inputNum();
			count();
		} while (strike!=3);
		
		System.out.println("축하합니다");
		System.out.println("당신은 " + cnt + "번만에 맞히셨습니다");
	}

	public void createNum() {
		Set<Integer> set = new HashSet<>();
		Random r = new Random();
		while (set.size() < 3) {
			set.add(r.nextInt(9) + 1);
		}
		list = new ArrayList<>(set);
		Collections.shuffle(list);
	}

	public void inputNum() {
		int a,b,c;
		
		do {
			System.out.print("숫자입력>> ");
			a = s.nextInt();
			System.out.print("숫자입력>> ");
			b = s.nextInt();
			System.out.print("숫자입력>> ");
			c = s.nextInt();
			if(a==b || b==c || a==c) {
				System.out.println("중복값이 있습니다. 다시 입력하세요.");
			}
		} while(a==b || b==c || a==c);
		
		userlist = new ArrayList<>();
		userlist.add(a);
		userlist.add(b);
		userlist.add(c);
	}
	
	public void count() {
		strike = 0;
		ball = 0;
		
		for (int i = 0; i < userlist.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if(userlist.get(i)==list.get(j)) {
					if(i==j) {
						strike++;
					} else {
						ball++;
					}
				}
			}
		}
		
		System.out.println(userlist.get(0)+","+userlist.get(1)+","+userlist.get(2)+" ==> "+strike+"S"+ball+"B");
	}
}