package basicJDBC.vo;

/*
 DB테이블에 있는 컬럼을 기준으로 데이터를 객체화할 클래스
 
 DB테이블의 컬럼과 클래스의 필드를 맵핑
 */

public class MemberVO {
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_tel;
	private String mem_addr;
	
	//별도의 생성자를 만들때에는 기본생성자도 반드시 같이 만들어 준다
	public MemberVO() {
		
	}
	
	public MemberVO(String mem_id, String mem_pass, String mem_name, String mem_tel, String mem_addr) {
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
		this.mem_name = mem_name;
		this.mem_tel = mem_tel;
		this.mem_addr = mem_addr;
	}

	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}

	@Override
	public String toString() {
		return String.format("아이디: %s\t 비밀번호: %s\t 이름: %s\t 전화번호: %s\t 주소: %s\n", mem_id, mem_pass, mem_name, mem_tel, mem_addr);
	}
}