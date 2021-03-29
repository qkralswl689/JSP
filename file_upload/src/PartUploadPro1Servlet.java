import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class PartUploadPro1Servlet
 */

@WebServlet("/partUploadPro1")
@MultipartConfig(
		fileSizeThreshold=0,
		location="D:\\lsh\\works\\upload"
		)
public class PartUploadPro1Servlet extends HttpServlet {
	private static final long serialVersionUID=1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PartUploadPro1Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String writer=request.getParameter("writer");
		Part part=request.getPart("partFile1");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		/*
		 * 참고) content-disposition 헤더(header) 정보
		 * : https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Content-Disposition
		 * 
		 * 일반적인 HTTP 응답에서 Content-Disposition 헤더는 컨텐츠가 브라우저에 inline 되어야 하는
		 * 웹페이지 자체이거나 웹페이지의 일부인지, 아니면 attachment로써 다운로드 되거나 로컬에 
		 * 저장될 용도록 쓰이는 것인지를 알려주는 헤더입니다. multipart/form-data 본문에서의 
		 * Content-Disposition 일반 헤더는 multipart의 하위파트에서 활용될 수 있는데, 
		 * 이 때 이 헤더는 multipart 본문 내의 필드에 대한 정보를 제공합니다. 
		 * multipart의 하위파트는 Content-Type 헤더에 정의된 boundary 구분자에 의해 구분되며, 
		 * Content-Disposition 헤더를 multipart 자체에 사용하는 것은 아무런 효과를 발휘하지 못합니다. 
		 * Content-Disposition 헤더는 광의의 MIME 맥락 속에서 정의되었는데, 그 정의에서 사용되는 파라미터 
		 * 중 일부인 form-data, name 그리고 filename만이 HTTP forms와 POST 요청에 적용될 수 있습니다. 
		 * 여기서 name과 filename은 필수적인 파라미터는 아닙니다.
		 */
		String contentDisposition=part.getHeader("content-disposition");
		String uploadFileName=getUploadFileName(contentDisposition);
		
		System.out.println("######## uploadFileName : "+ uploadFileName);
		System.out.println("######## uploadFileName : "+ uploadFileName.split("=\"")[1]);
		part.write(uploadFileName.split("=\"")[1]);
		out.println("작성자  " + writer + "님이 " + uploadFileName.split("=\"")[1] + " 파일을 업로드 하였습니다." );
	}

	private String getUploadFileName(String contentDisposition) {
		
		String uploadFileName=null;
		String[] contentSplitStr=contentDisposition.split(";");
		int lastPathSeparatorIndex=contentSplitStr[2].lastIndexOf("\\");
		int lastQutosIndex=contentSplitStr[2].lastIndexOf("\"");
		uploadFileName=contentSplitStr[2].substring(lastPathSeparatorIndex + 1, lastQutosIndex);
		return uploadFileName;
	}

}