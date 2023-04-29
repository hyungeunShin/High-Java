package basicIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {
	public static void main(String[] args) {
		//바이트 기반의 파일 입력용 스트림으로 파일내용 읽기
		
		FileInputStream fin = null; //파일 입력용 스트림객체
		
		try {
			//읽어올 파일을 매개변수로 지정해서 FileInputStream객체 생성
			
			//1.
			//fin = new FileInputStream("d:/d_other/test.txt");
			
			//2.
			File file = new File("d:/d_other/test.txt");
			fin = new FileInputStream(file);
			
			int data;
			
			while((data = fin.read()) != -1) {
				//읽어온 데이터를 화면에 출력
				System.out.print((char)data);
			}
		} catch (Exception e) {
			System.out.println("입출력 오류");
		} finally {
			if(fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
