package basicCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중 별명의 길이가
 * 제일 긴 별명을 출력
 * (별명의 길이는 모두 다르게 입력)
 */

public class ArrayListTest03 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		List<String> list = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			System.out.print("별명 입력 : ");
			String name = s.nextLine();
			list.add(name);
		}
		
		String max = list.get(0);
		
		for (int i = 1; i < list.size(); i++) {
			if(max.length() < list.get(i).length()) {
				max = list.get(i);
			}
		}
		System.out.println(max);
		
		s.close();
	}
}
