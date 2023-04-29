package basicJDBC.Service;

import java.util.List;

import basicJDBC.Dao.BoardDao;
import basicJDBC.Dao.IBoardDao;
import basicJDBC.VO.BoardVO;

public class BoardService implements IBoardService {
	private static BoardService instance;
	private IBoardDao dao;
	
	private BoardService() {
		dao = BoardDao.getInstance();
	}
	
	public static BoardService getInstance() {
		if(instance == null) {
			instance = new BoardService();
		}
		return instance;
	}

	@Override
	public int insertBoard(BoardVO vo) {
		return dao.insertBoard(vo);
	}

	@Override
	public int deleteBoard(int no) {
		return dao.deleteBoard(no);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		return dao.updateBoard(vo);
	}

	@Override
	public BoardVO getBoard(int no) {
		//게시글을 보면 조회수를 증가하는 작업까지 같이 수행
		int cnt = dao.setCountIncrement(no);
		
		if(cnt == 0) {
			return null;
		}
		
		return dao.getBoard(no);
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		return dao.getAllBoardList();
	}

	@Override
	public List<BoardVO> getBoardList(String title) {
		return dao.getBoardList(title);
	}

	@Override
	public int setCountIncrement(int no) {
		return dao.setCountIncrement(no);
	}
}
