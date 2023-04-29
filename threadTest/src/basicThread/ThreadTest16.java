package basicThread;

public class ThreadTest16 {
	private int balance;
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void deposit(int money) {
		balance += money;
	}
	
	public synchronized boolean withdraw(int money) {
		if(balance >= money) {
			//시간 지연용  
			//if안으로 들어오면 무조건 출금이 되고 return true;
			for (int i = 1; i <= 100000000; i++) {
			}
			
			balance -= money;
			System.out.println("메소드 안에 잔액 : " + balance);
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		ThreadTest16 t = new ThreadTest16();
		t.setBalance(10000);
		
		// 익명 구현체로 스레드 구현
		Runnable r = new Runnable() {
			@Override
			public void run() {
				boolean result = t.withdraw(6000);
				System.out.println("스레드에서 결과: " + result + " 잔액 : " + t.getBalance());
			}
		};
		Thread th1 = new Thread(r);
		Thread th2 = new Thread(r);
		th1.start();
		th2.start();
	}
}

