package basicCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 정렬과 관련된 interface는 Comparable, Comparator 이렇게 두 가지가 있다.
 * 
 * - Comparable은 Collection에 추가되는 데이터 자체에 정렬 기준을 넣고 싶을 때 구현하는 인터페이스이다.
 *   (내부 정렬 기준을 작성할 때 사용)
 *   
 * - Comparator는 외부에 별도로 정렬 기준을 구현하고 싶을 때 구현하는 인터페이스이다
 *   (외부 정렬 기준을 작성할 때 사용)
 *   
 * - Comparable에서는 compareTo()메소드를 재정의
 * - Comparator에서는 compare()메소드를 재정의
 * 
 * - String클래스, Wrapper클래스, Date클래스, File클래스에는 내부정렬기준이 구현되어 있다
 *   (내부정렬 기준은 오름차순)  
 */

public class ListSortTest01 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println(list);
		
		// List의 정렬은 Collections.sort()메소드를 이용
		// Collections.sort()메소드는 기본적으로 내부 정렬 기준으로 정렬
		Collections.sort(list);
		System.out.println("오름 차순 : " + list);
		
		Collections.shuffle(list);  //자료섞기
		System.out.println("섞은 자료 : " + list);
		
		//외부정렬 기준 적용
		Collections.sort(list, new desc());
		System.out.println("내림 차순 : " + list);
	}
}

/*
 * 정렬 방식을 정해주는 class 작성(외부 정렬 기준 클래스)
 * Comparator 인터페이스를 구현
 * 
 * compare() 메소드를 이용해서 정렬하고자 하는 기준을 정해준다
 * compare() 메소드를 이용해서 정렬하고자 하는 기준을 정해준다
 *    0 : 두 값이 같다
 * 양수 : 앞,뒤의 순서를 변경
 * 음수 : 앞,뒤의 순서를 바꾸지 않는다
 * 
 * ex) 오름차순 => 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수
 *     내림차순 => 앞의 값이 크면 음수, 같으면 0, 앞의 값이 작으면 양수 
 */
class desc implements Comparator<String> {
	@Override
	public int compare(String s1, String s2) {
		//내림차순
		if(s1.compareTo(s2) > 0) {
			return -1;
		} else if (s1.compareTo(s2) == 0) {
			return 0;
		} else {
			return 1;
		}
	}
}

