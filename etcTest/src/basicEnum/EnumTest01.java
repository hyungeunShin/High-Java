package basicEnum;

public class EnumTest01 {
	//클래스안에 내부클래스
	public enum Color { RED,GREEN,BLUE }
	public enum Count { ONE,TWO,THREE }
	
	public static void main(String[] args) {
		System.out.println(ConstTest.RED);
		System.out.println(ConstTest.GREEN);
		System.out.println(ConstTest.BLUE);
		
		System.out.println(ConstTest.ONE);
		System.out.println(ConstTest.TWO);
		System.out.println(ConstTest.THREE);
		System.out.println("============================");
		
     /* if(ConstTest.RED == ConstTest.THREE) {
			System.out.println("...");
		} else {
			System.out.println("@@@");
		}
		에러는 없지만 논리적으로 틀림 */
		
		Color mycol = Color.valueOf("RED");
		Count mycnt = Count.THREE;          //valueOf와 동일
		System.out.println(mycol.name());
		System.out.println(mycnt.name());
		
		System.out.println(mycol.ordinal());
		System.out.println(mycnt.ordinal());
		
//		if(mycol == mycnt) 컴파일에러   서로 다른 종류의 열거형끼리 비교 불가능
		
		if(mycol == Color.RED) {
			System.out.println("같다");
		}
		
		//switch의 case문에 열거형을 배치할 때는 '열거형이름'을 생략하고 상수명만 기술
		switch(mycnt) {
		case ONE: System.out.println("one"); break;
		case TWO: System.out.println("two"); break;
		case THREE: System.out.println("three"); break;
		}
		
		for (Color string : Color.values()) {
			System.out.println(string + "==>" + string.ordinal());
		}
	}
}
