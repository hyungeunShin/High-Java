package basicThread;

// wait(), notify()를 이용해서 두 스레드가 번갈아 가면서 한번씩 실행
// wait(), notify(), notifyAll()은 동기화 영역에서만 사용 가능

public class ThreadTest18 {
	public static void main(String[] args) {
		WorkObject w = new WorkObject();
		ThreadA a = new ThreadA(w);
		ThreadB b = new ThreadB(w);
		a.start();
		b.start();
	}
}

class WorkObject {
	public synchronized void testA() {
		System.out.println("testA()-AAAAAAAAAA");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void testB() {
		System.out.println("testB()-BBBBBBBBBB");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class ThreadA extends Thread {
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObj.testA();
		}
		// 마지막에 waiting poll에 있는 스레드를 깨우기
		synchronized (workObj) {
			workObj.notify(); // 이대로 돌리면 에러(동기화영역선언해야함)
		}
	}
}

class ThreadB extends Thread {
	private WorkObject workObj;

	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObj.testB();
		}
		// 마지막에 waiting poll에 있는 스레드를 깨우기
		synchronized (workObj) {
			workObj.notify(); // 이대로 돌리면 에러(동기화영역선언해야함)
		}
	}
}
