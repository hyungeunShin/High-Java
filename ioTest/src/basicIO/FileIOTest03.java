package basicIO;

import java.io.FileReader;
import java.io.IOException;

public class FileIOTest03 {
	public static void main(String[] args) {
		FileReader fr = null;
		
		try {
			fr = new FileReader("d:/d_other/test.txt");
			
			int c;
			
			while((c=fr.read()) != -1) {
				System.out.print((char) c);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
