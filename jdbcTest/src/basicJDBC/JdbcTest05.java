package basicJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/*
 lprod 테이블에 새로운 데이터 추가하기
 lprod_gu와 lprod_nm 은 직접 입력
 lprod_id는 현재의 lprod_id중에서 제일 큰 값보다 1 크게
 
 입력받은 lprod_gu가 이미 있으면 다시 입력받아 처리
 id,gu,nm
*/

public class JdbcTest05 {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	Statement st = null;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "SHG98";
	String pw = "java";
	
	public static void main(String[] args) {
		new JdbcTest05().insertL();
	}
	
	public void insertL() {
		Scanner s = new Scanner(System.in);
		
		JdbcTest05 test = new JdbcTest05();
		
		String gu = null;
		while(true) {
			System.out.print("분류코드 입력>> ");
			gu = s.nextLine();
			
			if(test.selectGU(gu) == 0) {
				break;
			} else {
				System.out.println("중복");
			}
		}
		
		System.out.print("상품이름 입력>> ");
		String name = s.nextLine();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, pw);
			String sql = "insert into lprod values (?, ?, ?)";
			pst = con.prepareStatement(sql);
			
			pst.setInt(1, test.getID());
			pst.setString(2, gu);
			pst.setString(3, name);
			
			int update = pst.executeUpdate();
			System.out.println(update);
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				s.close();
				if(con != null) con.close();
				if(pst != null) pst.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
			}
		}
	}
	
	public int getID() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, pw);
			
			String sql = "select max(lprod_id)+1 from lprod";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			rs.next();
			return rs.getInt(1);
			
		} catch (Exception e) {
			return 0;
		} finally {
			try {
				if(con != null) con.close();
				if(st != null) st.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
			}
		}
	}
	
	public int selectGU(String gu) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, pw);
			
			String sql = "select count(*) from lprod where lprod_gu = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, gu);
			
			rs = pst.executeQuery();
			
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			return 0;
		} finally {
			try {
				if(con != null) con.close();
				if(pst != null) pst.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
			}
		}
	}
}
