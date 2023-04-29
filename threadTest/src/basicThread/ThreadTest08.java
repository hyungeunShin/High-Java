package basicThread;

//데몬스레드 => 자동 저장 스레드

public class ThreadTest08 {
	public static void main(String[] args) {
		Thread t = new AutoSaveThread();
		
		//start()전에 데몬스레드로 설정
		t.setDaemon(true);
		t.start();
		
		try {
			for (int i = 1; i <= 20; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("main 스레드 종료");
	}
}

class AutoSaveThread extends Thread {
	//작업 내용을 저장하는 메소드
	public void save() {
		System.out.println("저장되었습니다");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
			} 
			save();
		}
	}
}