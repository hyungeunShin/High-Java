package basicJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import basicJDBC.util.DButil;

/*
 lprod_id 값을 2개를 입력받아서 두 값 중 작은 값부터 큰 값 사이의 자료 출력
 */

public class JdbcTest03 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		Scanner s = new Scanner(System.in);
		
		System.out.print("첫번째 정수 입력 >> ");
		int num1 = s.nextInt();
		System.out.print("두번째 정수 입력 >> ");
		int num2 = s.nextInt();
		
//		int max = Math.max(num1, num2);
//		int min = Math.min(num1, num2);
		
		int temp;
		if(num1 < num2) {
			temp = num1;
			num1 = num2;
			num2 = temp;
		}
		
		try {
			/*
			 * Class.forName("oracle.jdbc.driver.OracleDriver"); con =
			 * DriverManager.getConnection(url,id,pw);
			 */
			con = DButil.getConnection();
			
			String sql = "select * from lprod where lprod_id between " + num2 + " and " + num1;
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
