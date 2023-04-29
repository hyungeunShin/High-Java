package basicThread;

public class ThreadTest15 {
	public static void main(String[] args) {
		ShareObject s = new ShareObject();
		
		TestThread t1 = new TestThread("1번 스레드", s);
		TestThread t2 = new TestThread("2번 스레드", s);
		
		t1.start();
		t2.start();
	}
}

class ShareObject {
	private int sum = 0;
	
	//메소드 동기화
	/* public synchronized void add() { 
		int n = sum;
		
		n += 10;
		
		sum = n;
		
		System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
	} */
	
	//동기화 블록사용
	public void add() {
		synchronized (this) {
			int n = sum;
			
			n += 10;
			
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}
	}
}

class TestThread extends Thread {
	private ShareObject so;

	public TestThread(String name, ShareObject so) {
		super(name);
		this.so = so;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			so.add();
		}
	}
}
