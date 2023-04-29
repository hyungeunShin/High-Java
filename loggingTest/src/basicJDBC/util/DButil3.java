package basicJDBC.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

//JDBC드라이버 로딩 및 Connection 객체 생성하고 반환

public class DButil3 {
	static final Logger logger = Logger.getLogger(DButil3.class);
	
	private static ResourceBundle bundle;
	
	static {
		bundle = ResourceBundle.getBundle("config.dbinfo");
		logger.info("ResourceBundle 객체 생성 - dbinfo.properties파일 읽기");
		try {
			Class.forName(bundle.getString("driver"));
			logger.debug("DB 드라이버 로딩 성공");
		} catch (Exception e) {
			//System.out.println("드라이버 로딩 실패");
			logger.error("드라이버 로딩 실패", e);
		}
	}
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			con =  DriverManager.getConnection(bundle.getString("url"),bundle.getString("user"),bundle.getString("pw"));
			logger.info("DB 연결 성공 - Connection객체 생성");
		} catch (Exception e) {
			//System.out.println("연결 실패");
			logger.error("DB 연결 실패",e);
			return null;
		}
		return con;
	}
}
