package action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.UploadFileUtil;

/**
 * Servlet implementation class FileDownFilePolicyAction
 */
@WebServlet({ "/FileDownFilePolicyAction", "/fileDownFilePolicy.do" })
public class FileDownFilePolicyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charsert=UTF-8");
		PrintWriter out = response.getWriter();
		
		String fileName=request.getParameter("file_name");

		// 파일 정책에 따라 변환된 파일명을 업로드시 원래 파일명으로 변경
		String originalFileName = UploadFileUtil.getOriginalFileName(fileName);
				
		out.println("원 파일명 : "+originalFileName+"<br>");

		String savePath="upload";
		ServletContext context=getServletContext();
		String sDownloadPath=context.getRealPath(savePath);
		String sFilePath=sDownloadPath + "\\" + fileName;
		byte b[]=new byte[4096];
		FileInputStream in=new FileInputStream(sFilePath);
		String sMimeType=getServletContext().getMimeType(sFilePath);
		System.out.println("MIME Type : " + sMimeType);

		if (sMimeType == null)
			sMimeType="application/octet-stream";

		try {
			response.setContentType(sMimeType);
			String agent=request.getHeader("User-Agent");
			
			// 브라우저 체크
			boolean ieBrowser=(agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);
		
			if (ieBrowser) { // MS IE(Internet Explorer)
				fileName=URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
			} else { // 기타 브라우저
				fileName=new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			}
			
		} catch(Exception e) {
			System.out.println("step : "+e);		
		}
		
		ServletOutputStream out2=null;
		
		try {
			// 응답 헤더 설정
			// response.setHeader("Content-Disposition", "attachment; filename= " + fileName);
			response.setHeader("Content-Disposition", "attachment; filename= " + originalFileName);
			out2=response.getOutputStream();
			
			int numRead;
			
			while ((numRead=in.read(b, 0, b.length)) != -1) {
				out2.write(b, 0, numRead);
			}
			
		} catch (Exception e) {	
			// System.out.println(e.getMessage());
		} finally {			
			if (out2!=null) out2.close();
			in.close();
		}
	
	} //

}
