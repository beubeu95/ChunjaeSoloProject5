CREATE DATABASE tsherpa;

USE tsherpa;

CREATE TABLE ROLE(
	role_id INT PRIMARY KEY AUTO_INCREMENT,
	role VARCHAR(255) DEFAULT NULL
);

SELECT * FROM ROLE;

INSERT INTO ROLE VALUES (DEFAULT, 'ADMIN');
INSERT INTO ROLE VALUES (DEFAULT, 'USER');
INSERT INTO ROLE VALUES (DEFAULT, 'TEACHER');



CREATE TABLE user(
	user_id INT PRIMARY KEY AUTO_INCREMENT,
	active INT DEFAULT 0, 
	login_id VARCHAR(255) NOT NULL,
	user_name VARCHAR(255) NOT NULL,
	password VARCHAR(300) NOT NULL
);

SELECT * FROM user;

INSERT INTO USER VALUES (DEFAULT, 1, 'admin', '관리자', '1234'); 
UPDATE user SET password='$2a$10$dOuu/WdISMPA2/QTkGiwG.eh.JbhtRXHTAOVHnyBfh.Cx9cnGTb46' WHERE active=1;

CREATE TABLE user_role(
	user_id INT NOT NULL,
	role_id INT NOT NULL,
	PRIMARY KEY (user_id, role_id)
);

SELECT * FROM user_role;

INSERT INTO user_role VALUES ( 1, 1);


CREATE TABLE notice(
	no INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(300) NOT NULL,
	content VARCHAR(1000) NOT NULL,
	author INT,
	regdate DATETIME DEFAULT CURRENT_TIME,
	cnt INT DEFAULT 0,
	FOREIGN KEY(author) REFERENCES user(user_id) ON DELETE CASCADE
);

INSERT INTO notice VALUES (DEFAULT,'샘플 글 제목1  입니다.','여기는 샘플 글 1의 내용입니다.',1,DEFAULT, DEFAULT);
INSERT INTO notice VALUES (DEFAULT,'샘플 글 제목2  입니다.','여기는 샘플 글 2의 내용입니다.',1,DEFAULT, DEFAULT);
INSERT INTO notice VALUES (DEFAULT,'샘플 글 제목3  입니다.','여기는 샘플 글 3의 내용입니다.',1,DEFAULT, DEFAULT);
INSERT INTO notice VALUES (DEFAULT,'샘플 글 제목4  입니다.','여기는 샘플 글 4의 내용입니다.',1,DEFAULT, DEFAULT);
INSERT INTO notice VALUES (DEFAULT,'샘플 글 제목5  입니다.','여기는 샘플 글 5의 내용입니다.',1,DEFAULT, DEFAULT);
INSERT INTO notice VALUES (DEFAULT,'샘플 글 제목6  입니다.','여기는 샘플 글 6의 내용입니다.',1,DEFAULT, DEFAULT);

SELECT * FROM notice;


CREATE TABLE boardCate(
	cate VARCHAR(5) PRIMARY KEY NOT NULL,
	cateName VARCHAR(100) NOT NULL
);

INSERT INTO boardCate VALUES ('A', '일상');
INSERT INTO boardCate VALUES ('B', '질문');
INSERT INTO boardCate VALUES ('C', '정보');

CREATE TABLE board(
	seq INT PRIMARY KEY AUTO_INCREMENT,
	cate VARCHAR(5) NOT NULL,
   title VARCHAR(200) NOT NULL,
   content VARCHAR(1000),
   nickname int,
   regdate DATETIME DEFAULT CURRENT_TIMESTAMP(),
   visited INT DEFAULT 0,
   FOREIGN KEY(cate) REFERENCES boardCate(cate) ON DELETE CASCADE,
   FOREIGN KEY(nickname) REFERENCES user(user_id) ON DELETE CASCADE 
);

INSERT INTO board (cate, title, content, nickname) VALUES ('A' , 'test', 'test1입니다.', 1);

COMMIT;