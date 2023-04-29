package basicCollection;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest01 {
	public static void main(String[] args) {
		//ArrayList의 기본적인 사용법은 Vector와 동일
		List<Object> list = new ArrayList<>();
		
		list.add("aaa");
		list.add("bbb");
		list.add(123);
		list.add('k');
		list.add(true);
		list.add(3.14);
		System.out.println(list);
		
		String s = (String) list.get(0);
		String s1 = (String) list.get(1);
		System.out.println(s);
		System.out.println(s1);
		
		String set = (String) list.set(0, s1);
		System.out.println(set);
		System.out.println(list);
		
		list.remove(0);
		list.remove("bbb");
		list.remove(Integer.valueOf(123));
		System.out.println(list);
		
		
		List<String> list1 = new ArrayList<>();
		list1.add("a");
		list1.add("b");
		list1.add("c");
		list1.add("d");
		list1.add("e");
		for (int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i));
		}
		System.out.println("-----------------------------");
		for (String string : list1) {
			System.out.println(string);
		}
		System.out.println("-----------------------------");
		
		//contains(비교객체) => 리스트에 저장된 데이터 중에서 '비교객체'가 있으면 true, 없으면 false
		System.out.println(list1.contains("d"));
		System.out.println(list1.contains("f"));
		
		
		//indexOf(비교객체)
		//lastIndexOf(비교객체)  
		//=> 리스트에 '비교객체'가 있으면 '비교객체'의 인덱스를 반환, 없으면 -1을 반환
		//indexOf는 앞에서부터 뒤쪽으로 검색
		//lastIndexOf는 뒤에서부터 앞쪽으로 검색
		//비교객체가 많으면 첫번째로 찾아진 데이터의 위치값을 반환
		System.out.println(list1.indexOf("d"));
		System.out.println(list1.indexOf("f"));
		
		list1.add("a");
		list1.add("b");
		list1.add("c");
		list1.add("d");
		list1.add("e");
		System.out.println(list1);
		System.out.println(list1.lastIndexOf("d"));
		
		
		//toArray() => 리스트 안의 데이터를 배열로 변환해서 반환
		//기본적으로 Object형 배열로 변환
		//toArray(new 제네릭타입명[0]) => 제네릭 타입의 배열로 변환해서 반환
		
		Object[] strArr = list1.toArray();
//		String[] strArr = (String[]) list1.toArray();     //사용불가능
		
		System.out.println(list1.size());
		System.out.println(strArr.length);
		for (int i = 0; i < strArr.length; i++) {
			System.out.println(strArr[i]);
		}
		System.out.println("-------------------------------");
		
		//제네릭 타입의 배열로 변환해서 가져오기
		String[] strArr1 = list1.toArray(new String[0]);
		for (String string : strArr1) {
			System.out.println(string);
		}
	}
}
