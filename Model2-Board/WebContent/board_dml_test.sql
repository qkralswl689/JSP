select max(board_num) from board;

select board_num from board;

-- mySQL 
-- select * from board 
--   order by BOARD_RE_REF desc,BOARD_RE_SEQ asc limit ?,10;
    
select rownum from board;    


-- 5 : 페이지당 글 수     
SELECT * FROM (

                SELECT 
                       m.*,
                       FLOOR((ROWNUM - 1)/10 + 1) page
                FROM (
                         SELECT * FROM board
                            ORDER BY BOARD_RE_REF DESC, BOARD_RE_SEQ ASC
                     ) m
              )
              
      WHERE page = 1;
      
--------------------------------------------

update board set BOARD_RE_SEQ=BOARD_RE_SEQ+1 
   where BOARD_RE_REF = ? 
     and BOARD_RE_SEQ > ?;