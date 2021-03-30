package example;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 메일 인증(아이디/패쓰워드) 클래스
 * 
 * @author javateam
 *
 */
public class MyMailAuthenticator extends Authenticator {
		 
    PasswordAuthentication account;
 
    // MyMailAuthenticator : PasswordAuthentication 객체 생성후 id,pw정보 받는다
    public MyMailAuthenticator(String id, String pw){
        account = new PasswordAuthentication(id, pw);
    }
 
    // Authentication 구현시 반드시 구현해 주어야 하는 메소드
    public PasswordAuthentication getPasswordAuthentication(){
        return account;
    }
    
}