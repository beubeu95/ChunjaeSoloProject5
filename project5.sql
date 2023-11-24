CREATE DATABASE tsherpa;

USE tsherpa;

CREATE TABLE role(
                     role_id INT PRIMARY KEY AUTO_INCREMENT,
                     role VARCHAR(255) DEFAULT NULL
);

INSERT INTO ROLE VALUES(DEFAULT, 'ADMIN');
INSERT INTO ROLE VALUES(DEFAULT, 'EMP');
INSERT INTO ROLE VALUES(DEFAULT, 'TEACHER');
INSERT INTO ROLE VALUES(99, 'USER');


CREATE TABLE user(
	user_id INT PRIMARY KEY AUTO_INCREMENT,
	active INT DEFAULT 0, 
	login_id VARCHAR(255) NOT NULL,
	user_name VARCHAR(255) NOT NULL,
	password VARCHAR(300) NOT NULL
);

SELECT * FROM user;

INSERT INTO USER VALUES (DEFAULT, 1, 'admin', '관리자', '1234'); 

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
  content VARCHAR(10000),
  nickname int,
  regdate DATETIME DEFAULT CURRENT_TIMESTAMP(),
  visited INT DEFAULT 0,
  FOREIGN KEY(cate) REFERENCES boardCate(cate) ON DELETE CASCADE,
  FOREIGN KEY(nickname) REFERENCES user(user_id) ON DELETE CASCADE
  );
  
SELECT * FROM board;


-- 상품
CREATE TABLE market(
                       marketNo INT AUTO_INCREMENT PRIMARY KEY,	-- 상품 번호
                       title VARCHAR(100) NOT NULL,	-- 제목
                       price int NOT NULL,		-- 가격
                       content VARCHAR(5000),	-- 설명
                       login_id VARCHAR(255) NOT NULL,	-- 작성자 id
                       active INT DEFAULT 0 NOT NULL,	-- 거래 상태(거래 완료 여부)
                       readable INT DEFAULT 0 NOT NULL,
                       conditions varchar(20) NOT NULL,	-- 상품 상태(최상 상 중 하)
                       regdate DATETIME DEFAULT CURRENT_TIMESTAMP,	-- 등록일
                       selected_address VARCHAR(200),     -- 선택 주소
                       detail_address VARCHAR(100),        -- 상세 주소
                       xdata DOUBLE,                      -- x
                       ydata DOUBLE                      -- y
);

CREATE TABLE photos(
                       photo_no int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       marketNo INT,
                       saveFolder VARCHAR(300) NOT NULL,
                       originFile VARCHAR(300) NOT NULL,
                       saveFile VARCHAR(300) NOT NULL
);

CREATE TABLE mainphoto(
                          mainphoto_no int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          marketNo INT,
                          saveFolder VARCHAR(300) NOT NULL,
                          originFile VARCHAR(300) NOT NULL,
                          saveFile VARCHAR(300) NOT NULL
);






CREATE VIEW detaillist AS
SELECT
    m.marketNo,
    m.title,
    m.price,
    m.content,
    m.login_id,
    m.active,
    m.conditions,
    m.regdate,
    m.selected_address,
    m.detail_address,
    m.xdata,
    m.ydata,
    p.saveFolder AS saveFolder,
    p.originFile AS originFile,
    p.saveFile AS saveFile
FROM
    market m
        LEFT JOIN photos p ON m.marketNo = p.marketNo;


CREATE VIEW totallist as
SELECT
    m.marketNo,
    m.title,
    m.price,
    m.content,
    m.login_id,
    m.active,
    m.conditions,
    m.regdate,
    m.selected_address,
    m.detail_address,
    m.xdata,
    m.ydata,
    p.saveFolder AS saveFolder,
    p.originFile AS originFile,
    p.saveFile AS saveFile,
    mp.saveFolder AS mainSaveFolder,
    mp.originFile AS mainOriginFile,
    mp.saveFile AS mainSaveFile
FROM
    market m
        LEFT JOIN photos p ON m.marketNo = p.marketNo
        LEFT JOIN mainphoto mp ON m.marketNo = mp.marketNo;




CREATE VIEW mainlist AS
SELECT
    m.marketNo AS marketNo,
    m.title,
    m.price,
    m.readable,
    m.content,
    m.login_id,
    m.active,
    m.conditions,
    m.regdate,
    m.selected_address,
    m.detail_address,
    m.xdata,
    m.ydata,
    mp.saveFolder AS saveFolder,
    mp.originFile AS originFile,
    mp.saveFile AS saveFile
FROM
    market m
        LEFT JOIN mainphoto mp ON m.marketNo = mp.marketNo;

CREATE TABLE faq (
                     fno INT  PRIMARY KEY AUTO_INCREMENT ,
                     question VARCHAR(1000) NOT NULL,
                     author varchar(100),
                     answer VARCHAR(1000) NOT NULL,
                     cnt INT DEFAULT 0 NOT NULL
);

CREATE TABLE qna(
                    qno int PRIMARY KEY AUTO_INCREMENT,   			-- 번호
                    title VARCHAR(100) NOT NULL,   					-- 제목
                    content VARCHAR(10000) NOT NULL,   				-- 내용`
                    author VARCHAR(16),   								-- 작성자
                    resdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 등록일
                    lev INT DEFAULT 0, 									-- 질문(0), 답변(1)
                    par INT DEFAULT 0);													-- 질문(자신 레코드의 qno), 답변(질문의 글번호)


CREATE TABLE report (
                        report_id INT PRIMARY KEY AUTO_INCREMENT, -- 신고 번호
                        marketNo INT,
                        req_no int,
                        title varchar(100),-- 게시글 번호
                        login_id  VARCHAR(255),
                        reporter VARCHAR(16), -- 신고자
                        reason VARCHAR(300), -- 이유
                        report_date DATETIME DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE likes (
                       lno INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                       login_id VARCHAR(20) NOT NULL,
                       marketNo INT,
                       req_no INT,
                       liketime DATETIME DEFAULT CURRENT_TIMESTAMP
);



COMMIT;
