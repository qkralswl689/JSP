-- 검색

--- 검색 키워드(필드) : ex) "이름"  -> member_name (치환)
--- 유사/동등 검색 여부
--- 연산에 따른 연산자 구별(like(유사), =(동등)) 
--- 검색어 처리 : 유사/동등 검색에 따라 다름
--- 페이지 당 레코드(행) 수
--- 현재 페이지

SELECT *
FROM (SELECT ROWNUM,  
             m.*,    
             FLOOR((ROWNUM - 1) / 10 + 1) page  
      FROM (  
             SELECT * FROM member 
             WHERE member_name like '%순%'
             ORDER BY member_id ASC  
           ) m    
      )    
WHERE page = 1;


-- MariaDB

SELECT *
FROM member 
WHERE member_name like '%순%'
ORDER BY member_id ASC
LIMIT 0, 10;  