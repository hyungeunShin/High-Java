package basicIO;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class FileTest03 {
	public static void main(String[] args) {
		FileTest03 ft = new FileTest03();
		ft.Print(new File("C:\\Users\\PC-17\\Desktop"));
	}
	
	// 디렉토리 정보를 매개변수로 받아 해당 디렉토리에 있는 모든 파일 및 디렉토리를 출력
	void Print(File f) {
		if(!f.isDirectory()) {
			System.out.println("디렉토리(폴더)만 가능");
			return;
		}
		
		System.out.println("[" + f.getAbsolutePath() + "] 디렉토리 내용");
		
		//String[] list = f.list();
		File[] files = f.listFiles();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		
		for (int i = 0; i < files.length; i++) {
			String name = files[i].getName();
			String attr = "";  //파일의 속성(읽기,쓰기,히든,디렉토리 구분)
			String size = "";  //파일 크기
			
			if(files[i].isDirectory()) {
				attr = "<DIR>";
			} else {
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : "";
				attr += files[i].canWrite() ? "W" : "";
				attr += files[i].canExecute() ? "X" : "";
				attr += files[i].isHidden() ? "H" : "";
			}
			
			String date = df.format(new Date(files[i].lastModified()));
			
			System.out.printf("%s %5s %12s %s\n",date,attr,size,name);
		}
	}
}
