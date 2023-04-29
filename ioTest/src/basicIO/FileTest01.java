package basicIO;

import java.io.File;

/*
 * 1. new File(String 파일 또는 경로)
	  => 디렉토리와 디렉토리 사이 또는 디렉토르이와 파일명 사이의 구분자는 '/'를 사용하거나 '\'사용
		
   2. new File(File parent, String child)
	  => 'parent' 디렉토리 안에 있는 'child'파일을 갖는다
		
   3. new File(String parent, String child)
 */

public class FileTest01 {
	public static void main(String[] args) {
		//File f1 = new File("D:/D_Other/test.txt");
		File f1 = new File("D:\\D_Other\\test.txt");
		System.out.println("파일명 : " + f1.getName());
		System.out.println("디렉토리? " + f1.isDirectory());
		System.out.println("파일? " + f1.isFile());
		System.out.println();
		
		File f2 = new File("d:/d_other");
		System.out.println("파일명 : " + f2.getName());
		System.out.println("디렉토리? " + f2.isDirectory());
		System.out.println("파일? " + f2.isFile());
		System.out.println();
		
		
		File f3 = new File(f2, "test.txt");
		System.out.println("파일명 : " + f3.getName());
		System.out.println("디렉토리? " + f3.isDirectory());
		System.out.println("파일? " + f3.isFile());
		System.out.println();
		
		
		File f4 = new File("d:/d_other", "test.txt");
		System.out.println("파일명 : " + f4.getName());
		System.out.println("디렉토리? " + f4.isDirectory());
		System.out.println("파일? " + f4.isFile());
		System.out.println();
		
		/*
		 * 디렉토리(폴더) 만들기
		 * - mkdir()  ==> File객체의 경로 중 마지막 위치의 디렉토리를 만든다
		 *            ==> 중간 경로가 모두 만들어져 있어야 마지막 위치의 경로를 만들 수 있다 
		 *            ==> 만들면 true, 실패시 false
		 *           
		 * - mkdirs() ==> 중간 부분의 경로가 없으면 중간 부분의 경로도 같이 생성         
		 */
		
		File f5 = new File("d:/d_other/mkdir() 연습");
		System.out.println(f5.getName() + "의 존재 여부 : " + f5.exists());
		if(!f5.exists()) {
			if(f5.mkdir()) {
				System.out.println(f5.getName() + " 만들기 성공");
			} else {
				System.out.println(f5.getName() + " 만들기 실패");
			}
		}
		System.out.println();
		
		File f6 = new File("d:/d_other/test/java/src");
		if(f6.mkdirs()) {
			System.out.println(f6.getName() + " 만들기 성공");
		} else {
			System.out.println(f6.getName() + " 만들기 실패");
		}
		System.out.println();
		
		
	}
}
