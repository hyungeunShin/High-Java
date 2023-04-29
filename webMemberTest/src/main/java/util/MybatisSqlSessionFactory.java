package util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * mybatis의 환경 설정 파일을 읽어와서 실행하고
 * SqlSession객체를 반환하는 메소드
 *  
 * @author PC-17
 *
 */

public class MybatisSqlSessionFactory {
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		Reader rd = null;
		
		try {
			rd = Resources.getResourceAsReader("mybatis/config/mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
		} catch (Exception e) {
			System.out.println("mybatis 초기화 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rd != null) rd.close();
			} catch (Exception e2) {
			}
		}
	}
	
	/**
	 * SqlSessionFactory객체를 이용하여 SQL문을 처리할 SqlSession객체를 반환
	 * 
	 * @return SqlSession객체
	 */
	public static SqlSession getSqlSession() {
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
}
