package basicThread;

/*
 1 ~ 20억 까지의 합계를 구하는 프로그램을 하나의 스레드가 처리할 때와 
 여러개의 스레드가 협력해서 처리할 때의 경과 시간을 비교
 */

public class ThreadTest04 {
	public static long result = 0L;
	
	public static void main(String[] args) {
		Thread t = new SumThread(1L,2_000_000_000L);
		
		
		SumThread[] tarr = new SumThread[] {
				new SumThread(            1L,   500_000_000L),
				new SumThread(  500_000_001L, 1_000_000_000L),
				new SumThread(1_000_000_001L, 1_500_000_000L),
				new SumThread(1_500_000_001L, 2_000_000_000L)
		};
		
		long startTime = System.currentTimeMillis();
		
		t.start();
		
		try {
			t.join();
		} catch (Exception e) {
			
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독 처리 : " + (endTime - startTime));
		System.out.println();
		System.out.println();
		
		ThreadTest04.result = 0L;
		startTime = System.currentTimeMillis();
		
		for (int i = 0; i < tarr.length; i++) {
			tarr[i].start();
		}
		
		for (SumThread s : tarr) {
			try {
				s.join();
			} catch(Exception e) {
				
			}
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("4개로 처리 : " + (endTime-startTime));
		System.out.println(ThreadTest04.result);
	}
}

class SumThread extends Thread {
	//합계를 구할 영역의 시작값과 끝값
	private long start;
	private long end;
	
	public SumThread(long start, long end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void run() {
		long sum = 0L;
		for (long i = start; i <= end; i++) {
			sum += i;
		}
		ThreadTest04.result += sum;
		System.out.println(start + "부터 " + end + "까지의 합계 : " + sum);
	}
}