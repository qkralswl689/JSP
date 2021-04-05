-- mariaDB 저장 프로시저(stored procedure) : https://mariadb.com/kb/en/create-procedure/

-- procedure : https://mariadb.com/kb/en/create-procedure/
-- for : https://mariadb.com/kb/en/for/

DELIMITER $$

CREATE OR REPLACE PROCEDURE dummy_member_gen_proc()
BEGIN
	 
	 START TRANSACTION;
	 
    FOR i IN 1..100 
	 DO
        INSERT INTO member VALUES
        (CONCAT('goodee_', (1000+i)),
         '#Oracle1234',
         '별명',
         CONCAT('김', (100+i)),
         'm',
         CONCAT('goodee_',  i,  '@abcd.com'),
         CONCAT('010-1234-', (1000+i)),
         '1990-1-1',  
         '12345',  
         '서울 금천구 가산대로',
         '구디아카데미',  
         NOW());
     END FOR;
     
     COMMIT;
 
END $$


DELIMITER ;

-- 프로시저 및 스크립트 확인
show procedure STATUS; 

show create procedure dummy_member_gen_proc;

 
CALL dummy_member_gen_proc();
