package basicIO;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {
	public static void main(String[] args) {
		//SWING의 파일 열기, 저장 창 띄우기
		JFileChooser ch = new JFileChooser();
		
		//선택할 파일의 확장자 설정
		FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word", "doc", "docx");
		FileNameExtensionFilter img = new FileNameExtensionFilter("Image 파일", new String[] {"png", "jpg", "jpeg"});
		FileNameExtensionFilter pdf = new FileNameExtensionFilter("PDF 파일", "pdf");
		FileNameExtensionFilter txt = new FileNameExtensionFilter("text 파일", "txt");
		
		//구성한 확장자 추가
		ch.addChoosableFileFilter(doc);
		ch.addChoosableFileFilter(img);
		ch.addChoosableFileFilter(pdf);
		ch.addChoosableFileFilter(txt);
		
		//구성한 확장자 목록 중에서 현재 선택된 상태가 될 확장자 지정 => 기본값 지정
		//이것을 설정하지 않으면 첫번째로 추가한 확장자가 기본적으로 선택
		ch.setFileFilter(txt);
		
		//확장자 목록에 '모든 파일' 표시 여부
		//기본값은 true
		//ch.setAcceptAllFileFilterUsed(false); //'모든 파일' 표시 X
		
		//Dialog창이 나타날 때 기본적으로 보여줄 기본 경로
		ch.setCurrentDirectory(new File("d:/d_other"));
		
		//Dialog창 나타내기(열기용, 저장용을 구분해서 나타낸다)
		//int open = ch.showOpenDialog(new Panel());
		int save = ch.showSaveDialog(new Panel());
		
		//열기 또는 저장버튼을 눌렀을 경우
		if(save == JFileChooser.APPROVE_OPTION) {
			//선택한 파일 정보 구하기
			File selectFile = ch.getSelectedFile();
			
			System.out.println("선택한 파일 : " + selectFile.getAbsolutePath());
		}
	}
}
