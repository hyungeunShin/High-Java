package basicJDBC.Controller;

import java.util.List;
import java.util.Scanner;
import basicJDBC.Service.BoardService;
import basicJDBC.Service.IBoardService;
import basicJDBC.VO.BoardVO;

public class BoardController {
	private Scanner s;
	private IBoardService service;
	
	public BoardController() {
		s = new Scanner(System.in);
		service = BoardService.getInstance();
	}
	
	public static void main(String[] args) {
		new BoardController().start();
	}
	
	public void start() {
		String title = null;
		int num = 0;
		while(true) {
			if(num != 3) {
				title = null;
			}
			num = Integer.parseInt(display(title)); 
			switch(num) {
			case 1:
				insertBoard();
				break;
			case 2:
				selectBoard();
				break;
			case 3:
				title = search();
				break;
			case 0:
				System.out.println("종료합니다");
				return;
			default:
				System.out.println("잘못 누르셨습니다");
				break;
			}
		}
	}

	public String display(String search) {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println(" No       제목         작성자        조회수");
		System.out.println("----------------------------------------------");
		
		List<BoardVO> list = null;
		
		if(search == null) {
			list = service.getAllBoardList();
		} else {
			list = service.getBoardList(search);
		}
		
		if(list == null || list.size() == 0) {
			System.out.println("게시글 없음");
		} else {
			for (BoardVO vo : list) {
				System.out.printf("  %d\t  %s\t%s\t      %d\n",vo.getBoard_no(), vo.getBoard_title(), vo.getBoard_writer(), vo.getBoard_cnt());
			}
		}
		System.out.println("----------------------------------------------");
		System.out.println("1. 글 작성   2. 게시글 보기   3.검색   0. 종료");
		System.out.println("----------------------------------------------");
		System.out.print("선택>> ");
		return s.nextLine();
	}
	
	//1. 새글작성
	private void insertBoard() {
		System.out.println("새 글 작성하기"); 
		System.out.println("--------------------------------------------");
		System.out.print("- 제 목  : ");
		String title = s.nextLine();
		System.out.print("- 작성자 : ");
		String writer = s.nextLine();
		System.out.print("- 내 용  : ");
		String contnet = s.nextLine();
		
		BoardVO vo = new BoardVO();
		vo.setBoard_title(title);
		vo.setBoard_writer(writer);
		vo.setBoard_content(contnet);
		int cnt = service.insertBoard(vo);
		
		if(cnt > 0) {
			System.out.println("새 글이 추가되었습니다");
		} else {
			System.out.println("실패");
		}
	}
	
	private void selectBoard() {
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int num = Integer.parseInt(s.nextLine());
		
		BoardVO vo = service.getBoard(num);
		
		if(vo == null) {
			System.out.println(num + "번의 게시글은 없습니다");
			return;
		}
		
		System.out.println();
		System.out.println(num + "번 글의 내용");
		System.out.println("-----------------------------------------------");
		System.out.println("- 제  목 : " + vo.getBoard_title());
		System.out.println("- 작성자 : " + vo.getBoard_writer());
		System.out.println("- 내  용 : " + vo.getBoard_content());
		System.out.println("- 작성일 : " + vo.getBoard_date());
		System.out.println("- 조회수 : " + vo.getBoard_cnt());
		System.out.println("-----------------------------------------------");
		
		System.out.println("-----------------------------------------------");
		System.out.println("   1. 수정   2. 삭제   3. 리스트로 가기   ");
		System.out.print("작업선택>> ");
		int choice = Integer.parseInt(s.nextLine());
		
		switch(choice) {
		case 1:
			update(num);
			break;
		case 2:
			delete(num);
			break;
		case 3:
			return;
		default:
			System.out.println("잘못 누르셨습니다");
			break;
		}
	}
	
	private void update(int no) {
		System.out.println(); 
		System.out.println("-----------------------------------------------");
		System.out.print("- 제 목  : ");
		String title = s.nextLine();
		System.out.print("- 내 용  : ");
		String contnet = s.nextLine();
		
		BoardVO vo = new BoardVO();
		
		vo.setBoard_title(title);
		vo.setBoard_content(contnet);
		vo.setBoard_no(no);
		
		int cnt = service.updateBoard(vo);
		
		if(cnt > 0) {
			System.out.println(no +  "번 글이 수정되었습니다");
		} else {
			System.out.println("수정 실패");
		}
	}
	
	private void delete(int no) {
		int cnt = service.deleteBoard(no);
		
		if(cnt > 0) {
			System.out.println(no + "번 글이 삭제되었습니다");
		} else {
			System.out.println("삭제 실패");
		}
	}
	
	private String search() {
		System.out.println("-----------------------------------------------");
		System.out.print("- 검색할 제목 입력 : "); 
		String title = s.nextLine();
		
		return title;
	}
}
