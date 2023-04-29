package member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import member.vo.MemberVO;
import util.MybatisSqlSessionFactory;

public class MemberDao implements IMemberDao {
	private static MemberDao dao;

	private MemberDao() {
	}

	public static MemberDao getInstance() {
		if (dao == null)
			dao = new MemberDao();
		return dao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			cnt = session.insert("member.insertMember", memVo);
		} finally {
			try {
				session.commit();
				session.close();
			} catch (Exception e2) {
			}
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			cnt = session.delete("member.deleteMember", memId);
		} finally {
			try {
				session.commit();
				session.close();
			} catch (Exception e2) {
			}
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			cnt = session.update("member.updateMember", memVo);
		} finally {
			try {
				session.commit();
				session.close();
			} catch (Exception e2) {
			}
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		SqlSession session = null;
		List<MemberVO> list = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			list = session.selectList("member.getAllMember");
		} finally {
			try {
				session.close();
			} catch (Exception e2) {
			}
		}
		return list;
	}

	@Override
	public int getMemberCount(String memId) {
		SqlSession session = null;
		int count = 0;

		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			count = session.selectOne("member.getMemberCount",memId);
		} finally {
			try {
				session.close();
			} catch (Exception e2) {
			}
		}
		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MybatisSqlSessionFactory.getSqlSession();

			cnt = session.update("member.update2", paramMap);
		} finally {
			try {
				session.commit();
				session.close();
			} catch (Exception e2) {
			}
		}
		return cnt;
	}

	@Override
	public int updateMember3(Map<String, String> dataMap) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			cnt = session.update("member.update3", dataMap);
		} finally {
			try {
				session.commit();
				session.close();
			} catch (Exception e2) {
			}
		}
		return cnt;
	}
}
