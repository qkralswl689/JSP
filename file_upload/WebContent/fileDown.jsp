<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.*" %>
<%@ page import="util.UploadFileUtil" %>

<%
	String fileName=request.getParameter("file_name");

	String savePath="upload";
	ServletContext context=getServletContext();
	String sDownloadPath=context.getRealPath(savePath);
	// 경로가져오기 , window : \두번써야한다 => \\
	String sFilePath=sDownloadPath + "\\" + fileName;
	byte b[]=new byte[4096];
	FileInputStream in=new FileInputStream(sFilePath);
	String sMimeType=getServletContext().getMimeType(sFilePath);
	System.out.println("MIME Type : " + sMimeType);

	if (sMimeType == null)
		sMimeType="application/octet-stream";

	response.setContentType(sMimeType);
	String agent=request.getHeader("User-Agent");
	
	// 브라우저 체크
	boolean ieBrowser=(agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);

	if (ieBrowser) { // MS IE(Internet Explorer)
		fileName=URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
	} else { // 기타 브라우저
		fileName=new String(fileName.getBytes("UTF-8"), "iso-8859-1");
	}

	// 응답 헤더 설정
	response.setHeader("Content-Disposition", "attachment; filename= " + fileName);

	ServletOutputStream out2=response.getOutputStream();
	int numRead;

	while ((numRead=in.read(b, 0, b.length)) != -1) {
		out2.write(b, 0, numRead);
	}

	// 자원 반납 처리(flush/close) : 이 부분을 반드시 처리할 것 : 예외 방지 
	// Java.lang.IllegalStateException: 
	// getOutputStream() has already been called for this response
	// (이 응답을 위해 getOutputStream()이 이미 호출되었습니다)
		
	out2.flush();
	out2.close();
	in.close();
%>
