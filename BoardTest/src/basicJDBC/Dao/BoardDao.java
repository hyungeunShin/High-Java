package basicJDBC.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import basicJDBC.VO.BoardVO;
import basicJDBC.util.DButil3;

public class BoardDao implements IBoardDao {
	private static BoardDao instance;
	private BoardDao() {
		
	}
	public static BoardDao getInstance() {
		if(instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	
	@Override
	public int insertBoard(BoardVO vo) {
		Connection con = null;
		PreparedStatement pst = null;
		int cnt = 0;
		
		try {
			con = DButil3.getConnection();
			String sql = "insert into jdbc_board values(board_seq.nextval, ?, ?, sysdate, 0, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, vo.getBoard_title());
			pst.setString(2, vo.getBoard_writer());
			pst.setString(3, vo.getBoard_content());
			
			cnt = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				
			}
		}
		return cnt;
	}
	
	@Override
	public int deleteBoard(int no) {
		Connection con = null;
		PreparedStatement pst = null;
		int cnt = 0;
		
		try {
			con = DButil3.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, no);
			
			cnt = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				
			}
		}
		return cnt;
	}
	
	@Override
	public int updateBoard(BoardVO vo) {
		Connection con = null;
		PreparedStatement pst = null;
		int cnt = 0;
		
		try {
			con = DButil3.getConnection();
			String sql = "update jdbc_board set board_title = ?, board_content = ? where board_no = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, vo.getBoard_title());
			pst.setString(2, vo.getBoard_content());
			pst.setInt(3, vo.getBoard_no());
			
			cnt = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				
			}
		}
		return cnt;
	}
	
	@Override
	public BoardVO getBoard(int no) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		BoardVO vo = null;
		
		try {
			con = DButil3.getConnection();
			String sql = "select * from jdbc_board where board_no = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, no);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				vo = new BoardVO();
				vo.setBoard_no(rs.getInt(1));
				vo.setBoard_title(rs.getString(2));
				vo.setBoard_writer(rs.getString(3));
				vo.setBoard_date(rs.getDate(4));
				vo.setBoard_cnt(rs.getInt(5));
				vo.setBoard_content(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				
			}
		}
		return vo;
	}
	
	@Override
	public List<BoardVO> getAllBoardList() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<BoardVO> list = null;
		
		try {
			con = DButil3.getConnection();
			String sql = "select * from jdbc_board order by board_no desc";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			list = new ArrayList<>();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoard_no(rs.getInt(1));
				vo.setBoard_title(rs.getString(2));
				vo.setBoard_writer(rs.getString(3));
				vo.setBoard_date(rs.getDate(4));
				vo.setBoard_cnt(rs.getInt(5));
				vo.setBoard_content(rs.getString(6));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				
			}
		}
		return list;
	}
	
	@Override
	public List<BoardVO> getBoardList(String title) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<BoardVO> list = null;
		
		try {
			con = DButil3.getConnection();
			String sql = "select * from jdbc_board where board_title like '%' || ? || '%' order by board_no desc";
			pst = con.prepareStatement(sql);
			pst.setString(1, title);
			rs = pst.executeQuery();
			
			list = new ArrayList<>();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoard_no(rs.getInt(1));
				vo.setBoard_title(rs.getString(2));
				vo.setBoard_writer(rs.getString(3));
				vo.setBoard_date(rs.getDate(4));
				vo.setBoard_cnt(rs.getInt(5));
				vo.setBoard_content(rs.getString(6));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				
			}
		}
		return list;
	}
	
	@Override
	public int setCountIncrement(int no) {
		Connection con = null;
		PreparedStatement pst = null;
		int cnt = 0;
		
		try {
			con = DButil3.getConnection();
			String sql = "update jdbc_board set board_cnt = board_cnt + 1 where board_no = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, no);
			
			cnt = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				
			}
		}
		return cnt;
	}
}
