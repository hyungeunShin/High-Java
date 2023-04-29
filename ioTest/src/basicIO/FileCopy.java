package basicIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 d:/d_other 폴더에 있는 펭귄.jpg 파일을
 d:/d_other/연습용 폴더에 복사본_펭귄.jpg 파일로 복사
 */

public class FileCopy {
	public static void main(String[] args) {
		File f = new File("d:/d_other/펭귄.jpg");
		
		if (!f.exists()) {
			System.out.println(f.getName() + "이 존재하지 않습니다");
			return;
		}
		
		File targetDir = new File("d:/d_other/연습용");
		
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}

		File targetFile = new File(targetDir, "복사본_펭귄.jpg");

		FileInputStream fin = null;
		FileOutputStream fout = null;
		
		try {
			fin = new FileInputStream(f);
			fout = new FileOutputStream(targetFile);

			int data;
			
			System.out.println("복사시작");
			
			while ((data = fin.read()) != -1) {
				fout.write(data);
			}
			
			fout.flush();
			
			System.out.println("복사완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
