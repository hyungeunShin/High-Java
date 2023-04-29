package basicCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 5명의 사람 이름을 입력받아 ArrayList에 저장한 후에
 * 이 ArrayList에 저장된 이름들 중에 '김'씨 성의 이름을 찾아 모두 출력
 * (Scanner 사용)
 */

public class ArrayListTest02 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		List<String> list = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			System.out.print("이름 : ");
			String name = s.nextLine();
			list.add(name);
		}
		
		for (int i = 0; i < list.size(); i++) {
//			if(list.get(i).charAt(0) == '김') {
//				System.out.println(list.get(i));
//			}
			
//			if(list.get(i).substring(0, 1).equals("김")) {
//				System.out.println(list.get(i));
//			}
			
//			if(list.get(i).indexOf("김") == 0) {
//				System.out.println(list.get(i));
//			}
			
			if(list.get(i).startsWith("김")) {
				System.out.println(list.get(i));
			}
		}
		
		s.close();
	}
}