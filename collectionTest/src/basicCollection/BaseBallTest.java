package basicCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/*
 * Set을 이용하여 숫자 야구 게임 프로그램 작성
 * 컴퓨터의 숫자는 난수를 이용하여 계산 : 1~9사이의 값 3개
 * 스트라이크는 S, 볼은 B
 * 
 * ex) 컴퓨터 난수 ==> 957
 *     숫자입력 ==> 356 ==> 1S 0B
 *     위치와 숫자까지 같아야 스트라이크
 *     숫자입력 ==> 789 ==> 0S 2B
 *     숫자입력 ==> 975 ==> 1S 2B
 *     숫자입력 ==> 957 ==> 3S 0B
 *     
 *     축하합니다..
 *     당신은 4번만에 맞췄습니다
 */

public class BaseBallTest {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		Random r = new Random();
		
		Set<Integer> set = new HashSet<>();
		
		while(set.size() < 3) {
			set.add(r.nextInt(9)+1);
		}
		
		List<Integer> list = new ArrayList<>(set);
		
		Collections.shuffle(list);
		System.out.println(list);
		boolean run = true;
		int count = 0;
		while(run) {
			System.out.print("숫자 입력 : ");
			int a = s.nextInt();
			System.out.print("숫자 입력 : ");
			int b = s.nextInt();
			System.out.print("숫자 입력 : ");
			int c = s.nextInt();
			
			int scnt = 0;
			int bcnt = 0;
			
			
			if(a == list.get(0)) {
				scnt++;
			}
			if(b == list.get(1)) {
				scnt++;
			}
			if(c == list.get(2)) {
				scnt++;
			}
			if(a == list.get(1) || a==list.get(2)) {
				bcnt++;
			}
			if(b == list.get(0) || b==list.get(2)) {
				bcnt++;
			}
			if(c == list.get(0) || c==list.get(1)) {
				bcnt++;
			}
			
			System.out.printf("%dS %dB\n", scnt, bcnt);
			
			if((a == list.get(0)) && (b == list.get(1)) && (c == list.get(2))) {
				System.out.println("축하합니다");
				System.out.println("당신은 " + count++ + "번 만에 맞히셨습니다");
				run = false;
			}
			count++;
		}
		s.close();
	}
}
