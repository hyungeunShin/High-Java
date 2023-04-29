package basicDoc;

//javaDoc 파일 만들기 -> 프로그램과 메뉴얼을 같이 만들기

/**
 * 
 * @author PC-17
 * @version 1.0
 * 
 * <p>
 * 파일명 : JavaDocTest.java<br>
 * 설  명 : JavaDoc문서 작성을 위한 연습용 interface<br><br>
 * 
 * 수정이력<br>
 * --------------------------------<br>
 * 수정일자 : 2023-03-17<br>
 * 작성자   : 홍길동<br>
 * 수정내용 : 최초 작성<br>
 * </p>
 */

public interface javaDocTest {
	/**
	 * 메소드명 : methodTest<br>
	 * 설    명 : 반환값이 없는 메소드<br>
	 *  
	 * @param a 첫번째 매개변수
	 * @param b 두번째 매개변수
	 */
	public void methodTest(int a, int b);
	
	/**
	 * 메소드명 : methodAdd<br>
	 * 설    명 : 반환값이 있는 메소드<br>
	 *  
	 * @param x 첫번째 정수
	 * @param y 두번째 정수
	 * @return 처리된 결과가 정수형으로 반환
	 */
	public int methodAdd(int x, int y);
	
	/**
	 * 메소드명 : methodSub<br>
	 * 설    명 : 매개변수가 없는 메소드<br>
	 * 
	 * @return 정수형으로 반환
	 */
	public int methodSub();
}
