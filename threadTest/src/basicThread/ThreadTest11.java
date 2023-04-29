package basicThread;

/*
 Thread의 stop() 메소드를 호출하면 스레드가 바로 멈춤
 이 때 사용하던 자원을 정리하지 못하고 스레드가 종료되어 다른 스레드에 영향을 줄 수 있음
 그래서 stop()은 deprecated 되어 있음
 */

public class ThreadTest11 {
	public static void main(String[] args) {
		/*
		ThreadStopTest1 t = new ThreadStopTest1();
		t.start();
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		t.stop();
		t.setStop(true); */
		
		ThreadStopTest2 t2 = new ThreadStopTest2();
		t2.start();
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		t2.interrupt();
	}
}

class ThreadStopTest1 extends Thread {
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
		public void run() {
			while(!stop) {
				System.out.println("스레드 실행 중");
			}
			
			System.out.println("자원 정리");
			System.out.println("스레드 종료");
		}
}

//interrupt() 메소드 이용하여 스레드 정지
class ThreadStopTest2 extends Thread {
	@Override
	public void run() {
		//방법 1 => interrupt()메소드와 sleep()메소드를 이용
		/*
		try {
			while(true) {
				System.out.println("스레드 실행중");
				Thread.sleep(1);
			}
		} catch (Exception e) {
			System.out.println("interrupt 발생");
		} */
		
		
		//방법 2 => interrupt()가 호출되었는지 검사
		while(true) {
			System.out.println("실행중");
			
			// 검사방법 1 => Thread의 인스턴스 메소드인 isInterrupted() 이용
			// interrupt()가 호출되면 true 반환
			/* if(this.isInterrupted()) {
			    System.out.println("interrupt 발생");
				break;
			} */
			
			// 검사방법 2 => Thread의 정적(static) 메소드인 interrupted() 이용
			// interrupt()가 호출되면 true 반환
			if(Thread.interrupted()) {
				System.out.println("interrupt 발생");
				break;
			}
		}
		
		System.out.println("자원정리");
		System.out.println("스레드 종료");
	}
}