package basicThread;

import javax.swing.JOptionPane;

public class ThreadTest06 {
	public static void main(String[] args) {
		Thread t1 = new input();
		Thread t2 = new count();
		
		
		t1.start();
		t2.start();
	}
}

class input extends Thread {
	//입력여부 확인, 두 개의 스레드에서 공통으로 사용
	public static boolean check = false;
	
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요...");
		check = true;   //입력이 완료되면 check를 true로 변경
		System.out.println("입력한 값 : " + str);
	}
}

class count extends Thread {
	@Override
	public void run() {
		for (int i = 10; i >= 1; i--) {
			//입력이 완료되었는지 여부 검사, 입력되면 스레드 종료
			if(input.check == true) {
				return;  //run()메소드가 종료되면 해당 스레드도 종료된다
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("시간이 초과되었습니다.");
		System.exit(0); //프로그램 종료
	}
}