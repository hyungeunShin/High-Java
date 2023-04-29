package member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import member.vo.MemberVO;
import util.MybatisSqlSessionFactory;

public class MemberDaoImpl implements IMemberDao {
	private static MemberDaoImpl dao;
	
	private MemberDaoImpl() {
		
	}
	
	public static MemberDaoImpl getInstance() {
		if(dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}
	
	@Override
	public List<MemberVO> getAllMember() {
		SqlSession session = null;
		List<MemberVO> list = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			list = session.selectList("member.getAllMember");
		} finally {
			session.close();
		}
		
		return list;
	}

	@Override
	public int insertMember(MemberVO vo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("member.insertMember", vo);
		} finally {
			session.commit();
			session.close();
		}
		
		return cnt;
	}

	@Override
	public MemberVO getMember(String id) {
		SqlSession session = null;
		MemberVO vo = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			vo = session.selectOne("member.getMember", id);
		} finally {
			session.close();
		}
		
		return vo;
	}

	@Override
	public int deleteMember(String id) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.delete("member.deleteMember", id);
		} finally {
			session.commit();
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO vo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("member.updateMember", vo);
		} finally {
			session.commit();
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int checkID(String id) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.selectOne("member.checkID", id);
		} finally {
		}
		
		return cnt;
	}
}
