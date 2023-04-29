package basicEnum;

public class EnumTest02 {
	public enum Season {
		봄("3월부터 5월까지", 15),
		여름("6월부터 8월까지", 30),
		가을("9월부터 11월까지", 18),
		겨울("12월부터 2월까지", -8);
		
		//'값'들이 저장될 변수들 선언
		private String span;
		private int temp;
		
		Season(String span, int temp) {
			this.span = span;
			this.temp = temp;
		}

		public String getSpan() {
			return span;
		}

		public int getTemp() {
			return temp;
		}

		public void setSpan(String span) {
			this.span = span;
		}

		public void setTemp(int temp) {
			this.temp = temp;
		}
		
	}
	
	public static void main(String[] args) {
		Season s = Season.valueOf("봄");
		System.out.println(s.name());
		System.out.println(s.ordinal());
		
		System.out.println(s.getSpan());
		System.out.println(s.getTemp());
		s.setTemp(10);
		for (Season ss : Season.values()) {
			System.out.println(ss.name() + " == " + ss + "==> " + ss.getSpan() + "의 평균온도 : " + ss.getTemp());
		}
	}
}
