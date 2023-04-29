package basicCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 필드로 갖는 Student클래스를 만든다
 * 
 * 이 클래스의 생성자에서는 학번,이름,국어점수,영어점수,수학점수만 매개변수로 받아서 초기화
 * 
 * 이 Student객체는 List에 저장하여 관리한다
 * 
 * List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현
 * 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부정렬 기준도 구현
 * 
 * (등수는 List에 전체 데이터가 추가된 후에 구해서 저장)
 */

public class StudentTest {
	
	public void setRanking(List<Student> list) {
		for (Student s : list) {
			int rank = 1;
			
			for(Student s1 : list) {
				if(s.getTotal() < s1.getTotal()) {
					rank++;
				}
			}
			s.setRank(rank);
		}
	}
	
	public static void main(String[] args) {
		//StudentTest test = new StudentTest();
		
		List<Student> list = new ArrayList<>();
		
		list.add(new Student(1, "김씨", 80, 80, 80));
		list.add(new Student(3, "가씨", 70, 70, 70));
		list.add(new Student(4, "나씨", 80, 80, 80));
		list.add(new Student(2, "다씨", 80, 80, 80));
		list.add(new Student(5, "라씨", 80, 80, 80));
		
		Collections.sort(list);
		for (Student student : list) {
			System.out.println(student);
		}
		
		System.out.println("-------------------------------------------------");
		
		//test.setRanking(list);
		
		Collections.sort(list, new SortByTotal());
		int rank = 1;
		for (Student student : list) {
			student.setRank(rank++);
			System.out.println(student);
		}
	}
}

class SortByTotal implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		if(o1.getTotal() == o2.getTotal()) {
			return o1.getName().compareTo(o2.getName());
		} else {
			return -Integer.compare(o1.getTotal(), o2.getTotal());
		}
	}
}

class Student implements Comparable<Student> {
	private int num;
	private String name;
	private int korean;
	private int english;
	private int math;
	private int total;
	private int rank;

	public Student(int num, String name, int korean, int english, int math) {
		this.num = num;
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.total = korean+english+math;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorean() {
		return korean;
	}

	public void setKorean(int korean) {
		this.korean = korean;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", korean=" + korean + ", english=" + english + ", math="
				+ math + ", total=" + total + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student o) {
		return new Integer(this.getNum()).compareTo(o.getNum());
	}
}
