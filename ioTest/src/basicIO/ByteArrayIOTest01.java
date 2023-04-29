package basicIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class ByteArrayIOTest01 {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		ByteArrayInputStream input = null;    //입력용 스트림 객체 
		ByteArrayOutputStream output = null;    //출력용 스트림 객체
		
		try {
			input = new ByteArrayInputStream(inSrc);
			output = new ByteArrayOutputStream();
			
			int data; //읽어온 데이터 저장할 변수
			
			//read() => 더 이상 읽어올 자료가 없으면 -1 반환
			while( (data = input.read()) != -1 ) {
				//읽어온 데이터를 사용
				output.write(data); //데이터 출력
			}
			
			//출력된 스트림 값들을 배열로 변환해서 저장
			outSrc = output.toByteArray();
			
			//입출력 작업이 모두 끝나면 자원 반납
			input.close();
			output.close();
			
			System.out.println(data);
			
			System.out.println(" inSrc => " + Arrays.toString(inSrc));
			System.out.println("outSrc => " + Arrays.toString(outSrc));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
