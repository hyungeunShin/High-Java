package basicIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {
	public static void main(String[] args) {
		//문자기반의 Buffered 스트림 사용
		
		BufferedReader br = null;
		
		try {
			//이클립스에서 자바 프로그램이 실행되는 현재 위치는
			//프로그램이 속한 프로젝트 폴더 위치
			br = new BufferedReader(new FileReader("./src/basicIO/FileTest01.java"));
			
			String temp = "";
			
			//문자기반의 입력용 버퍼스트림은 데이터를 한줄 단위로 읽어오는 메소드 지원
			// => readLine() => 더 이상 읽어올 데이터가 없으면 null 반환
			for (int i = 1; (temp = br.readLine()) != null ; i++) {
				System.out.printf("%4d : %s\n", i, temp);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
