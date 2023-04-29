package board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.vo.BoardVO;
import util.MybatisSqlSessionFactory;


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
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("board.insertBoard", vo);
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}
	
	@Override
	public int deleteBoard(int no) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.delete("board.deleteBoard", no);
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}
	
	@Override
	public int updateBoard(BoardVO vo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("board.updateBoard", vo);
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}
	
	@Override
	public BoardVO getBoard(int no) {
		SqlSession session = null;
		BoardVO vo = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			vo = session.selectOne("board.getBoard",no);
		} finally {
			session.close();
		}
		return vo;
	}
	
	@Override
	public List<BoardVO> getAllBoardList() {
		SqlSession session = null;
		List<BoardVO> list = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			list = session.selectList("board.getAllBoardList");
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<BoardVO> getBoardList(String title) {
		SqlSession session = null;
		List<BoardVO> list = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			list = session.selectList("board.getBoardList",title);
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public int setCountIncrement(int no) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("board.setCountIncrement",no);
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}
}
