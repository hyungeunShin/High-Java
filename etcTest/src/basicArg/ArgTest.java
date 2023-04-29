package basicArg;

public class ArgTest {
	
	//배열을 이용
	public static int sum(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return sum;
	}
	
	//가변형 인수 => 메소드의 인수 개수가 호출할 때마다 다를 때 사용
	// - 가변형 인수는 메소드 안에서는 배열로 처리
	// - 가변형 인수는 한가지 자료형만 사용 가능
	
	
	// - 가변형 인수와 일반적인 인수를 같이 사용할 경우에는
	// 가변형 인수를 제일 뒤쪽에 배치해야 한다
	
	
	//가변형 인수 이용
	public int sumArg(int...arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return sum;
	}
	
	public String sumArg2(String name, int...arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return name + "의 점수 : " + sum;
	}
	
	public void fn(int a) {
		
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		int[] arr1;
//		arr1 = {1,2,3,4,5};  에러
		arr1 = new int[] {1,2,3,4,5};
		
		System.out.println(sum(arr));
		System.out.println(sum(arr1));
		System.out.println(sum(new int[] {1,2,3,4,5}));
		
		
		ArgTest t = new ArgTest();
		t.fn(100);
		int b = 100;
		t.fn(b);
		
		
		System.out.println(t.sumArg(1,2,3,4,5));
		
		System.out.println(t.sumArg2("홍길동", 70,80,90));
	}
}
