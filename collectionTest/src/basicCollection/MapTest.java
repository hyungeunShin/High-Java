package basicCollection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
Map ==> key값과 value값을 한 쌍으로 관리하는 객체
- key : 중복을 허용하지 않고 순서(index)가 없다 => (Set의 특징을 가진다)
- value : 중복을 허용한다
*/

public class MapTest {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>();
		
		//1. 자료추가 : put(key값, value값)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1111-1111");
		System.out.println(map);
		
		
		//자료 수정 : 데이터를 추가할 때 key값이 같으면 나중에 입력한 값으로 초기화
		map.put("addr", "서울");
		System.out.println(map);
		System.out.println("================================");
		
		
		//자료 삭제 : remove(key값) => key값이 같은 자료를 찾아 삭제한다
		// 삭제된 자료의 value값이 반환
//		String remove = map.remove("tel");
//		System.out.println(remove);
//		System.out.println(map);
		
		
		//자료 읽기 : get(key 값) => key값과 쌍이되는 value값이 반환
		//key값이 없으면 null 반환
		System.out.println(map.get("name"));
		System.out.println(map.get("name1"));
		System.out.println("================================");
		
		
		//key값의 존재여부를 확인 : containsKey(key값)
		//해당 key값이 있으면 true, 없으면 false
		System.out.println(map.containsKey("name"));
		System.out.println(map.containsKey("name1"));
		System.out.println("================================");
		
		
		//===========================================================================
		
		//Map에 저장된 모든 데이터를 읽어와 사용하기
		
		//1. 모든 key값을 가져와 처리하기 => keySet() 메소드를 이용
		//Map에 있는 모든 key값을 읽어와 Set형으로 반환
		Set<String> set = map.keySet(); 
		
		//1-1) keySet()을 Iterator로 처리
		Iterator<String> i = set.iterator();
		while(i.hasNext()) {
			String key = i.next();  //키 값을 가져옴
			String value = map.get(key);
			
			System.out.println(key + " : " + value);
		}
		System.out.println("================================");
		
		//1-2) keySet()을 향상된 for문으로 처리
		for (String key : set) {
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("================================");
		
		
		//2. value값만 가져와 처리
		for (String value : map.values()) {
			System.out.println(value);
		}
		//===========================================================================
	}
}
