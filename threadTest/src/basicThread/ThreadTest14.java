package basicThread;

//스레드에서 객체를 공통으로 접근

/*
 원주율을 계산하는 스레드와 계산된 원주율을 출력하는 스레드가 있다.
 
 원주율을 저장하는 객체가 필요하다
 이 객체를 두 스레드에서 공통으로 사용해서 처리
 */

public class ThreadTest14 {
	public static void main(String[] args) {
		ShareData s = new ShareData();
		
		CalcPI c = new CalcPI();
		c.setShare(s);
		
		PrintPI p = new PrintPI(s);
		
		c.start();
		p.start();
	}
}

//공통으로 사용할 클래스
class ShareData {
	public double result;  //원주율이 저장될 변수
	public boolean isOk = false;  //계산이 완료되었는지
}

//원주율을 계산하는 스레드
class CalcPI extends Thread {
	private ShareData share;

	public void setShare(ShareData share) {
		this.share = share;
	}
	
	@Override
	public void run() {
		/*
		 원주율 => (1/1 - 1/3 + 1/5 - 1/7 + 1/9 .....) * 4;
		 +1 -3 +5 -7 +9  ..... 2로 나누면
		 0   1  2  3  4
		 */
		double sum = 0.0;
		
		for (int i = 1; i < 2_000_000_000; i+=2) {
			if((i/2) % 2 == 0) {
				sum += 1.0 / i;
			} else {
				sum -= 1.0 / i;
			}
		}
		
		share.result = sum * 4;
		share.isOk = true;
	}
}

//출력스레드
class PrintPI extends Thread {
	private ShareData share;

	public PrintPI(ShareData share) {
		this.share = share;
	}
	
	@Override
	public void run() {
		while(true) {
			if(share.isOk == true) {
				break;
			}
			Thread.yield();
		}
		System.out.println("결과 : " + share.result);
		System.out.println();
		System.out.println("파이값 : " + Math.PI);
	}
}