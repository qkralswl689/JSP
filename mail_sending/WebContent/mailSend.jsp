<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page import="example.*" %>
<%
	
	
	// 클라이언트 페이지에서 메일 전송에 사용하기 위해서 전송되어 온 파라미터 값들은 받는 부분
	// 자바 메일 보내기 폼에서 입력한 한글 정보가 깨지지 않도록 설정
	request.setCharacterEncoding("UTF-8");
	// 메일 송신자
	String sender = request.getParameter("sender");
	// 메일 수신자
	String receiver = request.getParameter("receiver");
	// 메일 제목
	String subject = request.getParameter("subject");
	// 메일 내용
	String content = request.getParameter("content");
	
	out.println("송신자 : " + sender + "<br>");
	out.println("수신자 : " + receiver + "<br>");
	out.println("제목 : " + subject + "<br>");
	out.println("내용 : " + content + "<br>");

	// 메일 송신자
	String id = "alswl689";
	String pw = "m5948312";

	// 시스템 설정 => 서버 정보를 Properties객체에 저장한다
	// https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#getProperties--
    Properties p = System.getProperties();
    // SMTP 사용 여부 설정
    p.put("mail.smtp.starttls.enable", "true");
    // SMTP 서버 설정 : smtp.naver.com 서버 명
    p.put("mail.smtp.host", "smtp.naver.com");
    // SMTP 인증 여부 설정
    p.put("mail.smtp.auth", "true");
    // STMP 포트(port) 설정
    p.put("mail.smtp.port", "587");

    // 네이버(naver) ID/PW 인증 -> 인증 정보 생성
    Authenticator auth = new MyMailAuthenticator(id, pw);
    // 메일 인증 세션
    Session mailSession = Session.getDefaultInstance(p, auth);
    // 메일 메시지 객체
    MimeMessage msg = new MimeMessage(mailSession);

    try {
    	// 메일 송부 날짜 설정
        msg.setSentDate(new Date());
        InternetAddress from = new InternetAddress();
                
        // 송신자 메일
        from = new InternetAddress(sender);
        msg.setFrom(from);

        // 수신자 메일
        InternetAddress to = new InternetAddress(receiver);
        msg.setRecipient(Message.RecipientType.TO, to);

        // 메일 내용 및 인코딩 방식(encoding), MIME type(text/html) 등록
        // 메일 제목 설정
        msg.setSubject(subject, "UTF-8");
        // 메일 내용 설정
        msg.setText(content, "UTF-8");
        // 메일 MIME type 설정
        msg.setHeader("content-Type", "text/html");

        // 메일 전송
        javax.mail.Transport.send(msg);
        
        out.println("메일 송부");
        
    } catch (AddressException e) {
    	System.err.println("AddressException : " + e);
        e.printStackTrace();
    } catch (MessagingException e){
    	System.err.println("MessagingException : " + e);
        e.printStackTrace();
    } // try
%>