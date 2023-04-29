package basicIO;

import java.awt.Panel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileCopyWithDialog {
	public static void main(String[] args) {
		new FileCopyWithDialog().start();
	}

	public void start() {
		File f = ShowDialog("OPEN");

		if (f == null) {
			System.out.println("실패");
			return;
		}

		if (!f.exists()) {
			System.out.println(f.getName() + "이 존재하지 않습니다");
			return;
		}

		File targetFile = ShowDialog("SAVE");

		if (targetFile == null) {
			System.out.println("실패");
			return;
		}

		FileInputStream fin = null;
		FileOutputStream fout = null;
		try {
			fin = new FileInputStream(f);
			fout = new FileOutputStream(targetFile);

			int data;

			while ((data = fin.read()) != -1) {
				fout.write(data);
			}
			fout.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// 매개변수는 열기용일때는 "Open", 저장용일때는 "Save"
	public File ShowDialog(String openType) {
		JFileChooser ch = new JFileChooser();

		FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word", "doc", "docx");
		FileNameExtensionFilter img = new FileNameExtensionFilter("Image 파일", new String[] { "png", "jpg", "jpeg" });
		FileNameExtensionFilter pdf = new FileNameExtensionFilter("PDF 파일", "pdf");
		FileNameExtensionFilter txt = new FileNameExtensionFilter("text 파일", "txt");

		ch.addChoosableFileFilter(doc);
		ch.addChoosableFileFilter(img);
		ch.addChoosableFileFilter(pdf);
		ch.addChoosableFileFilter(txt);

		ch.setCurrentDirectory(new File("d:/d_other"));
		
		int result;

		if ("OPEN".equals(openType.toUpperCase())) {
			result = ch.showOpenDialog(new Panel());
		} else if ("SAVE".equals(openType.toUpperCase())) {
			result = ch.showSaveDialog(new Panel());
		} else {
			System.out.println("Open 또는 Save 지정");
			return null;
		}

		File selectFile = null;

		if (result == JFileChooser.APPROVE_OPTION) {
			selectFile = ch.getSelectedFile();
		}

		return selectFile;
	}
}
