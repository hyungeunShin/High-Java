package basicIO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyWithBuffer {
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
		
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		
		try {
			fin = new FileInputStream(f);
			bin = new BufferedInputStream(fin);
			
			fout = new FileOutputStream(targetFile);
			bout = new BufferedOutputStream(fout);
			
			int data;
			
			System.out.println("복사시작");
			
			while ((data = bin.read()) != -1) {
				bout.write(data);
			}
			
			bout.flush();
			
			System.out.println("복사완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bin != null) {
				try {
					bin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (bout != null) {
				try {
					bout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
