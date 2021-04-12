package com.javateam.board_project.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
// import com.fasterxml.jackson.databind.SerializationFeature;
import com.javateam.board_project.board.domain.BoardVO;
import com.javateam.board_project.board.service.BoardService;
import com.javateam.board_project.board.service.BoardServiceImpl;
import com.javateam.board_project.board.util.BoardFileUtil;
import com.javateam.board_project.controller.CommandAction;

public class BoardViewAjaxAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("##### 개별 게시글 조회 : ajax");
		String msg = ""; // 에러 메시지
		List<Object> list = new ArrayList<>(); // 전송할 정보 
		String json = ""; // json feed 정보
		String returnPath = ""; // 이동 페이지
		// 게시글 번호
		int boardNum = request.getParameter("boardNum")==null || 
					   request.getParameter("boardNum").trim().contentEquals("") ? 0 : 
					   Integer.parseInt(request.getParameter("boardNum"));
		
		// 게시글 번호 : 인자 점검
		if (boardNum==0) {
			msg = "게시글 번호를 입력하십시오.";
			System.out.println("msg : "+msg);			
			request.setAttribute("msg", msg);
			request.setAttribute("move_page", "/board/viewAll.do");
			returnPath = "/error/result.jsp";
		} else {
			System.out.println("게시글 번호 : "+boardNum);
		} // 
		
		// 인자 점검
		try {
			    // fasterxml jackson 활용
			    ObjectMapper mapper = new ObjectMapper();
			    
			    // JsonNode jsonNode = mapper.readTree(request.getReader());
			    // System.out.println("jsonNode : " + jsonNode);
			    // boardNum = jsonNode.get("board_num").asInt();	
			    
			    System.out.println("boardNum : " + boardNum);
			    
				BoardService service = BoardServiceImpl.getInstance();
				
				BoardVO board = service.getBoard(boardNum);	
				System.out.println("게시글 : "+board);
				
				// 추가 조회수 증가
				service.updateReadCount(boardNum);
				
				// 파일 원본명
				String originalBoardFile = board.getBoardFile()==null ? "" : 
							BoardFileUtil.getOriginalFileName(board.getBoardFile()); 
				
				System.out.println("파일 : " + originalBoardFile);
				
				list.add(board);
				list.add(originalBoardFile);
				
				if (board.getBoardNum() == 0) { 
				// 게시글이 없으면 DB 테이블상의 기본값(default=0)이 설정됨.
					
					msg = "게시글 조회(보기)에 실패하였습니다.";
					System.out.println("msg : "+msg);
					request.setAttribute("msg", msg);
					request.setAttribute("move_page", "/board/viewAll.do");
					return "/error/result.jsp";
					
				} else {
					
					// JSON 정보 생성(feed)
				    // ObjectMapper mapper = new ObjectMapper();
					// 날짜 포맷 활성화
					
					// 날짜는 long 성분으로 리턴되는 UTC성분의 시간을 그대로 JSON으로 전송하여
					// Javascript에서 날짜 포맷 처리하는 방향으로 전개하였습니다. 
					// 만약 날짜 포맷을 맞추어서 송부하려면 아래와 같은 메서드를 활성화시켜야 합니다. 
					
				    // mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
				    // mapper.setDateFormat(new ISO8601DateFormat());
				    
				    System.out.println("날짜 : "+board.getBoardDate());
				    
				    json = mapper.writeValueAsString(list);
			        
			        System.out.println("//////////////////////////");
			        System.out.println("json : " + json);
					
			        request.setAttribute("json", json);
			        returnPath = "/board/json.jsp";
				}	
				
		} catch (Exception e) { // 인자 전송 오류 발생시
			
			System.out.println("" + e);
			
			msg = "게시글 조회(보기)에 실패하였습니다.";
			request.setAttribute("msg", msg);
			request.setAttribute("move_page", "/board/viewAll.do");
			returnPath = "/error/result.jsp";
		}
		
		return returnPath;
	} //

} //