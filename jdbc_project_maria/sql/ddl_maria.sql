CREATE TABLE member (
    member_id   varchar(20)    PRIMARY KEY,
    member_password varchar(20)    NOT NULL,
    member_nickname varchar(75)    NOT NULL,
    member_name varchar(75)    NOT NULL,
    member_gender   CHAR(1)    NOT NULL,
    member_email    varchar(50)    NOT NULL,
    member_phone    varchar(13)    NOT NULL,
    member_birth    DATE    NOT NULL,
    member_zip  CHAR(5),
    member_address_basic    varchar(600),
    member_address_detail   varchar(300),
    member_joindate DATE DEFAULT NOW() NOT NULL
);

DROP TABLE member;