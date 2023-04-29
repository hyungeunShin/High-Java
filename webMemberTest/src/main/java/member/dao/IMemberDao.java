package member.dao;

import java.util.List;

import member.vo.MemberVO;

public interface IMemberDao {
	public List<MemberVO> getAllMember();
	
	public int insertMember(MemberVO vo);
	
	public MemberVO getMember(String id);
	
	public int deleteMember(String id);
	
	public int updateMember(MemberVO vo);
	
	public int checkID(String id);
}
