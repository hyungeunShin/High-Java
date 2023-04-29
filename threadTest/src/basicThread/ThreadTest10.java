package basicThread;

public class ThreadTest10 {
	public static void main(String[] args) {
		YieldThread t1 = new YieldThread("1번 스레드");
		YieldThread t2 = new YieldThread("2번 스레드");
		
		t1.start();
		t2.start();
		
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("111111111111111111===================================");
		t1.work = false;
		
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("222222222222222222====================================");
		t1.work = true;
		
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("333333333333333333====================================");
		
		t1.stop = true;
		t2.stop = true;
	}
}

class YieldThread extends Thread {
	public boolean stop = false;
	public boolean work = true;
	
	public YieldThread(String name) {
		super(name);         //스레드 이름 설정
	}
	
	@Override
	public void run() {
		while(!stop) {   //stop이 true가 되면 반복문 종료
			if(work) {
				System.out.println(getName() + "작업 중");
			} else {
				System.out.println(getName() + "양보");
				Thread.yield();
			}
		}
	}
}