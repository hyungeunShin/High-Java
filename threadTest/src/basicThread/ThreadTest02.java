package basicThread;

public class ThreadTest02 {
	//main 메소드도 하나의 thread
	public static void main(String[] args) {
		// 멀티스레드 프로그램
		MyThread1 t1 = new MyThread1();
		t1.start();

		MyThread2 r = new MyThread2();
		Thread t2 = new Thread(r);
		t2.start();

		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 200; i++) {
					System.out.print("@");
					try {
						// 밀리초 단위(1000 => 1초)
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		Thread t3 = new Thread(r2);
		t3.start();
	}
}

class MyThread1 extends Thread {
	// run() 메소드에 스레드가 처리할 내용을 기술하는 곳
	@Override
	public void run() {
		for (int i = 0; i <= 200; i++) {
			System.out.print("*");
			try {
				// 밀리초 단위(1000 => 1초)
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {
	// run() 메소드에 스레드가 처리할 내용을 기술하는 곳
	@Override
	public void run() {
		for (int i = 0; i <= 200; i++) {
			System.out.print("$");
			try {
				// 밀리초 단위(1000 => 1초)
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}