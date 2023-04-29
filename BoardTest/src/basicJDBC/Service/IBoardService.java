package basicJDBC.Service;

import java.util.List;

import basicJDBC.VO.BoardVO;

public interface IBoardService {
	/**
	 * vo객체인 게시글을 DB에 추가
	 * 
	 * @param vo DB에 추가할 자료가 저장된 객체
	 * @return 성공 : 1, 실패 : 0
	 */
	public int insertBoard(BoardVO vo);
	
	/**
	 * 게시글 번호를 받아서 해당 게시글을 삭제하는 메소드
	 * 
	 * @param no 삭제할 게시글 번호
	 * @return 성공 : 1, 실패 : 0
	 */
	public int deleteBoard(int no);
	
	/**
	 * 수정할 데이터가 저장된 vo를 전달받아 수정하는 메소드
	 * 
	 * @param vo 수정할 데이터가 저장된 vo객체
	 * @return 성공 : 1, 실패 : 0
	 */
	public int updateBoard(BoardVO vo);
	
	/**
	 * 게시글 번호를 받아서 해당 게시글 정보를 조회하는 메소드
	 * 
	 * @param no 조회할 게시글 번호
	 * @return 해당 게시글 자료가 있으면 해당 게시글 정보가 저장된 vo객체
	 *         없으면 null
	 */
	public BoardVO getBoard(int no);
	
	/**
	 * 전체 게시글을 조회해서 list에 담고 반환하는 메소드
	 * 
	 * @return 모든 vo객체를 갖고 있는 list
	 */
	public List<BoardVO> getAllBoardList();
	
	/**
	 * 게시글의 제목을 이용하여 게시글을 검색하는 메소드
	 * 
	 * @param title 검색할 게시글 제목
	 * @return 검색된 결과가 저장된 list객체
	 */
	public List<BoardVO> getBoardList(String title);
	
	/**
	 * 게시글 번호를 받아서 해당 게시글의 조회수를 증가시키는 메소드
	 * 
	 * @param no 조회수를 증가시킬 게시글 번호
	 * @return 성공 : 1, 실패 : 0
	 */
	public int setCountIncrement(int no);
}
