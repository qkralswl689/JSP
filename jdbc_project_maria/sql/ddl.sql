CREATE TABLE member (
    member_id   varchar2(20)    PRIMARY KEY,
    member_password varchar2(20)    NOT NULL,
    member_nickname varchar2(75)    NOT NULL,
    member_name varchar2(75)    NOT NULL,
    member_gender   CHAR(1)    NOT NULL,
    member_email    varchar2(50)    NOT NULL,
    member_phone    varchar2(13)    NOT NULL,
    member_birth    DATE    NOT NULL,
    member_zip  CHAR(5),
    member_address_basic    varchar2(600),
    member_address_detail   varchar2(300),
    member_joindate DATE DEFAULT sysdate NOT NULL
);

DROP TABLE member;