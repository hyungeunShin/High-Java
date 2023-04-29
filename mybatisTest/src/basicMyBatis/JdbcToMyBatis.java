package basicMyBatis;

import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;

/*
 lprod 테이블에 새로운 데이터 추가하기
 lprod_gu와 lprod_nm 은 직접 입력
 lprod_id는 현재의 lprod_id중에서 제일 큰 값보다 1 크게

 입력받은 lprod_gu가 이미 있으면 다시 입력받아 처리
 
 jdbc5를 mybatis로 변경
 mapper파일명은 'jdbc-mapper.xml'으로
*/

public class JdbcToMyBatis {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		/*
		Reader rd = null;
		SqlSessionFactory sessionfactory = null;
		
		try {
			rd = Resources.getResourceAsReader("mybatis/config/mybatis-config.xml");
			
			sessionfactory = new SqlSessionFactoryBuilder().build(rd);
			
		} catch (Exception e) {
			System.out.println("mybatis 초기화 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rd != null) rd.close();
			} catch (Exception e2) {
			}
		}
		*/
		
		SqlSession session = null;
		
		try {
			//session = sessionfactory.openSession();
			session = MybatisSqlSessionFactory.getSqlSession();
			
			String gu = null;
			while(true) {
				System.out.print("분류코드 입력>> ");
				gu = s.nextLine();
				int cnt = session.selectOne("jdbc.getLprodCount", gu);
				
				if(cnt == 0) {
					break;
				} else {
					System.out.println("중복");
				}
			}
			
			System.out.print("상품분류명 입력>> ");
			String name = s.nextLine();
			
			int id = session.selectOne("jdbc.getNextId");
			
			lprodVO vo = new lprodVO();
			vo.setLprod_id(id);
			vo.setLprod_gu(gu);
			vo.setLprod_nm(name);
			
			int cnt = session.insert("jdbc.insertLprod", vo);
			
			if(cnt > 0) {
				System.out.println("등록");
			} else {
				System.out.println("실패");
			}
		} finally {
			session.commit();
			session.close();
			s.close();
		}
	}
}
