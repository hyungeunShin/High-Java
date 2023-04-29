package basicJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/*
 사용자로부터 Lprod_id 값을 입력 받아 입력한 값보다 lprod_id가 큰 자료들을 출력
 */

public class JdbcTest02 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "SHG98";
		String pw = "java";
		
		Scanner s = new Scanner(System.in);
		
		System.out.print("번호 >> ");
		int num = s.nextInt();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,id,pw);
			String sql = "select * from lprod where lprod_id > " + num;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
			}
		} catch (Exception e) {
		} finally {
			try {
				s.close();
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
