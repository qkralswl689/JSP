package newlecture.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// "/*" :  모든 URL에 필터를 적용
@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)
			throws IOException, ServletException {
		
		// 요청시 실행 되는 필터
		//System.out.println("before filter");
		
		// 실행전 모든 서블릿에 인코딩 필터가 적용
		req.setCharacterEncoding("UTF-8");
		
		// 실행되면 다음 서블릿이나 필터가 실행된다 => 다음실행을 관활하기 위한것
		chain.doFilter(req, rep);
		
		// 필터 실행된 이후에 실행
		//System.out.println("after filter");

	}

}
