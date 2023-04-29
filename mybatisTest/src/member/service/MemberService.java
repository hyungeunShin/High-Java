package member.service;

import java.util.List;
import java.util.Map;

import member.dao.IMemberDao;
import member.dao.MemberDao;
import member.vo.MemberVO;


public class MemberService implements IMemberService {
	// 일을 시킬 DAO객체 변수 선언
		private IMemberDao dao;		
		
		// 1번
		private static MemberService service;
		
		
		// 2번
		// 생성자
		private MemberService() {
			//dao = new MemberDaoImpl();  // DAO객체 생성
			dao = MemberDao.getInstance();  // DAO객체 생성
		}
		
		// 3번
		public static MemberService getInstance() {
			if(service==null) service = new MemberService();
			return service;
		}
			
		@Override
		public int insertMember(MemberVO memVo) {
			return dao.insertMember(memVo);
		}

		@Override
		public int deleteMember(String memId) {
			return dao.deleteMember(memId);
		}

		@Override
		public int updateMember(MemberVO memVo) {
			return dao.updateMember(memVo);
		}

		@Override
		public List<MemberVO> getAllMember() {
			return dao.getAllMember();
		}

		@Override
		public int getMemberCount(String memId) {
			return dao.getMemberCount(memId);
		}

		@Override
		public int updateMember3(Map<String, String> dataMap) {
			return dao.updateMember3(dataMap);
		}

		@Override
		public int updateMember2(Map<String, String> paramMap) {
			return dao.updateMember2(paramMap);
		}
}
