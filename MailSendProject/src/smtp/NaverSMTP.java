package smtp;


import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class NaverSMTP {
	private final Properties serverInfo; //서버정보
	private final Authenticator auth; //인증정보

	public NaverSMTP() {
		//네이버 SMTP 서버접속정보
		serverInfo = new Properties();
		serverInfo.put("mail.smtp.host", "smtp.naver.com");
		serverInfo.put("mail.smtp.port", "465"); 
		serverInfo.put("mail.smtp.starttls.enable", "true");
		serverInfo.put("mail.smtp.auth", "true");
		serverInfo.put("mail.smtp.debug", "true");
		serverInfo.put("mail.smtp.socketFactory.port", "465");
		serverInfo.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		serverInfo.put("mail.smtp.socketFactory.fallback", "false");
		
		//사용자 인증 정보
		auth = new Authenticator() {

			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("네이버아이디","네이버 비밀번호");
			}
			
		};
	}
	
	//주어진 메일 내용을 네이버 SMTP 서버를 통해 전송합니다.
	public void emailSending(Map<String, String> mailInfo)
			throws MessagingException {
		//1. 세션생성 
		Session session = Session.getInstance(serverInfo, auth); //세션을 만들때 인스턴스 변수로 만들어둔 서버정보와 인증 정보를 사용한다
		session.setDebug(true);
		
		//2. 메세지 작성     매개변수로 받은 Map컬렉션에서 메일내용을 뽑아 메세지를 작성한당
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(mailInfo.get("from"))); //보내는사람
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailInfo.get("to"))); //받는사람
		msg.setSubject(mailInfo.get("subject"));  //제목
		msg.setContent(mailInfo.get("content"), mailInfo.get("format")); //내용
		
		//3. 전송
		Transport.send(msg);
		
	}
}
