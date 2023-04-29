package basicJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		Connection con = null;
		Statement st = null;
		PreparedStatement pst = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "SHG98";
		String pw = "java";
		
		System.out.print("계좌번호 입력>> ");
		String no = s.nextLine();
		
		System.out.print("은행명 입력>> ");
		String name = s.nextLine();
		
		System.out.print("예금주명 입력>> ");
		String user = s.nextLine();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,id,pw);
			
			/* //statement 버전
			String sql1 = String.format("insert into bankinfo values ('%s', '%s', '%s', sysdate)", no, name, user);
			System.out.println(sql1);
			st = con.createStatement();
			
			//select ==> executeQuery()
			//insert, delete, update ==> executeUpdate() ==> 실행된 행의 개수 반환
			int cnt = st.executeUpdate(sql1); */
			
			//----------------------------------------------------------------------------------------------------------------
			
			//prepare 버전
			String sql = "insert into bankinfo values (?, ?, ?, sysdate)";
			
			//prepare객체를 생성할때 sql문을 매개변수로 넣는다
			pst = con.prepareStatement(sql);
			pst.setString(1, no);
			pst.setString(2, name);
			pst.setString(3, user);
			
			//객체를 만들때 미리 세팅해놔서 실행할때는 매개변수가 필요하지 않음
			int cnt = pst.executeUpdate();
			System.out.println(cnt);
			
		} catch (Exception e) {
		} finally {
			try {
				s.close();
				if(con != null) con.close();
				if(st != null) st.close();
				if(pst != null) pst.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
