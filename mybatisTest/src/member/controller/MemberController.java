package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import member.service.IMemberService;
import member.service.MemberService;
import member.vo.MemberVO;


public class MemberController {
	private Scanner scan;
	private IMemberService service;  
	
	public MemberController() {
		scan = new Scanner(System.in);
		service = MemberService.getInstance(); 
	}
	
	public static void main(String[] args) {
		new MemberController().startMember();
	}

	public void startMember() {
		while(true) {
			int choice = displayMenu();
			
			switch(choice) {
				case 1 : insertMember(); break;				// 추가
				case 2 : deleteMember(); break;				// 삭제
				case 3 : updateMember(); break;				// 수정
				case 4 : displayAllMember(); break;				// 전체 출력
				case 5 : updateMember2(); break;				// 수정2
				case 6 : updateMember3(); break;				// 수정3
				case 0 : 
					System.out.println("작업을 마칩니다...");
					return;
				default : System.out.println("작업 번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
			
		}
	}
	
	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		
		int count = service.getMemberCount(id);
		if(count==0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		int num;
		String updateField = null;
		String updateFieldTitle = null;
		do {
			System.out.println();
			System.out.println(" 수정할 항목을 선택하세요...");
			System.out.println(" 1.비밀번호    2.회원이름    3.전화번호    4.회원주소");
			System.out.println("-------------------------------------------------------");
			System.out.print("수정 항목 입력 >> ");
			num = scan.nextInt();
			
			switch(num) {
				case 1 : updateField = "mem_pass"; updateFieldTitle = "비밀번호";
					break;
				case 2 : updateField = "mem_name"; updateFieldTitle = "회원이름";
					break;
				case 3 : updateField = "mem_tel"; updateFieldTitle = "전화번호";
					break;
				case 4 : updateField = "mem_addr"; updateFieldTitle = "회원주소";
					break;
				default : System.out.println("수정 항목을 잘못 선택했습니다.");
						System.out.println("다시 선택하세요...");
			}
		
		}while(num < 1 || num > 4);
		
		scan.nextLine(); 
		System.out.println();
		System.out.print("새로운 " + updateFieldTitle + " >> ");
		String updateData = scan.nextLine();
		
		Map<String, String> paramMap = new HashMap<>();		
		paramMap.put("field", updateField);		
		paramMap.put("data", updateData);		
		paramMap.put("memId", id);				
		
		int cnt = service.updateMember2(paramMap);
		
		if(cnt>0) {
			System.out.println(id + " 회원 정보 수정 완료!!!");
		}else {
			System.out.println(id + " 회원 정보 수정 실패~~~");
		}
	}
	
	private void updateMember3() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		
		int count = service.getMemberCount(id);
		if(count==0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		Map<String, String> dataMap = new HashMap<>();
		
		System.out.println();
		scan.nextLine();  
		System.out.print("새로운 비밀번호 >> ");
		String newPass = scan.nextLine().trim();  
		if(!"".equals(newPass)) {
			dataMap.put("mem_pass", newPass);
		}
		
		System.out.print("새로운 회원이름 >> ");
		String newName = scan.nextLine().trim();
		if(!"".equals(newName)) {
			dataMap.put("mem_name", newName);
		}
		
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.nextLine().trim();
		if(!"".equals(newTel)) {
			dataMap.put("mem_tel", newTel);
		}
		
		System.out.print("새로운 회원주소 >> ");
		String newAddr = scan.nextLine().trim();
		if(!"".equals(newAddr)) {
			dataMap.put("mem_addr", newAddr);
		}
		
		if(dataMap.size() == 0) {
			System.out.println("수정할 내용이 하나도 없습니다.");
			return;
		}
		
		dataMap.put("memId", id);
		
		int cnt = service.updateMember3(dataMap);
		
		if(cnt>0) {
			System.out.println(id + " 회원 정보 수정 완료!!!");
		}else {
			System.out.println(id + " 회원 정보 수정 실패~~~");
		}
	}
	
	
	private void displayAllMember() {
		System.out.println();
		System.out.println("----------------------------------------------------");
		System.out.println(" ID     비밀번호     이 름       전화번호    주 소 ");
		System.out.println("----------------------------------------------------");
		
		List<MemberVO> memList = service.getAllMember();
		
		if(memList == null ||  memList.size()==0) {
			System.out.println("   등록된 회원 정보가 하나도 없습니다...");
		}else {
			for(MemberVO memVo : memList) {
				String memId = memVo.getMem_id();
				String memPass = memVo.getMem_pass();
				String memName = memVo.getMem_name();
				String memTel = memVo.getMem_tel();
				String memAddr = memVo.getMem_addr();
				
				System.out.println(memId + "\t" + memPass + "\t" + memName + "\t"
							+ memTel + "\t" + memAddr);
			}
		}
		System.out.println("----------------------------------------------------");
		System.out.println("출력 끝...");
	}
	
	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		
		int count = service.getMemberCount(id);
		
		if(count==0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		}
		
		System.out.println();
		System.out.print("새로운 비밀번호 >> ");
		String newPass = scan.next();
		
		System.out.print("새로운 회원이름 >> ");
		String newName = scan.next();
		
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.next();
		
		scan.nextLine();  // 버퍼 비우기
		System.out.print("새로운 회원주소 >> ");
		String newAddr = scan.nextLine();
		
		MemberVO memVo = new MemberVO();  
		memVo.setMem_id(id);
		memVo.setMem_pass(newPass);
		memVo.setMem_name(newName);
		memVo.setMem_tel(newTel);
		memVo.setMem_addr(newAddr);
		
		int cnt = service.updateMember(memVo);
		
		if(cnt>0) {
			System.out.println(id + " 회원 정보 수정 완료!!!");
		}else {
			System.out.println(id + " 회원 정보 수정 실패~~~");
		}
	}
	
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String id = scan.next();
		
		int cnt = service.deleteMember(id);
		
		if(cnt>0) {
			System.out.println("회원ID가 " + id + "인 회원 정보 삭제 성공!!!");
		}else {
			System.out.println(id + " 회원은 없는 회원이거나 삭제 작업에 실패했습니다...");
		}
	}
	
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");
		
		String id = null;
		int count = 0;
		do {
			System.out.print("회원ID >> ");
			id = scan.next();
			count = service.getMemberCount(id);
			if(count > 0) {
				System.out.println(id + "은(는) 이미 등록된 회원ID 입니다.");
				System.out.println("다른 회원ID를 입력하세요...");
			}
			
		}while(count>0);
		
		System.out.print("비밀번호 >> ");
		String pass = scan.next();
		
		System.out.print("회원이름 >> ");
		String name = scan.next();
		
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		
		scan.nextLine();  
		System.out.print("회원주소 >> ");
		String addr = scan.nextLine();
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		memVo.setMem_pass(pass);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);
		
		int cnt = service.insertMember(memVo);
		
		if(cnt>0) {
			System.out.println(id + " 회원 정보 추가 완료!!!");
		}else {
			System.out.println(id + " 회원 정보 추가 실패~~~");
		}
		
	}
	
	private int displayMenu() {
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("  1. 자 료 추 가");
		System.out.println("  2. 자 료 삭 제");
		System.out.println("  3. 자료수정 (전체항목수정)");
		System.out.println("  4. 전 체 자 료 출 력");
		System.out.println("  5. 자료수정2 (수정항목선택)");
		System.out.println("  6. 자료수정3 (입력항목만수정)");
		System.out.println("  0. 작 업 끝.");
		System.out.println("---------------------------------");
		System.out.print("작업 선택 >> ");
		
		return scan.nextInt();
	}
}
