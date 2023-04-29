package basicIO;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {
	public static void main(String[] args) {
		FileOutputStream fout = null;
		BufferedOutputStream bout = null;
		
		try {
			fout = new FileOutputStream("d:/d_other/bufferTest.txt");
			
			//버퍼의 크기가 5인 버퍼스트림 객체 생성
			bout = new BufferedOutputStream(fout, 5);
			
			for (char ch = '1'; ch <= '9'; ch++) {
				bout.write(ch);
				//12345출력
				
				//1. flush()가 없고 bout.close()를 하지 않으면
				//6789 => 크기가 5가 안되서 가지고만 있고 출력X
			}
			
			//3. 닫기만 해도 되지만 써주는걸 추천
			bout.flush();
			
			System.out.println("끝");
		} catch (Exception e) {
		} finally {
//			if(fout != null) try {fout.close();} catch(Exception e) {}
			
			//보조 스트림을 닫으면 보조스트림에서 사용한 기반이 되는 스트림도 같이 닫힘
			//2. close() 안에 flush()기능이 있음 => 6789도 출력이 됨
			if(bout != null) {
				try {
					bout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
