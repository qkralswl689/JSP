package com.javateam.board_project.board.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.board_project.board.util.BoardFileUtil;
import com.javateam.board_project.controller.CommandAction;

public class BoardDownloadFileAction implements CommandAction {
	
	/**
	 * 브라우저 점검 
	 * 
	 * @param request
	 * @return
	 */
	private String getBrowser(HttpServletRequest request) {
		
		  String header = request.getHeader("User-Agent");

		  if (header.contains("MSIE")) {
			  return "MSIE";
	  	  } else if(header.contains("Trident")) {
	  		  return "MSIE11";
		  } else if(header.contains("Chrome")) {
			  return "Chrome";
		  } else if(header.contains("Opera")) {
			  return "Opera";
		  }

		  return "Firefox";
	}

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("-------------------------------------");
		System.out.println("첨부 업로드 파일 다운로드");
		
		// 실제 업로드된 첨부  파일명 ex) 제주도_2021040113540045813cdd.jpg
		String uploadedFileName = request.getParameter("uploadFile");
		// int boardNum = new Integer(request.getParameter("boardNum"));
		
		System.out.println("실제 첨부 파일명 : " + uploadedFileName);
		System.out.println("원래 첨부 파일명 : " + BoardFileUtil.getOriginalFileName(uploadedFileName));
		
		// 원래 첨부 파일명 ex) 제주도.jpg
		String originalFileName = BoardFileUtil.getOriginalFileName(uploadedFileName);
		
		// 파일 다운로드시 원래 파일명으로 복원
		System.out.println("파일 다운로드 서비스");
		
		String filePath = request.getServletContext().getRealPath("/upload/");
		// 저장 폴더
		String sourcePath = filePath + uploadedFileName;
		File file = new File(sourcePath);
		response.setContentLength((int)file.length());
		
		System.out.println("sourcePath : " + sourcePath);
		
		// 다운로드시 한글 파일 깨짐 현상 방지
		String mimeType= URLConnection.guessContentTypeFromName(uploadedFileName);
        String header = getBrowser(request);
        String docName = "";

        if (header.contains("MSIE")) {
        	docName = URLEncoder.encode(originalFileName,"UTF-8").replaceAll("\\+", "%20");
	        response.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
        } else if (header.contains("Firefox")) {
	        docName = new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
        } else if (header.contains("Opera")) {
	        docName = new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
        } else if (header.contains("Chrome")) {
        	docName = new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
        }

        // 응답 헤더 설정
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        
        response.setHeader("Content-Disposition","attachment; filename=" 
        				  + java.net.URLEncoder.encode(originalFileName,"UTF-8") + ";");
        
        response.setContentType(mimeType);
		
		// 추가
        ServletOutputStream rout = null;
        BufferedInputStream in = null;
        BufferedOutputStream bout = null;
        
		try {
			System.out.println("file Path : " + filePath);
	        System.out.println("source Path : " + sourcePath);
			
	        System.out.println("파일 크기 : "+(int)file.length());
	        
	        // 다운로드시 파일 이름 원래 파일명으로 변경
	        byte [] buffer = new byte[1024*15];
	        rout = response.getOutputStream();
	        in = new BufferedInputStream(new FileInputStream(file));
	        
	        bout = new BufferedOutputStream(rout);	        
	        int n = 0;
	        
	        while((n=in.read(buffer, 0, 1024*15)) != -1) {
	              bout.write(buffer, 0, n);
	              bout.flush();
	        }
	        
        } catch (Exception e) {
        	System.out.println("예외 처리");
        	e.printStackTrace();
        } finally {
        	rout.close();
        	in.close();
        	bout.close();
        }
		
		return "/board/download.jsp";
	} //

}