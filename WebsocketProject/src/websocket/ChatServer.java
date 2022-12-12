package websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ChatingServer") 
public class ChatServer {
	private static Set<Session> clients 
	= Collections.synchronizedSet(new HashSet<Session>());//여러클라이언트가 접속해도 문제되지않도록 동기화한것
	
	//클라이언트 접속시 실행
	@OnOpen
	public void onOpen(Session session) {
		clients.add(session);
		System.out.println("웹소켓 연결 : " + session.getId());
		
	}
	
	//메세지를 받으면 실행
	@OnMessage
	public void onMessage(String message,Session session) throws IOException {
		System.out.println("메세지 전송 : " + session.getId() + ":" + message);
		synchronized(clients) {
			for (Session client : clients) { //모든 클라이언트에 메세지 전달
				if (!client.equals(session)) {
					//단, 메세지를 보낸 클라이언트는 제외하고 전달
					client.getBasicRemote().sendText(message);
				}
			}
		}
	}
	
	//클라이언트와의 연결이 끊기면 실행
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("웹소켓 종료 : " + session.getId());
	}

	//에러 발생 시 실행
	@OnError 
	public void onError(Throwable e) {
		System.out.println("에러 발생!!!");
		e.printStackTrace();
	}



}
