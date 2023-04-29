package basicCollection;

import java.util.Stack;

public class StackTest {
	public static void main(String[] args) {
		Browser b = new Browser();
		b.history();
		b.goURL("네이버");
		
		b.history();
		b.goURL("구글");
		
		b.goURL("다음");
		b.goURL("네이트");
		b.history();
		
		System.out.println("뒤로가기 후");
		b.goBack();
		b.history();
		
		System.out.println("뒤로가기 후");
		b.goBack();
		b.history();
		
		System.out.println("앞으로가기 후");
		b.goForward();
		b.history();
		
		System.out.println("새로운 사이트 접속");
		b.goURL("야후");
		b.history();
	}
}

//웹 브라우저의 앞으로가기, 뒤로가기 기능 구현(스택 활용)
class Browser {
	private Stack<String> back;    // 이전 방문내역이 저장될 스택(주소값)
	private Stack<String> forward; // 다음 방문내역이 저장될 스택(주소값)
	private String currentURL;     // 현재 페이지가 저장될 변수
	
	public Browser() {
		back = new Stack<>();
		forward = new Stack<>();
		currentURL = "";
	}
	
	//사이트를 방문하는 메소드 => 매개변수에는 방문할 URL이 저장된다
	public void goURL(String url) {
		System.out.println(url + " 사이트로 이동");
		
		if(currentURL != null && !"".equals(currentURL)) {  //현재 페이지가 있으면
			back.push(currentURL);                          //현재 페이지를 back스택에 저장
		}
		currentURL = url;                                   //현재 페이지를 이동할 페이지로 변경
		forward.clear();                                    //forward 스택 비우기 
	}
	
	//뒤로가기 메소드
	public void goBack() {
		//isEmpty() => 컬렉션이 비어있는지 여부를 검사
		if(!back.isEmpty()) {                               //back스택이 비어있지 않으면
			forward.push(currentURL);                       //현재 페이지를 forward스택에 추가
			currentURL = back.pop();                        //현재 페이지를 back에서 꺼낸 주소로 초기화
		}
	}
	
	//앞으로가기 메소드
	public void goForward() {
		if(!forward.isEmpty()) {                            //forward스택이 비어있지 않으면 
			back.push(currentURL);                          //현재 페이지를 back스택에 추가
			currentURL = forward.pop();                     //현재 페이지를 forward에서 꺼낸 주소로 초기화
		}
	}
	
	//방문 기록 확인
	public void history() {
		System.out.println();
		System.out.println("   방 문 기 록   ");
		System.out.println("-----------------");
		System.out.println("back      => " + back);
		System.out.println("현재      => " + currentURL);
		System.out.println("forward   => " + forward);
		System.out.println();
	}
}
