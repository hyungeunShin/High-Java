package basicMyBatis;

import java.io.Reader;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//mybatis를 이용하여 DB자료를 처리하는 순서 및 방법
public class lprodMyBatisTest {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		//1. mybatis의 환경 설정 파일을 읽어와서 실행한다 (mybatis-config.xml)
		Reader rd = null;
		SqlSessionFactory sessionfactory = null;
		
		try {
			//1-1. 환경설정 파일을 읽어 올 스트림 객체를 생성
			//     이때 읽어올 환경설정 파일을 지정해준다
			rd = Resources.getResourceAsReader("mybatis/config/mybatis-config.xml");
			
			//1-2. 환경설정파일을 읽어와 환경 설정을 완성한 후 SQL문을 호출해서 실행할 수 있는 
			//     객체를 생성하는 SqlSessionFactory객체를 생성
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
		
		//----------------------------------------------------------------------------------------------------------------
		//2. mapper에 등록된 SQL문 중에서 실행할 SQL문을 호출해서 원하는 작업 수행
		
		//SqlSession : 실제 SQL문을 읽어와 처리하고 결과를 얻어오는 객체
		SqlSession session = null;   
		
		/*
		//2-1. insert 작업
		System.out.println("insert 작업");
		
		System.out.print("lprod_id 입력>> ");
		int id = Integer.parseInt(s.nextLine());
		
		System.out.print("lprod_gu 입력>> ");
		String gu = s.nextLine();
		
		System.out.print("lprod_nm 입력>> ");
		String nm = s.nextLine();
		
		//입력한 데이터를 VO에 저장
		lprodVO vo = new lprodVO();
		vo.setLprod_id(id);
		vo.setLprod_gu(gu);
		vo.setLprod_nm(nm);
		
		try {
			// SqlSessionFactory객체를 이용하여 SqlSession객체를 구한다 => openSession() 메소드 이용
			// SqlSessionFactory객체.openSession(논리값)
			// => 논리값이 true이면 'AutoCommit'이 활성화
			// => 논리값이 false이면 'AutoCommit'이 비활성화 => 기본값
			session = sessionfactory.openSession();
			
			// SqlSession객체 변수를 이용하여 처리할 SQL문을 호출해서 실행
			// 형식) session.insert("namespace속성값.id속성값", 파라미터클래스)
			//       => 반환값 : 작업에 성공한 레코드 수
			int cnt = session.insert("lprod.insertLprod", vo);
			
			if(cnt>0) {
				System.out.println("등록성공");
			} else {
				System.out.println("등록 실패");
			}
		} finally {
			// SqlSession을 생성할 때 AutoCommit이 비활성화된 상태일 때는 commit을 직접 실행해야 한다
			session.commit();
			
			// 작업이 완료되면 SqlSession객체를 닫아준다
			session.close();
			s.close();
		}
		*/
		
		/*
		//2-2. update
		System.out.println("update 작업");
		
		System.out.print("lprod_gu 입력>> ");
		String gu = s.nextLine();
		
		System.out.print("lprod_id 입력>> ");
		int id = Integer.parseInt(s.nextLine());
		
		System.out.print("lprod_nm 입력>> ");
		String nm = s.nextLine();
		
		//입력한 데이터를 VO에 저장
		lprodVO vo = new lprodVO();
		vo.setLprod_gu(gu);
		vo.setLprod_id(id);
		vo.setLprod_nm(nm);
		
		try {
			session = sessionfactory.openSession();
			//반환값 => 작업에 성공한 레코드 수
			int cnt = session.update("lprod.updateLprod", vo);
			
			if(cnt > 0) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
		} finally {
			session.commit();
			session.close();
		}
		*/
		
		/*
		//2-3. delete
		System.out.println("delete 작업");
		
		System.out.print("lprod_gu 입력>> ");
		String gu = s.nextLine();
		
		try {
			session = sessionfactory.openSession();
			int cnt = session.delete("lprod.deleteLprod", gu);
			
			if(cnt > 0) {
				System.out.println("삭제성공");
			} else {
				System.out.println("삭제실패");
			}
		} finally {
			session.commit();
			session.close();
		}
		*/
		
		
		//2-4. select
		
		try {
			session = sessionfactory.openSession();
			
			// 조회결과가 여러개일 경우 selectList()사용
			// => 여러 레코드를 resultType에 맞게 list에 추가하는 작업을 자동으로 수행
			// 반환값 : resultType에 지정한 객체가 저장된 List객체
			
			List<lprodVO> list = session.selectList("lprod.getAllLprod");
			
			for (lprodVO vo : list) {
				System.out.println(vo);
			}
			
		} finally {
			session.close();
		}
		
		//2-5. select
		System.out.print("lprod_gu 입력>> ");
		String gu = s.nextLine();
		
		try {
			session = sessionfactory.openSession();
			
			//select문의 처리결과가 1개일 경우에는 selectOne()사용
			//session.selectOne("namespace.id",파라미터)
			//resultType에 설정한 자료형으로 반환, 없으면 null반환
			
			lprodVO vo = session.selectOne("lprod.getLprod", gu);
			
			if(vo == null) {
				System.out.println("없음");
			} else {
				System.out.println(vo);
			}
		} finally {
			session.close();
		}
		
		s.close();
	}
}
