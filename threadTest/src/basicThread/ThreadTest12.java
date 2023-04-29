package basicThread;

import java.util.Random;

/*
 3개의 스레드가 각각 알파벳 A~Z까지를 출력하는데 
 출력이 끝난 순서대로 결과를 나타내는 프로그램
 */

public class ThreadTest12 {
	public static void main(String[] args) {
		DisplayCharacter[] d = new DisplayCharacter[] {
			new DisplayCharacter("홍길동"),	
			new DisplayCharacter("이순신"),
			new DisplayCharacter("강감찬")	
		};
		
		for (DisplayCharacter dc : d) {
			dc.start();
		}
		
		for (DisplayCharacter dc : d) {
			try {
				dc.join();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println(DisplayCharacter.ranking);
	}
}


//A~Z까지 출력하는 스레드
class DisplayCharacter extends Thread {
	public static String ranking = "";
	private String name;
	
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		Random r = new Random();
		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.println(name + " : " + c);
			try {
				Thread.sleep(r.nextInt(400));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		System.out.println(name + " 출력 끝.............................");
		
		ranking += name + " ";
	}
}