package basicJDBC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import basicJDBC.util.DButil3;
import basicJDBC.vo.MemberVO;

public class MemberDao implements IMemberDao {
	
	private final Logger logger = Logger.getLogger(MemberDao.class);
	
	private static MemberDao dao;

	private MemberDao() {
	}

	public static MemberDao getInstance() {
		if (dao == null)
			dao = new MemberDao();
		return dao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DButil3.getConnection();
			logger.info("Connection객체 생성 완료");
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr) "
					+ " values ( ?, ?, ?, ?, ? ) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_pass());
			pstmt.setString(3, memVo.getMem_name());
			pstmt.setString(4, memVo.getMem_tel());
			pstmt.setString(5, memVo.getMem_addr());
			
			logger.debug("PreparedStatement 객체 생성");
			logger.debug("실행 SQL : " + sql);
			logger.debug("사용데이터 : " + memVo);
			
			cnt = pstmt.executeUpdate();
			
			logger.info("쿼리문 실행 성공");
			
		} catch (SQLException e) {
			logger.error("insert 작업 실패",e);
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
					logger.info("PreparedStatement 반납");
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
					logger.info("Connection 반납");
				} catch (SQLException e) {
				}
		}

		return cnt;
	}
	
	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DButil3.getConnection();
			logger.info("Connection객체 생성 완료");
			
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			logger.debug("PreparedStatement 객체 생성");
			
			pstmt.setString(1, memId);
			logger.debug("실행 SQL : " + sql);
			logger.debug("사용데이터 : " + memId);
			
			cnt = pstmt.executeUpdate();
			logger.info("쿼리문 실행 성공");
			
		} catch (SQLException e) {
			logger.error("delete 작업 실패",e);
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
					logger.info("PreparedStatement 반납");
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
					logger.info("Connection 반납");
				} catch (SQLException e) {
				}
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; 
		
		try {
			conn = DButil3.getConnection();
			logger.info("Connection객체 생성 완료");
			
			String sql = "update mymember set mem_pass = ?, " + " mem_name = ?, mem_tel = ?, mem_addr = ? "
					+ " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			logger.debug("PreparedStatement 객체 생성");
			
			pstmt.setString(1, memVo.getMem_pass());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			pstmt.setString(5, memVo.getMem_id());
			
			logger.debug("실행 SQL : " + sql);
			logger.debug("사용데이터 : " + memVo);
			
			cnt = pstmt.executeUpdate();
			logger.info("쿼리문 실행 성공");
		} catch (SQLException e) {
			logger.error("update 작업 실패",e);
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
					logger.info("PreparedStatement 반납");
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
					logger.info("Connection 반납");
				} catch (SQLException e) {
				}
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> memList = null; // 반환값이 저장될 변수
		
		try {
			conn = DButil3.getConnection();
			logger.info("Connection객체 생성 완료");
			
			String sql = "select * from mymember";

			pstmt = conn.prepareStatement(sql);
			logger.debug("PreparedStatement 객체 생성");
			
			rs = pstmt.executeQuery();
			logger.debug("실행 SQL : " + sql);
			logger.info("쿼리문 실행 성공");
			
			memList = new ArrayList<>(); // List객체 생성
			while (rs.next()) {
				MemberVO memVo = new MemberVO(); 

				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_pass(rs.getString("mem_pass"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_addr(rs.getString("mem_addr"));

				memList.add(memVo);
			}

		} catch (SQLException e) {
			logger.error("select 작업 실패",e);
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
					logger.info("ResultSet 반납");
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
					logger.info("PreparedStatement 반납");
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
					logger.info("Connection 반납");
				} catch (SQLException e) {
				}
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = 0; // 반환값이 저장될 변수 선언
		
		try {
			conn = DButil3.getConnection();
			logger.info("Connection객체 생성 완료");
			
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			logger.debug("PreparedStatement 객체 생성");
			
			pstmt.setString(1, memId);
			logger.debug("실행 SQL : " + sql);
			logger.debug("사용데이터 : " + memId);
			
			
			rs = pstmt.executeQuery();
			logger.info("쿼리문 실행 성공");
			
			if (rs.next()) {
				count = rs.getInt("cnt");
			}

		} catch (SQLException e) {
			logger.error("select 작업 실패",e);
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
					logger.info("ResultSet 반납");
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
					logger.info("PreparedStatement 반납");
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
					logger.info("Connection 반납");
				} catch (SQLException e) {
				}
		}
		return count;
	}

	@Override
	public int updateMember3(Map<String, String> dataMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		
		try {
			conn = DButil3.getConnection();
			logger.info("Connection객체 생성 완료");
			
			String temp = ""; 

			for (String fieldName : dataMap.keySet()) {
				if (!"memId".equals(fieldName)) {
					if (!"".equals(temp)) {
						temp += ", ";
					}
					temp += fieldName + " = ?";
				}
			}

			String sql = "update mymember set " + temp + " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			logger.debug("PreparedStatement 객체 생성");
			
			int num = 1;
			for (String fieldName : dataMap.keySet()) {
				if (!"memId".equals(fieldName)) {
					pstmt.setString(num++, dataMap.get(fieldName));
				}
			}
			pstmt.setString(num, dataMap.get("memId"));
			logger.debug("실행 SQL : " + sql);
			logger.debug("사용데이터 : " + temp + ", " + dataMap.get("memId"));

			cnt = pstmt.executeUpdate();
			logger.info("쿼리문 실행 성공");
		} catch (SQLException e) {
			logger.error("update 작업 실패",e);
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
					logger.info("PreparedStatement 반납");
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
					logger.info("Connection 반납");
				} catch (SQLException e) {
				}
		}
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		
		try {
			conn = DButil3.getConnection();
			logger.info("Connection객체 생성 완료");
			
			String sql = "update mymember set " + paramMap.get("field") + " = ? where mem_id = ? ";

			pstmt = conn.prepareStatement(sql);
			logger.debug("PreparedStatement 객체 생성");
			
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memId"));
			logger.debug("실행 SQL : " + sql);
			logger.debug("사용데이터 : " + paramMap.get("field") +  ", " + paramMap.get("data") + ", " + paramMap.get("memId"));
			
			cnt = pstmt.executeUpdate();
			logger.info("쿼리문 실행 성공");
		} catch (SQLException e) {
			logger.error("update 작업 실패",e);
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
					logger.info("PreparedStatement 반납");
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
					logger.info("Connection 반납");
				} catch (SQLException e) {
				}
		}
		return cnt;
	}
}
