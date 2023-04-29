package member.service;

import java.util.List;

import member.dao.MemberDaoImpl;
import member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private MemberDaoImpl dao;
	private static MemberServiceImpl service;
	
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if(service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public int insertMember(MemberVO vo) {
		return dao.insertMember(vo);
	}

	@Override
	public MemberVO getMember(String id) {
		return dao.getMember(id);
	}

	@Override
	public int deleteMember(String id) {
		return dao.deleteMember(id);
	}

	@Override
	public int updateMember(MemberVO vo) {
		return dao.updateMember(vo);
	}

	@Override
	public int checkID(String id) {
		return dao.checkID(id);
	}
}
