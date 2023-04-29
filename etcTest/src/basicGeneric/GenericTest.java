package basicGeneric;

public class GenericTest {
	public static void main(String[] args) {
		NonGeneric n1 = new NonGeneric();
		n1.setValue("가");
		
		NonGeneric n2 = new NonGeneric();
		n2.setValue(1);
		
		String a = (String) n1.getValue();
		System.out.println(a);
		
		int b = (int) n2.getValue();
		System.out.println(b);
		
//		String c = (String) n2.getValue();
//		System.out.println(c);                 에러
		
		System.out.println("=================================");
		Generic<String> g1 = new Generic<>();
		g1.setValue("가");
		
		Generic<Integer> g2 = new Generic<>();
		g2.setValue(1);
		
		String c = g1.getValue();
		System.out.println(c);
		
		int d = g2.getValue();
		System.out.println(d);
	}
}

class Generic<T> {
	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}

class NonGeneric {
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
