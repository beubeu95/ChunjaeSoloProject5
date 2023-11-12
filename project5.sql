CREATE DATABASE tsherpa;

USE tsherpa;

CREATE TABLE ROLE(
	role_id INT PRIMARY KEY AUTO_INCREMENT,
	role VARCHAR(255) DEFAULT NULL
);

SELECT * FROM ROLE;

INSERT INTO ROLE VALUES (DEFAULT, 'ADMIN');


CREATE TABLE user(
	user_id INT PRIMARY KEY AUTO_INCREMENT,
	active INT DEFAULT 0, 
	login_id VARCHAR(255) NOT NULL,
	user_name VARCHAR(255) NOT NULL,
	password VARCHAR(300) NOT NULL
);

SELECT * FROM user;

INSERT INTO USER VALUES (DEFAULT, 1, 'admin', '관리자', '1234'); 

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

INSERT INTO boardCate VALUES('A', '일상');
INSERT INTO boardCate VALUES('B', '정보');
INSERT INTO boardCate VALUES('C', '질문');

SELECT * FROM boardCate;

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
  
SELECT * FROM board;


-- 상품
CREATE TABLE products (
   product_no INT AUTO_INCREMENT PRIMARY KEY,		/*상품 번호 */ 	
   title VARCHAR(100) NOT NULL,							/* 제목 */
   price int NOT NULL,										/* 가격 */
   description VARCHAR(5000),							  	/* 설명 */
	user_id INT NOT NULL,								 	/* 작성자 id */
   active varchar(20) NOT NULL,							/* 거래 상태(거래 완료 여부) */
   `condition` varchar(20) NOT NULL,					/* 상품 상태(최상 상 중 하) */
   regdate DATETIME DEFAULT CURRENT_TIMESTAMP,		/*  등록일 */
   location varchar(200) NOT NULL,						/* 지역 */
   category_id INT											/* 카테고리 */
);

SELECT * FROM products;

-- 사진 파일
CREATE TABLE photos (
   photo_no INT AUTO_INCREMENT PRIMARY KEY,      	-- 번호 	
   product_no INT NOT NULL,
	saveFolder VARCHAR(300),	 						-- 상품 id
   photo_file VARCHAR(1000),		 						-- 난수화된 파일 이름
	realname VARCHAR(250),
	FOREIGN KEY(product_no) REFERENCES products(product_no) ON DELETE CASCADE 			 						-- 실제 파일 이름
); 


-- 카테고리
CREATE TABLE category (
    category_no VARCHAR(50) PRIMARY KEY, 	/* 번호 */
    category_name VARCHAR(50) NOT NULL					/* 카테고리명 */
);

INSERT INTO category VALUES ('A', '국어');
INSERT INTO category VALUES ('B', '수학');
INSERT INTO category VALUES ('C', '영어');


-- 상품 좋아요
create table product_likes(
	userid VARCHAR(20) NOT NULL,      					-- 사용자 ID
   pno INT NOT NULL,           							-- 상품 no
   liketime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 	-- 좋아요를 누른 시간
   PRIMARY KEY (userid, pno)   							-- 사용자 ID와 상품 no 조합으로 각 레코드를 유일하게 식별
);

COMMIT;
