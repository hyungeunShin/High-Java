package basicIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataIOTest {
	public static void main(String[] args) {
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/test.dat");
			
			//자료형 단위로 출력할 보조스트림 객체 생성
			DataOutputStream dout = new DataOutputStream(fout);
			
			dout.writeInt(2);          //정수형으로 데이터 출력
			dout.writeFloat(123.45f);  //실수형 
			dout.writeBoolean(false);  //논리형
			dout.writeUTF("ABCD");     //문자열
			
			System.out.println("출력 완료");
			System.out.println();
			
			dout.close();
			
			//================================================================
			
			//출력한 자료 읽어오기
			DataInputStream din = new DataInputStream(new FileInputStream("d:/d_other/test.dat"));
			
			//DataInputStream으로 자료를 읽어올 때는 출력할 때와 순서가 동일 해야함
			System.out.println(din.readInt());
			System.out.println(din.readFloat());
			System.out.println(din.readBoolean());
			System.out.println(din.readUTF());
			
			System.out.println();
			System.out.println("읽어오기 완료");
			System.out.println();
			
			din.close();
		} catch (Exception e) {
		}
	}
}
