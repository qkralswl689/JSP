-- CRUD(insert, select, update, delete) : DML

-- 개별 회원정보 생성
INSERT INTO member VALUES
('javacan', '#Abcd1234', '자바맨', '홍길동', 'm', 'abcd@abcd.com',
'010-1234-5678', '1990-01-01', '12345','서울 금천구 가산',
'대륭 3차 11층', NOW());

INSERT INTO member VALUES
('javaman', '#Abcd1234', '자바샘', '고길동', 'm', 'abcd@abcd.com',
'010-1234-5678', '1990-01-01', '12345','서울 금천구 가산',
'대륭 3차 11층', NOW());

INSERT INTO member VALUES
('javaspring', '#Abcd1234', '스프링맨', '홍길동', 'm', 'abcd@abcd.com',
'010-1234-5678', '1990-01-01', '12345','서울 금천구 가산',
'대륭 3차 11층', NOW());

-- 개별 회원정보 조회 
SELECT * FROM member WHERE member_id='java';
 
-- 전체 회원정보 조회
SELECT * FROM member;

-- 개별 회원정보 수정(갱신)
UPDATE member SET
member_password='#Spring1234',
member_zip='12345',
member_address_basic='서울 금천구 가산디지털단지',
member_address_detail='구디아카데미'
WHERE member_id='java1234';

-- 개별 회원정보 삭제
DELETE member WHERE member_id='java1234';

-- 전체 회원정보 삭제
DELETE member;

-- 회원 여부 점검
-- SELECT * From member WHERE member_id='java123123123';
SELECT count(*) FROM member WHERE member_id='java';

-- 회원 인증 여부 점검
SELECT count(*) FROM member 
WHERE member_id='java' AND
      member_password='1234';
      
SELECT count(*) FROM member 
WHERE member_id='java' AND
      member_password='1234123123';      
      
-- 페이징 SQL
      
SELECT *
FROM member 
ORDER BY member_id ASC
LIMIT 0, 10;

-- 이메일 중복 점검
-- count : https://mariadb.com/kb/en/count/

SELECT count(*) FROM member
WHERE member_email = 'abcd1@abcd.com';

SELECT count(*) FROM member
WHERE member_email = 'abcdabcd@abcd.com';

-- decode 함수(조건 구문 함수) 활용
-- decode_oracle : https://mariadb.com/kb/en/decode/
-- 0 (중복 없음) -> true(사용가)
-- 1 (중복 있음) -> false(사용 불가)
SELECT DECODE_ORACLE(count(*), 0, 'true', 1, 'false') FROM member
WHERE member_email = 'abcd1@abcd.com';

SELECT DECODE_ORACLE(count(*), 0, 'true', 1, 'false') FROM member
WHERE member_email = 'abcdabcd@abcd.com';


-- 이메일 중복 점검

-- 회원 아이디/이메일 => 회원정보 수정시 
-- 기존의 이메일과 중복이 안되면서 
-- 기존의 자신의 이메일과는 중복 허용(수정 안하고 재사용 가능)

-- case) 회원 이메일 변경하고자 함.

-- 0 : 사용가 : 자신의 것을 재사용
SELECT count(*) FROM member
WHERE member_id != 'javacan'	
  AND member_email = 'abcd1@abcd.com';

-- 1 : 사용불가 : 다른 회원 아이디의 이메일과 중복
SELECT count(*) FROM member
WHERE member_id != 'javacan'	
  AND member_email = 'abcd2@abcd.com';

-- 0 : 사용가 : 중복 안되는 새로운 이메일
SELECT count(*) FROM member
WHERE member_id != 'javacan'	
  AND member_email = 'abcdabcd@abcd.com';  
  
  
-- 연락처(전화번호) 중복 점검

SELECT count(*) FROM member
WHERE member_phone = '010-1234-1001';

SELECT count(*) FROM member
WHERE member_phone = '010-5678-1001';

-- decode 함수(조건 구문 함수) 활용

-- 0 (중복 없음) -> true(사용가)
-- 1 (중복 있음) -> false(사용 불가)
SELECT DECODE_ORACLE(count(*), 0, 'true', 1, 'false') FROM member
WHERE member_phone = '010-1234-1001';

SELECT DECODE_ORACLE(count(*), 0, 'true', 1, 'false') FROM member
WHERE member_phone = '010-5678-1001';


-- 연락처(전화번호) 중복 점검

-- 회원 아이디/연락처(전화번호) => 회원정보 수정시 
-- 기존의 연락처(전화번호)과 중복이 안되면서 
-- 기존의 자신의 연락처(전화번호)과는 중복 허용(수정 안하고 재사용 가능)

-- case) 회원 연락처(전화번호) 변경하고자 함.

-- 0 : 사용가 : 자신의 것을 재사용
SELECT count(*) FROM member
WHERE member_id != 'goodee_1001'	
  AND member_phone = '010-1234-1001';

-- 1 : 사용불가 : 다른 회원 아이디의 연락처(전화번호)과 중복
SELECT count(*) FROM member
WHERE member_id != 'goodee_1001'	
  AND member_phone = '010-1234-1002';

-- 0 : 사용가 : 중복 안되는 새로운 연락처(전화번호)
SELECT count(*) FROM member
WHERE member_id != 'goodee_1001'	
  AND member_phone = '010-5678-1111';  
  
-- 검색

--- 검색 키워드(필드) : ex) "이름"  -> member_name (치환)
--- 유사/동등 검색 여부
--- 연산에 따른 연산자 구별(like(유사), =(동등)) 
--- 검색어 처리 : 유사/동등 검색에 따라 다름
--- 페이지 당 레코드(행) 수
--- 현재 페이지

SELECT *
FROM member 
WHERE member_name LIKE '%순%'
ORDER BY member_id ASC
LIMIT 0, 10;


-- 참고) str_to_date : https://mariadb.com/kb/en/str_to_date/

SELECT *
FROM member 
WHERE member_birth = '2016-02-05'
ORDER BY member_id ASC
LIMIT 0, 10;  


-- DATE_FORMAT : https://mariadb.com/kb/en/date_format/

-- ex) 금요일
SELECT DATE_FORMAT('2021-04-02', '%W', 'ko_KR');

SELECT member_joindate FROM member;

-- ex) 2021-04-02
SELECT DATE_FORMAT(member_joindate, '%Y-%m-%d') 
FROM member;

-- ex) 2021-4-2
SELECT DATE_FORMAT(member_joindate, '%Y-%c-%e') 
FROM member;


SELECT *
FROM member 
WHERE member_joindate = '2021-04-02'
ORDER BY member_id ASC
LIMIT 0, 10;  

SELECT *
FROM member 
WHERE member_joindate = '2021-4-2'
ORDER BY member_id ASC
LIMIT 0, 10;  
