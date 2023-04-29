package basicIO;

import java.io.FileOutputStream;

public class FileIOTest02 {
	public static void main(String[] args) {
		//바이트 기반의 출력용 스트림을 이용해서 파일로 출력
		
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/out.txt");
			
			for (char c = 'A'; c <= 'Z'; c++) {
				fout.write(c);
			}
			fout.flush(); //출력 버퍼에 남아 있는 자료를 강제로 출력
			System.out.println("작업완료");
			
			fout.close();
		} catch (Exception e) {
		}
	}
}
