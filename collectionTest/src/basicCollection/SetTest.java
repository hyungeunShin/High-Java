package basicCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/* List와 Set의 차이점
 * 1. List
 * - 데이터의 순서(index)가 있다
 * - 중복되는 데이터를 저장할 수 있다
 * 
 * 2. Set
 * - 데이터의 순서가 없다
 * - 중복되는 데이터를 저장할 수 없다
 * 
 */

public class SetTest {
	public static void main(String[] args) {
		Set<Object> set = new HashSet<>();
		
		//1. 데이터 추가 : add()
		set.add("AA");
		set.add("BB");
		set.add("CC");
		set.add("DD");
		set.add(11);
		set.add(10);
		set.add(9);
		
		//Set에 중복데이터를 추가하면 false를 반환하고 데이터는 추가 되지 않는다
		System.out.println(set.add("FF"));
		System.out.println(set.add(9));
		System.out.println(set);
		System.out.println(set.size());
		System.out.println("-------------------------------------");
		
		//Set에는 데이터를 수정하는 메소드가 없다.
		//데이터를 삭제하고 다시 추가해야 한다
		//2. 데이터 삭제 : remove(삭제할 자료)
		//삭제 성공시 true, 실패시 false
		set.remove("FF");
		set.add("EE");
		System.out.println(set);
//		set.clear();
//		System.out.println(set);
		
		//Set의 데이터는 순서가 없기 떄문에 List처럼 데이터를 하나씩 불러올 수 없다
		//그래서 데이터를 하나씩 차례로 얻기 위해서 Iterator형 객체로 변환
		Iterator<Object> i = set.iterator();  //Set데이터를 Iterator로 변환
		
		//Iterator의 hasNext()메소드 => Iterator의 포인터가 가리키는 곳의 다음번째에 데이터가 있는지 검사
		//데이터가 있으면 true, 없으면 false
		while(i.hasNext()) {
			//Iterator의 next()메소드 => Iterator의 포인터를 다음번째 위치로 이동시킨 후 그곳의 데이터를 반환
			System.out.println(i.next());			
		}
		System.out.println("-------------------------------------");
		for (Object object : set) {
			System.out.println(object);
		}
		System.out.println("-------------------------------------");
		
		//1~28 3명 추첨
		//nextInt(최대값 - 최소값 + 1) + 최소값
		Random r = new Random();
		Set<Integer> set2 = new HashSet<>();
		while(set2.size() < 3) {  
			set2.add(r.nextInt(28)+1);
		}
		System.out.println(set2);
		
		//Set자료를 List로 변환
		List<Object> list = new ArrayList<>(set2);
		System.out.println(list);
	}
}
