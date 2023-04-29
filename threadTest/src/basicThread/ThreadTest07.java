package basicThread;

import java.util.Random;

import javax.swing.JOptionPane;

/*
 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오
 
 컴퓨터의 가위 바위 보는 난수를 이용해서 구하고
 사용자의 가위 바위 보는 showInputDialog()메소드를 이용하여 입력
 
 입력시간은 5초로 제한하고 카운트 다운을 진행
 5초안에 입력이 없으면 게임에 진것으로 처리
 
 5초안에 입력이 있으면 승패를 구해서 출력
 
 예시)
 1) 5초안에 입력X
 -------결   과--------
 시간초과로 당신이 졌습니다
 
 2) 5초안에 입력O
 -------결   과--------
 컴퓨터 : 가위
 사용자 : 바위
 결  과 : 당신이 이겼습니다 
 */

public class ThreadTest07 {
	public static boolean check = false;
	
	public static void main(String[] args) {
		Random r = new Random();
		String[] str = {"가위", "바위", "보"};
		String com = str[r.nextInt(3)];
		Thread t = new countDown();
		t.start();
		String user = null;
		do {
			user = JOptionPane.showInputDialog("가위 바위 보 게임");
//		} while (!(user.equals("가위") || user.equals("바위") || user.equals("보")));
		} while (!user.equals("가위") && !user.equals("바위") && !user.equals("보"));
		
		check = true;
		
		System.out.println("----------결  과----------");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + user);
//		if(user.equals(com)) {
//			System.out.println("결과 : 비겼습니다");
//		} else if((user.equals("보") && com.equals("바위")) || (user.equals("가위") && com.equals("보")) || (user.equals("바위") && com.equals("가위"))) {
//			System.out.println("결과 : 당신이 이겼습니다");
//		} else {
//			System.out.println("결과 : 컴퓨터가 이겼습니다");
//		}
		
		switch(user + com) {
		case "가위가위":
		case "바위바위":
		case "보보": System.out.println("결과 : 비겼습니다"); break;
		
		case "가위보":
		case "바위가위":
		case "보바위": System.out.println("결과 : 당신이 이겼습니다"); break;
		
		default: System.out.println("결과 : 컴퓨터가 이겼습니다"); break;
		}
	}
}

class countDown extends Thread {
	@Override
	public void run() {
		for (int i = 10; i >= 1; i--) {
			if(ThreadTest07.check == true) {
				return;
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
		System.out.println("----------결  과----------");
		System.out.println("시간초과로 당신이 졌습니다");
		System.exit(0);
	}
}