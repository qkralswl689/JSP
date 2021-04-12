package com.javateam.board_project.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.board_project.board.domain.BoardVO;
import com.javateam.board_project.board.service.BoardService;
import com.javateam.board_project.board.service.BoardServiceImpl;
import com.javateam.board_project.board.util.BoardFileReNamePolicy;
import com.javateam.board_project.board.util.BoardFileUtil;
import com.javateam.board_project.controller.CommandAction;
import com.oreilly.servlet.MultipartRequest;
// import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteProcAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("글쓰기 처리");
		
		BoardVO boardVO = new BoardVO();
		MultipartRequest multi = null;
		String returnPath = ""; // 이동 페이지
		
		// 인자 인쇄
		String realFolder = ""; // 업로드 폴더
		String saveFolder = "upload";
		int fileSize = 5*1024*1024;
		realFolder = request.getServletContext().getRealPath(saveFolder);
		
		// 업로드 파일 중복 방지 : 고유 파일 정책 으로 변경
		// file format) 파일 형식 : 원본 파일명 + "_" + 날짜 포맷 + 해쉬코드 + 확장자
		multi = new MultipartRequest(request,
								realFolder,
								fileSize,
								"UTF-8",
								new BoardFileReNamePolicy());
								 // new DefaultFileRenamePolicy());
		
		boardVO.setBoardName(multi.getParameter("boardName"));
		boardVO.setBoardPass(multi.getParameter("boardPass"));
		boardVO.setBoardSubject(multi.getParameter("boardSubject"));
		boardVO.setBoardContent(multi.getParameter("boardContent"));
		
		if (multi.getOriginalFileName("boardFile") != null) {
			
			System.out.println("첨부 원본 파일명  :  " + multi.getOriginalFileName("boardFile"));
			System.out.println("실제 저장 파일명  :  " + multi.getFilesystemName("boardFile"));
		
			System.out.println("가공된 것을 변환하여 원본 파일명 추출 : "
						  +BoardFileUtil.getOriginalFileName(multi.getFilesystemName("boardFile")));
		} // 	
		
		// 중복 방지 가공된 파일명 DB에 저장
		boardVO.setBoardFile(multi.getFilesystemName("boardFile") == null ? "" : 
							 multi.getFilesystemName("boardFile"));
		
		System.out.println("### boardVO : "+boardVO);
		
		// Service
		BoardService boardService = BoardServiceImpl.getInstance();
		// 최근 게시글 번호 획득 
		int boardNum = boardService.getBoardMaxNum() + 1; 
		boardVO.setBoardNum(boardNum); // 게시글 번호 반영
		
		if (boardService.insertBoard(boardVO)==true) {
			request.setAttribute("msg", "게시글 저장에 성공하였습니다.");
		} else {
			request.setAttribute("msg", "게시글 저장에 실패하였습니다.");
		}
		
		request.setAttribute("move_page", "/board/write.do");
		returnPath = "/error/result.jsp";
		
		return returnPath;
	} //

}