package basicTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MultiChatServer {
	//접속한 클라이언트 정보를 저장할 Map
	private Map<String, Socket> client;
	
	public MultiChatServer() {
		//client을 동기화 처리가 되도록 생성
		client = Collections.synchronizedMap(new HashMap<>());
	}

	public static void main(String[] args) {
		new MultiChatServer().serverStart();
	}
	
	//서버의 시작
	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			
			System.out.println("서버 시작...");
			
			while(true) {
				socket = server.accept();

				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속");
				
				//스레드 영역==============================
				ServerReceive t = new ServerReceive(socket);
				t.start();
			}
		} catch (Exception e) {
		} finally {
			if(server != null) {
				try {
					server.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	//clientMap에 저장된 전체 사용자에게 메시지를 전송
	public void sendToAll(String msg) {
		//Map의 개수만큼 반복
		for (String name : client.keySet()) {
			try {
				//key에 맞는 socket을 이용하여 출력용 스트림 객체 구하기
				DataOutputStream dout = new DataOutputStream(client.get(name).getOutputStream());
				dout.writeUTF(msg);
			} catch (Exception e) {
			}
		}
	}
	
	//서버에서 클라이언트로 메시지를 전송하는 thread
	//inner class로 만드는 이유 => MultiChatServer의 전용스레드
	// ==> outer class의 필드를 사용가능
	class ServerReceive extends Thread {
		private Socket socket;
		private DataInputStream din;
		private DataOutputStream dout;
		
		public ServerReceive(Socket socket) {
			this.socket = socket;
			
			try {
				din = new DataInputStream(this.socket.getInputStream());
				dout = new DataOutputStream(this.socket.getOutputStream());
			} catch (Exception e) {
			}
		}
		
		@Override
		public void run() {
			String name = "";
			try {
				//클라이언트가 연결이 성공하면 첫번째로 대화명을 입력받아 보낸다
				//서버에서는 이 대화명을 받아서 중복체크
				while(true) {
					name = din.readUTF();
					if(client.containsKey(name)) {
						dout.writeUTF("대화명중복");
					} else {
						dout.writeUTF("OK");
						break;
					}
				}
				
				//이미 접속한 사람들에게 접속 알리기
				sendToAll("[" + name + "]님이 입장했습니다.");
				
				//대화명과 현재 접속한 클라이언트의 socket을 추가
				client.put(name, socket);
				
				System.out.println("현재 접속자 수 : " + client.size() + "명");
				
				//클라이언트가 보낸 메시지 받아서 전체에게 보내기
				while(din != null) {
					sendToAll(din.readUTF());
				}
			} catch (Exception e) {
			} finally {
				//클라이언트가 접속을 종료하면 finally 영역
				sendToAll("[" + name + "]님이 퇴장했습니다.");
				client.remove(name);
				
				System.out.println();
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 퇴장");
				System.out.println();
				System.out.println("현재 접속자 수 : " + client.size() + "명");
			}
		}
	}
}
