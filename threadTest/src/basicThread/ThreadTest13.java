package basicThread;

import java.util.Arrays;
import java.util.Random;

/*
 10마리의 말들이 경주하는 프로그램을 작성
 
 말은 Horse라는 이름의 스레드 클래스로 작성
 이 클래스는 말 이름(String), 등수(int), 현재 위치(int)를 멤버 변수
 그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준
 
 경기 구간은 1~50구간
 경기가 끝나면 등수 순으로 출력
 
 경기 중간중간에 각 말들의 위치를 아래와 같이 출력
 
 말이름01 : ------------ * 50    만약 4번 구간에 있으면
          : --->
          
 말이름02 : -->----------
 말이름03 : ------------>
 말이름04 : --------->---
 ...
 
 말 - 스레드
 중간 중간에 위치 출력도 스레드
 */

public class ThreadTest13 {
	public static void main(String[] args) {
		Horse[] arr = new Horse[] { 
				new Horse("01번말"),
				new Horse("02번말"),
				new Horse("03번말"),
				new Horse("04번말"),
				new Horse("05번말"),
				new Horse("06번말"),
				new Horse("07번말"),
				new Horse("08번말"),
				new Horse("09번말"),
				new Horse("10번말") 
		};		
		HorseLocate locate = new HorseLocate(arr);
		
		for (Horse horse : arr) {
			horse.start();
		}
		
		locate.start();
		
		for (Horse horse : arr) {
			try {
				horse.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			locate.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Arrays.sort(arr);
		for (Horse horse : arr) {
			System.out.println(horse);
		}
	}
}

class Horse extends Thread implements Comparable<Horse> {
	public static int currentRank = 1;
	private String horseName;
	private int rank;
	private int location;

	public Horse(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public void run() {
		Random r = new Random();
		for (int i = 1; i <= 50; i++) {
			location = i;
			try {
				Thread.sleep(r.nextInt(500));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		rank = currentRank++;
	}

	@Override
	public int compareTo(Horse o) {
		return Integer.compare(this.getRank(), o.getRank());
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "의 등수는 " + rank + "위 입니다";
	}
}


class HorseLocate extends Thread {
	private Horse[] horse;

	public HorseLocate(Horse[] horse) {
		this.horse = horse;
	}

	@Override
	public void run() {
		while (true) {
			if(Horse.currentRank == horse.length) { 
				break;
			}
			
			System.out.println();
			System.out.println();
			System.out.println();
			
			for (int i = 0; i < horse.length; i++) {
				System.out.print(horse[i].getHorseName() + " : ");
				for (int j = 1; j <= 50; j++) {
					if (horse[i].getLocation() == j) { 
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
