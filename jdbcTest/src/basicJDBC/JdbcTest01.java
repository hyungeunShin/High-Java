package basicJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//Java DataBase Connectivity 라이브러리를 이용한 DB자료 처리
public class JdbcTest01 {
	/*
	 JDBC를 이용한 DB처리 순서
	 1. 드라이버 로딩 => 라이브러리를 사용할 수 있게 메모리로 읽어 들이는 작업
	  ==> JDBC API버전 4이상 에서는 getConnection()메서드에 자동으로 로딩해주기 때문에 생략가능
	  Class.forName("oracle.jdbc.driver.OracleDriver")
	 
	 2. DB에 접속 
	  DiverManager.getConnection() => 접속되면 Connection 객체가 반환
	 
	 3. 질의 => SQL문장을 DB서버로 전송 후 처리 그리고 결과 반환
	  Statement 또는 PreparedStatement
	 
	 4. 반환된 결과 처리
	  1) Select문 => ResultSet에 저장되어 반환
	  2) 그 외(delete, insert, update) => 정수값이 반환
	     (반환된 정수값은 실행된 컬럼의 수)
	 
	 5. 사용한 자원 반납 => close()        
	 */
	
	public static void main(String[] args) {
		//DB작업에 필요한 객체 생성
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "SHG98";
		String pw = "java";
		
		try {
			//1. 드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB연결
			con = DriverManager.getConnection(url,id,pw);
			
			//3. 질의
			//1) SQL작성
			String sql = "select * from lprod";
			
			//2) Statement 또는 PreparedStatement 생성
			st = con.createStatement();
			
			//3) SQL문을 서버로 보내기
			rs = st.executeQuery(sql);
			
			//4) 결과처리
			//ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드 위치로 이동시키고 있으면 true
			while(rs.next()) {
				//rs.get자료형("컬럼명 또는 alias명")
				//rs.get자료형(컬럼 인덱스)
				System.out.println(rs.getInt("lprod_id"));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println("------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
