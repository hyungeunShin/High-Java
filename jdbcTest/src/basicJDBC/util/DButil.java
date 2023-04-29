package basicJDBC.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DButil {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패");
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SHG98", "java");
		} catch (Exception e) {
			System.out.println("연결 실패");
			return null;
		}
	}
}
