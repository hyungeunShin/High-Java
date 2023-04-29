package basicThread;

//스레드의 상태를 출력

public class ThreadTest09 {
	public static void main(String[] args) {
		Thread t = new StatePrintThread(new TargetThread());
		t.start();
	}
}

//검사 대상이 되는 스레드
class TargetThread extends Thread {
	@Override
	public void run() {
		double sum = 0.0;
		for (long i = 1L; i <= 2_000_000_000L; i++) {
			sum += i;
		}
		
		try {
			Thread.sleep(1500);
		} catch (Exception e) {
			
		}
		
		for (long i = 1L; i <= 2_000_000_000L; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}

//검사 대상의 상태를 출력
class StatePrintThread extends Thread {
	private TargetThread target;
	
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true) {
			//스레드의 상태값 구하기 => getState() 메소드 이용
			State state = target.getState();
			System.out.println("현재 상태 : " + state);
			
			if(state == State.NEW) {   //상태가 NEW
				target.start();
				System.out.println("시작합니다");
			}
			
			if(state == State.TERMINATED) { //스레드 상태가 종료상태
				System.out.println("작업이 완료되었습니다");
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
}
