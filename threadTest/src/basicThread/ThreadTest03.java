package basicThread;

public class ThreadTest03 {
	public static void main(String[] args) {
		// Thread가 실행되는 시간 체크
		Thread t = new Thread(new MyRunner());
		
		// 1970년 1월1일 0시0분0초(표준시간)부터
		// currentTimeMillis() 메소드가 실행된 시점까지의 
		// 경과한 시간을 밀리초 단위로 반환
		long start = System.currentTimeMillis();
		
		t.start();
		
		try {
			t.join(); //현재 위치에서 대상이 되는 스레드(변수 t)가 끝날때까지 기다린다
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println(end-start);
	}
}

class MyRunner implements Runnable {
	@Override
	public void run() {
		long sum = 0L;
		for (long i = 1L; i <= 1_000_000_000L; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}