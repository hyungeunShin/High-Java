package basicJDBC.Singleton;

/*
 singleton패턴 => 객체가 1개임을 보장(외부에서 new 명령을 사용하지 못하게)
 
 - 사용 이유 : 메모리 낭비 방지, 데이터 공유
 
 - singleton 만드는 법(필수 구성 요소)
  1. 자신 class의 참조값이 저장될 변수를 private static으로 선언
  2. 생성자의 접근제한자가 private
  3. 자신 class의 인스턴스를 생성하고 반환한는 public static 메소드  
     (이 메소드 이름은 보통 getInstance())
     
*/

public class MySingleton {
	//1번
	private static MySingleton instance;
	int count = 0;
	
	//2번
	private MySingleton() {
		System.out.println(++count);
		System.out.println("생성자가 호출됨");
		System.out.println("------------------");
	}
	
	//3번
	public static MySingleton getInstance() {
		if(instance == null) {
			instance = new MySingleton();
		}
		return instance;
	}
	
	//etc...이 클래스가 처리할 내용 작성
	public void display() {
		System.out.println("일반메소드");
	}
}
