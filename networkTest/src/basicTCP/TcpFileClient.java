package basicTCP;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpFileClient {
	public static void main(String[] args) throws Exception {
		
		File f = new File("d:/d_other/펭귄.jpg");
		
		if(!f.exists()) {
			return;
		}
		
		Socket socket = new Socket("localhost", 7777);
		
		System.out.println("서버연결완료...");
		
		OutputStream out = socket.getOutputStream();
		DataOutputStream dout = new DataOutputStream(out);
		
		dout.writeUTF("펭귄.jpg");
		
		FileInputStream fin = new FileInputStream(f);
		BufferedInputStream bin = new BufferedInputStream(fin);
		
		int data;
		
		while((data = bin.read()) != -1) {
			dout.write(data);
		}
		
		bin.close();
		dout.close();
		socket.close();
	}
}
