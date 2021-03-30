package example;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

public class MailSend {
	
    public static void main(String[] args) {
    	
    	// 네이버(naver)의 아이디/패쓰워드를 외부 인자를 통해서 입력합니다.
    	String id = args[0].trim();
    	String pw = args[1].trim();
    	
    	// 시스템 설정
        Properties p = System.getProperties();
        // SMTP 사용 여부 설정
        p.put("mail.smtp.starttls.enable", "true");
        // SMTP 서버 설정
        p.put("mail.smtp.host", "smtp.naver.com");
        // SMTP 인증 여부 설정
        p.put("mail.smtp.auth", "true");
        // STMP 포트(port) 설정
        p.put("mail.smtp.port", "587");
 
        // 네이버(naver) ID/PW 인증
        Authenticator auth = new MyMailAuthenticator(id, pw);
        // 메일 인증 세션
        Session session = Session.getDefaultInstance(p, auth);
        // 메일 메시지 객체
        MimeMessage msg = new MimeMessage(session);
 
        try {
        	// 메일 송부 날짜 설정
            msg.setSentDate(new Date());
            InternetAddress from = new InternetAddress();
            
            // 송신자 메일
            from = new InternetAddress("sender<alswl689@naver.com>");
            msg.setFrom(from);
 
            // 수신자 메일
            InternetAddress to = new InternetAddress("alswl689@naver.com");
            msg.setRecipient(Message.RecipientType.TO, to);
 
            // 메일 내용 및 인코딩 방식(encoding), MIME type(text/html) 등록
            // 메일 제목 설정
            msg.setSubject("메일 제목입니다.", "UTF-8");
            // 메일 내용 설정
            msg.setText("메일 내용입니다.", "UTF-8");
            // 메일 MIME type 설정
            msg.setHeader("content-Type", "text/html");
 
            // 메일 전송
            javax.mail.Transport.send(msg);
            
        } catch (AddressException e) {
        	System.err.println("AddressException : " + e);
            e.printStackTrace();
        } catch (MessagingException e){
        	System.err.println("MessagingException : " + e);
            e.printStackTrace();
        }
        
    }
    
}