package basicJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import basicJDBC.util.DButil;
import basicJDBC.util.DButil3;

/*
 회원관리 프로그램(MYMEMBER 테이블)
 ------------------
 1.추가
 2.삭제
 3.수정
 4.조회
 0.끝
 ------------------
 1) 추가 : 회원ID는 중복되지 않는다. 중복되면 다시 입력
 2) 삭제 : 회원ID를 입력받아서 처리
 3) 수정 : 회원ID는 변경X
 */

public class JdbcTest06 {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private Scanner s = new Scanner(System.in);
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(pst != null) pst.close();
			if(con != null) con.close();
		} catch (Exception e) {
		}
	}
	
	public static void main(String[] args) {
		new JdbcTest06().start();
	}
	
	public void start() {
		System.out.println("--------------------------------");
		System.out.println("       회원관리프로그램");
		System.out.println("--------------------------------");
		
		while(true) {
			int num = Integer.parseInt(display()); 
			switch(num) {
			case 1:
				insertMem();
				break;
			case 2:
				deleteMem();
				break;
			case 3:
				updateMem();
				break;
			case 4:
				selectMem();
				break;
			case 5:
				updateMem2();
				break;
			case 6:
				updateMem3();
				break;
			case 0:
				System.out.println("종료합니다");
				return;
			}
		}
	}
	
	public String display() {
		System.out.println("================================");
		System.out.println("1. 추가");
		System.out.println("2. 삭제");
		System.out.println("3. 수정 (전체수정)");
		System.out.println("4. 조회");
		System.out.println("5. 수정2 (선택항목)");
		System.out.println("6. 수정3 (입력받은 항목만)");
		System.out.println("0. 종료");
		System.out.println("================================");
		System.out.print("선택>> ");
		return s.nextLine();
	}
	
	public int findID(String id) {
		int cnt = 0;
		
		try {
			con = DButil.getConnection();
			String sql = "select count(mem_id) from mymember where mem_id = ?";
			
			pst = con.prepareStatement(sql);
			
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
		} finally {
			close();
		}
		return cnt;
	}
	
	public void insertMem() {
		String id = null;
		
		while(true) {
			System.out.print("아이디>> ");
			id = s.nextLine();
			
			if(findID(id) == 1) {
				System.out.println("중복");
			} else {
				break;
			}
		}
		
		System.out.print("비밀번호>> ");
		String pw = s.nextLine();
		
		System.out.print("이름>> ");
		String name = s.nextLine();
		
		System.out.print("전화번호>> ");
		String tel = s.nextLine();
		
		System.out.print("주소>> ");
		String addr = s.nextLine();
		
		try {
			con = DButil.getConnection();
			String sql = "insert into mymember values (?, ?, ?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, pw);
			pst.setString(3, name);
			pst.setString(4, tel);
			pst.setString(5, addr);
			
			int cnt = pst.executeUpdate();
			
			if(cnt == 1) {
				System.out.println("등록 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("등록 실패");
		} finally {
			close();
		}
	}

	public void deleteMem() {
		String id = null;
		
		while(true) {
			System.out.print("아이디>> ");
			id = s.nextLine();
			
			if(findID(id) == 1) {
				break;
			} else {
				System.out.println("없는 회원");
			}
		}
		
		try {
			con = DButil.getConnection();
			String sql = "delete from mymember where mem_id = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			
			int cnt = pst.executeUpdate();
			
			if(cnt == 1) {
				System.out.println("삭제 성공");
			}
		} catch (Exception e) {
			System.out.println("삭제 실패");
		} finally {
			close();
		}
	}

	public void updateMem() {
		String id = null;
		
		while(true) {
			System.out.print("아이디>> ");
			id = s.nextLine();
			
			if(findID(id) == 1) {
				break;
			} else {
				System.out.println("없는 회원");
			}
		}
		
		System.out.print("변경할 비밀번호>> ");
		String pw = s.nextLine();
		
		System.out.print("변경할 이름>> ");
		String name = s.nextLine();
		
		System.out.print("변경할 전화번호>> ");
		String tel = s.nextLine();
		
		System.out.print("변경할 주소>> ");
		String addr = s.nextLine();
		
		try {
			con = DButil.getConnection();
			String sql = "update mymember set mem_pass = ?, mem_name = ?, mem_tel = ?, mem_addr = ? where mem_id = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, pw);
			pst.setString(2, name);
			pst.setString(3, tel);
			pst.setString(4, addr);
			pst.setString(5, id);
			
			int cnt = pst.executeUpdate();
			
			if(cnt == 1) {
				System.out.println("수정 성공");
			}
		} catch (Exception e) {
			System.out.println("수정 실패");
		} finally {
			close();
		}
	}

	public void selectMem() {
		try {
			//con = DButil2.getConnection();
			con = DButil3.getConnection();
			String sql = "select * from mymember";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			int cnt = 0;
			while(rs.next()) {
				cnt++;
				System.out.println("아 이 디 : " + rs.getString(1));
				System.out.println("비밀번호 : " + rs.getString(2));
				System.out.println("이    름 : " + rs.getString(3));
				System.out.println("전화번호 : " + rs.getString(4));
				System.out.println("주    소 : " + rs.getString(5));
				System.out.println("--------------------------------");
			}
			
			if(cnt == 0) {
				System.out.println("등록된 회원이 없다");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("조회 실패");
		} finally {
			close();
		}
	}
	
	public void updateMem2() {
		String id = null;
		
		while(true) {
			System.out.print("아이디>> ");
			id = s.nextLine();
			
			if(findID(id) == 1) {
				break;
			} else {
				System.out.println("없는 회원");
			}
		}
		
		String what;
		
		if((what=updatewhat()) == null) {
			System.out.println("잘못선택");
			return;
		}
		
		System.out.print("수정할 정보입력>> ");
		String ss = s.nextLine();
		
		try {
			con = DButil.getConnection();
			String sql = "update mymember set " + what + " = ? where mem_id = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, ss);
			pst.setString(2, id);
			
			int cnt = pst.executeUpdate();
			
			if(cnt == 1) {
				System.out.println("수정 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("수정 실패");
		} finally {
			close();
		}
	}
	
	public String updatewhat() {
		System.out.println("------------------------");
		System.out.println("1. 비밀번호변경");
		System.out.println("2. 이름변경");
		System.out.println("3. 전화번호변경");
		System.out.println("4. 주소변경");
		System.out.println("------------------------");
		System.out.print("선택>> ");
		int num = Integer.parseInt(s.nextLine());
		String str = "";
		switch(num) {
		case 1:
			str = "mem_pass";
			return str;
		case 2:
			str = "mem_name";
			return str;
		case 3:
			str = "mem_tel";
			return str;
		case 4:
			str = "mem_addr";
			return str;
		default:
			return null;
		}
	}

	//입력받은 항목만 수정
	public void updateMem3() {
		String id = null;
		
		while(true) {
			System.out.print("아이디>> ");
			id = s.nextLine();
			
			if(findID(id) == 1) {
				break;
			} else {
				System.out.println("없는 회원");
			}
		}
		
		List<String> list = new ArrayList<>();
		
		String str = "";
		
		System.out.print("변경할 비밀번호>> ");
		String pw = s.nextLine().trim();
		if(!"".equals(pw)) {
			str = "mem_pass = " + "'" + pw + "'";
			list.add(str);
		}
		
		System.out.print("변경할 이름>> ");
		String name = s.nextLine().trim();
		if(!"".equals(name)) {
			str = "mem_name = " + "'" + name + "'";
			list.add(str);
		}
		
		System.out.print("변경할 전화번호>> ");
		String tel = s.nextLine().trim();
		if(!"".equals(tel)) {
			str = "mem_tel = " + "'" + tel + "'";
			list.add(str);
		}
		
		System.out.print("변경할 주소>> ");
		String addr = s.nextLine().trim();
		if(!"".equals(addr)) {
			str = "mem_addr = " + "'" + addr + "'";
			list.add(str);
		}
		
		try {
			con = DButil.getConnection();
			String aa = "";
			
			for (int i = 0; i < list.size(); i++) {
				if(i == list.size() - 1) {
					aa += list.get(i);
					break;
				}
				aa += list.get(i) + ", ";
			}
			
			String sql = "update mymember set "+ aa + " where mem_id = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			
			int cnt = pst.executeUpdate();
			
			if(cnt == 1) {
				System.out.println("수정 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("수정 실패");
		} finally {
			close();
		}
	}
}
