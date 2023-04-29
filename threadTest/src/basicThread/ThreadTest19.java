package basicThread;

public class ThreadTest19 {
	public static void main(String[] args) {
		DataBox d = new DataBox();
		Producer p = new Producer(d);
		Consumer c = new Consumer(d);
		p.start();
		c.start();
	}
}

class DataBox {
	private String value;
	
	//value값이 null이면 value에 값이 생길때까지 기다리고
	//value값이 있으면 해당 문자열을 반환
	//문자열을 반환후에는 value를 다시 null로 초기화
	public synchronized String getValue() {
		if(value == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String temp = value;
		System.out.println("aaaaaaaaaaaa : " + temp);
		value = null;
		notify(); //데이터를 채워줄 스레드 깨우기
		return temp;
	}
	
	//value에 값이 있으면 value가 null이 될때까지 기다린다
	//value가 null이되면 새로운 데이터를 저장
	public synchronized void setValue(String value) {
		if(this.value != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.value = value;
		System.out.println("bbbbbbbbbb : " + this.value);
		notify();
	}
}

class Producer extends Thread {
	private DataBox box;
	
	public Producer(DataBox box) {
		this.box = box;
	}

	@Override
	public void run() {
		String[] name = {"홍길동","일지매","이순신"};
		for (int i = 0; i < name.length; i++) {
			box.setValue(name[i]);
		}
	}
}

class Consumer extends Thread {
	private DataBox box;

	public Consumer(DataBox box) {
		this.box = box;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			box.getValue();
		}
	}
}