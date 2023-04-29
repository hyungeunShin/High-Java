package basicCollection;

import java.util.Vector;

public class VectorTest {
	public static void main(String[] args) {
		Vector<Object> v1 = new Vector<Object>();
		
		System.out.println(v1.size());
		
		//데이터 추가 : add()
		//반환값 : 추가성공시(true), 실패시(false)
		v1.add("aa");
		v1.add(new Integer(11));  //wrapper 클래스		
		v1.add(11);               
		//오토박싱, 오토언박싱이 지원
		v1.add('a');
		v1.add(true);
		boolean r = v1.add(3.14);
		
		System.out.println(v1.size());
		//System.out.println(v1);
		System.out.println(r);
		
		//데이터 추가 : addElement()  == add()와 동일
		//이전 버전의 프로그램의 호환성을 위해서 남아 있음
		v1.addElement("bb");
		System.out.println(v1);
		
		//데이터 추가하기 : add(index, 데이터)
		//'index'번째에 데이터를 끼워 넣음
		//반환값이 없음
		v1.add(0, "cc");
		System.out.println(v1);
		
		
		//데이터 꺼내오기 : get(index)
		//'index'번째 데이터를 반환
		System.out.println(v1.get(0));
		//Object타입으로 꺼내와서 캐스팅을 해야함
		String a = (String) v1.get(0);
		int temp = (int) v1.get(2);
		System.out.println(a);
		System.out.println(temp);
		
		
		//데이터 수정 : set(index, 새로운 데이터)
		//'index'번째의 데이터를 새로운 데이터로변경
		//반환값 : 원래의 데이터
		String o = (String) v1.set(0, "dd");
		System.out.println(v1);
		System.out.println(o);
		
		
		//데이터 삭제 : remove(index)
		//'index'번째 데이터를 삭제
		//반환값 : 삭제된 데이터
		String s = (String) v1.remove(0);
		System.out.println(s);
		System.out.println(v1);
		
		String str = (String) v1.remove(0);
		System.out.println(str);
		System.out.println(v1);
		
		
		//데이터 삭제 : remove(삭제할 데이터)
		//'삭제할 데이터'를 찾아서 삭제
		//'삭제할 데이터'가 여러개이면 이 중 첫번째 자료가 삭제
		//반환값 : 삭제성공(true), 실패(false)
		v1.remove("bb");
		System.out.println(v1);
		
		//삭제할 데이터가 정수형이거나 캐릭터형일 경우 반드시 객체변환 필요
		v1.remove(new Integer(11));        //1.9버전부터는 사용 불가
		v1.remove(Integer.valueOf(11));    //추천
//		v1.remove(new Character('a'));
		v1.remove(Character.valueOf('a'));
		System.out.println(v1);
		
		v1.remove(true);
		v1.remove(3.14);
		System.out.println(v1);
		
		
		v1.add(1);
		v1.add(1);
		v1.add(1);
		v1.add(1);
		System.out.println(v1);
		
		
		//전체 데이터 삭제 : clear()
		v1.clear();
		System.out.println(v1);
		System.out.println("--------------------------------------------------------------------");
		
		/*
		 * 제네릭타입(Generic Type) : 클래스 내부에서 사용할 데이터 타입을 
		 * 객체를 생성 할 때 외부에서 지정해 주는 기법으로 객체를 선언할때 <>안에
		 * 그 객체의 내부에서 사용할 데이터의 타입을 정해줌
		 * 
		 * - 선언하게 되면 그 데이터 타입 이외의 다른 종류의 데이터는 저장할 수 없다
		 * - 선언될 수 있는 데이터 타입은 클래스형 이어야 함
		 * int => Integer
		 * boolean => Boolean
		 * char => Character 등
		 * - 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다
		 * 
		 */
		
		Vector<Integer> v = new Vector<>();
		Vector<String> v2 = new Vector<>();
		
		v.add(1);
		v2.add("a");
		
		String strr = v2.get(0);
		System.out.println(strr);
		
		v2.add("b");
		v2.add("c");
		v2.add("d");
		v2.add("e");
		
		Vector<String> v3 = new Vector<>();
		v3.add("b");
		v3.add("c");
		v3.add("d");
		v3.add("f");
		
		System.out.println(v2);
		System.out.println(v3);
		
		//데이터 삭제 : removeAll(Collection 삭제)
		//벡터의 데이터 중에서 'Collection'객체 가 가지고 있는 모든 데이터 삭제
		//반환값 : 성공(true), 실패(false)
		v2.removeAll(v3);
		System.out.println(v2);
		v2.clear();
		System.out.println("------------------------------------");
		
		//벡터의 모든 데이터를 사용하고 싶으면 반복문 사용(주로 for문 사용)
		v2.add("a");
		v2.add("b");
		v2.add("c");
		v2.add("d");
		v2.add("e");
		for (int i = 0; i < v2.size(); i++) {
			System.out.println(v2.get(i));
		}
		System.out.println("------------------------------------");
		for (String string : v2) {
			System.out.println(string);
		}
	}
}
