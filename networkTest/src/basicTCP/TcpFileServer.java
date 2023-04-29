package basicTCP;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	public static void main(String[] args) throws Exception {
		File f = new File("d:/d_other/연습용");
 		
		if(!f.exists()) {
			f.mkdirs();
		}
		
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("서버 연결 중...");
		
		Socket socket = server.accept();
		
		System.out.println("연결완료...");
		
		InputStream in = socket.getInputStream();
		DataInputStream din = new DataInputStream(in);
		
		String fileName = din.readUTF();
		
		File f2 = new File("d:/d_other/연습용/" + fileName);
		
		FileOutputStream fout = new FileOutputStream(f2);
		BufferedOutputStream bout = new BufferedOutputStream(fout);
		
		int data;
		
		while((data=din.read()) != -1) {
			bout.write(data);
		}
		
		bout.close();
		din.close();
		socket.close();
		server.close();
	}
}
