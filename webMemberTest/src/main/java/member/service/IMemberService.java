package member.service;

import java.util.List;

import member.vo.MemberVO;

public interface IMemberService {
	public List<MemberVO> getAllMember();
	
	public int insertMember(MemberVO vo);
	
	public MemberVO getMember(String id);
	
	public int deleteMember(String id);
	
	public int updateMember(MemberVO vo);
	
	public int checkID(String id);
}
