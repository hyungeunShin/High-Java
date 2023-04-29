package basicJDBC.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DButil2 {
	private static Properties pro;
	
	static {
		pro = new Properties();
		File f = new File("res/config/dbinfo.properties");
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(f);
			pro.load(fin);

			Class.forName(pro.getProperty("driver"));
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패");
		} finally {
			try {
				if(fin != null) fin.close();
			} catch (Exception e2) {
				
			}
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(pro.getProperty("url"), pro.getProperty("user"), pro.getProperty("pw"));
		} catch (Exception e) {
			System.out.println("연결 실패");
			return null;
		}
	}
}
