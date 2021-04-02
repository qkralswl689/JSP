CREATE TABLE dog(
	id NUMBER PRIMARY KEY,
	kind VARCHAR2(12) NOT NULL,
	price NUMBER NOT NULL,
	image VARCHAR2(20) NOT NULL,
	country VARCHAR2(12) NOT NULL,
	height NUMBER,
	weight NUMBER,
	content VARCHAR2(400),
	readcount NUMBER
);

CREATE SEQUENCE dog_seq;

INSERT INTO dog VALUES(dog_seq.nextval,'푸들',1000,'pu.jpg','프랑스',1,20,'털많다',0);
INSERT INTO dog VALUES(dog_seq.nextval,'불독',2000,'bul.jpg','독일',1,20,'못생겼다',0);
INSERT INTO dog VALUES(dog_seq.nextval,'진도개',3000,'jin.jpg','대한민국',1,20,'최고다',0);
INSERT INTO dog VALUES(dog_seq.nextval,'허스키',4000,'h.jpg','북극',1,20,'멋지다',0);
COMMIT

