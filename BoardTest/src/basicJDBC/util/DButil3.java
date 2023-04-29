package basicJDBC.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

//JDBC드라이버 로딩 및 Connection 객체 생성하고 반환

public class DButil3 {
	private static ResourceBundle bundle;
	static {
		bundle = ResourceBundle.getBundle("config.dbinfo");
		try {
			Class.forName(bundle.getString("driver"));
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패");
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(bundle.getString("url"),bundle.getString("user"),bundle.getString("pw"));
		} catch (Exception e) {
			System.out.println("연결 실패");
			return null;
		}
	}
}
