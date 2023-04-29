package basicJDBC.Singleton;

public class SingletonTest {
	public static void main(String[] args) {
		MySingleton ms = MySingleton.getInstance();
		ms.display();
		MySingleton ms2 = MySingleton.getInstance();
		ms2.display();
		
		System.out.println(ms == ms2);
		System.out.println(ms.equals(ms2)); 
	}
}
