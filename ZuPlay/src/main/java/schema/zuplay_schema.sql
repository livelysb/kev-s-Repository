create user zuplay identified by 1234;
alter user zuplay account unlock;
grant connect, resource to zuplay;
conn zuplay/1234;

conn system/admin;

drop user zuplay cascade; -- 계정 삭제

drop table player;
/* 플레이어 */
CREATE TABLE PLAYER (
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* 닉네임 */
	PLAYER_NAVER_ID VARCHAR2(30) NOT NULL, /* 네이버아이디 */
	PLAYER_GENDER VARCHAR2(2) NOT NULL, /* 성별 */
	PLAYER_AGE VARCHAR2(30), /* 연령대 */
	PLAYER_LIKE INTEGER, /* 좋아요 */
	PLAYER_MONEY INTEGER DEFAULT 0 NOT NULL, /* 사이버머니 */
	PLAYER_RUBY INTEGER DEFAULT 0 NOT NULL, /* 루비 */
	PLAYER_GRADE VARCHAR2(30) NOT NULL, /* 등급 */
	PLAYER_DAILY_RANK INTEGER NOT NULL, /* 일간 랭크 */
	PLAYER_WEEKLY_RANK INTEGER NOT NULL, /* 주간 랭크 */
	PLAYER_SEASON_RANK INTEGER NOT NULL, /* 시즌 랭크 */
	PLAYER_TOTAL_RANK INTEGER NOT NULL /* 토탈 랭크 */
);

COMMENT ON TABLE PLAYER IS '플레이어';

COMMENT ON COLUMN PLAYER.PLAYER_NICKNAME IS '닉네임';

COMMENT ON COLUMN PLAYER.PLAYER_NAVER_ID IS '네이버아이디';

COMMENT ON COLUMN PLAYER.PLAYER_GENDER IS '성별';

COMMENT ON COLUMN PLAYER.PLAYER_AGE IS '연령대';

COMMENT ON COLUMN PLAYER.PLAYER_LIKE IS '좋아요';

COMMENT ON COLUMN PLAYER.PLAYER_MONEY IS '사이버머니';

COMMENT ON COLUMN PLAYER.PLAYER_RUBY IS '루비';

COMMENT ON COLUMN PLAYER.PLAYER_GRADE IS '등급';

COMMENT ON COLUMN PLAYER.PLAYER_DAILY_RANK IS '일간 랭크';

COMMENT ON COLUMN PLAYER.PLAYER_WEEKLY_RANK IS '주간 랭크';

COMMENT ON COLUMN PLAYER.PLAYER_SEASON_RANK IS '시즌 랭크';

COMMENT ON COLUMN PLAYER.PLAYER_TOTAL_RANK IS '토탈 랭크';

ALTER TABLE PLAYER
	ADD
		CONSTRAINT PK_PLAYER
		PRIMARY KEY (
			PLAYER_NICKNAME
		);

delete 
/* 플레이어_아이템 */
CREATE TABLE PLAYER_ITEM (
	PI_SQ INTEGER NOT NULL, /* 시퀀스 */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* 닉네임 */
	ITEM_CODE VARCHAR(10) NOT NULL, /* 아이템 코드 */
	PI_ISUSED VARCHAR(1) NOT NULL, /* 착용여부 */
	PI_INDEX INTEGER /* 인덱스 */
);

COMMENT ON TABLE PLAYER_ITEM IS '플레이어_아이템';

COMMENT ON COLUMN PLAYER_ITEM.PI_SQ IS '시퀀스';

COMMENT ON COLUMN PLAYER_ITEM.PLAYER_NICKNAME IS '닉네임';

COMMENT ON COLUMN PLAYER_ITEM.ITEM_CODE IS '아이템 코드';

COMMENT ON COLUMN PLAYER_ITEM.PI_ISUSED IS '착용여부';

COMMENT ON COLUMN PLAYER_ITEM.PI_INDEX IS '인덱스';

ALTER TABLE PLAYER_ITEM
	ADD
		CONSTRAINT PK_PLAYER_ITEM
		PRIMARY KEY (
			PI_SQ
		);

/* 아이템 */
CREATE TABLE ITEM (
	ITEM_CODE VARCHAR(10) NOT NULL, /* 아이템 코드 */
	ITEM_NAME VARCHAR2(30) NOT NULL, /* 아이템 이름 */
	ITEM_PRICE INTEGER, /* 아이템 가격 */
	ITEM_CLASS VARCHAR2(15) NOT NULL, /* 아이템 구분 */
	ITEM_GRADE VARCHAR(10) NOT NULL, /* 아이템 등급 */
	ITEM_IMG VARCHAR2(255) NOT NULL /* 이미지 파일 */
);

COMMENT ON TABLE ITEM IS '아이템';

COMMENT ON COLUMN ITEM.ITEM_CODE IS '아이템 코드';

COMMENT ON COLUMN ITEM.ITEM_NAME IS '아이템 이름';

COMMENT ON COLUMN ITEM.ITEM_PRICE IS '아이템 가격';

COMMENT ON COLUMN ITEM.ITEM_CLASS IS '아이템 구분';

COMMENT ON COLUMN ITEM.ITEM_GRADE IS '아이템 등급';

COMMENT ON COLUMN ITEM.ITEM_IMG IS '이미지 파일';

select count(*) from item;
delete item ;
insert into item values ('m_hair_00','기본남자머리',0,'hair','default','resource/img/avatar/head/hair-04.png','M');
insert into item values ('m_hair_01','석이머리',20000,'hair','common','resource/img/avatar/head/hair-15.png','M');
insert into item values ('m_hair_02','키도머리',20000,'hair','common','resource/img/avatar/head/hair-03.png','M');
insert into item values ('m_hair_03','터프머리',25000,'hair','common','resource/img/avatar/head/hair-05.png','M');
insert into item values ('m_hair_04','민수머리',35000,'hair','rare','resource/img/avatar/head/hair-07.png','M');
insert into item values ('m_hair_05','범이머리',40000,'hair','rare','resource/img/avatar/head/hair-09.png','M');
insert into item values ('m_hair_06','쉼표머리',45000,'hair','rare','resource/img/avatar/head/hair-10.png','M');
insert into item values ('m_hair_07','승이머리',45000,'hair','rare','resource/img/avatar/head/hair-16.png','M');
insert into item values ('m_hair_08','뾰족머리',45000,'hair','rare','resource/img/avatar/head/hair-02.png','M');
insert into item values ('m_hair_09','어린왕자머리',60000,'hair','uniq','resource/img/avatar/head/hair-12.png','M');

insert into item values ('m_eyes_00',	'기본눈',	0,	'eyes',	'default',	'resource/img/avatar/eyes/eyes-08.png',	'a'	);
insert into item values ('m_eyes_01','초롱눈',	15000,	'eyes',	'common','resource/img/avatar/eyes/eyes-01.png',	'a'	);
insert into item values ('m_eyes_02',	'용감눈',	25000,	'eyes',	'common','resource/img/avatar/eyes/eyes-06.png',	'm'	);
insert into item values ('m_eyes_03','웃는눈',	10000,	'eyes',	'common',	'resource/img/avatar/eyes/eyes-06.png',	'a'	);
insert into item values ('m_eyes_04',	'졸린눈',	10000,	'eyes',	'common',	'resource/img/avatar/eyes/eyes-07.png',	'a'	);
insert into item values ('m_eyes_05','유령신랑눈',	15000,	'eyes',	'common',	'resource/img/avatar/eyes/eyes-02.png',	'a'	);
insert into item values ('f_eyes_01',	'초롱눈	',	15000,	'eyes',	'common','resource/img/avatar/eyes/eyes-03.png',	'f'	);

insert into item values (	'f_hair_00','기본머리',	0	,'hair','default','resource/img/avatar/head/hair-17.png',	'w'	);
insert into item values (	'f_hair_01','다솜머리',	20000	,'hair','common','resource/img/avatar/head/hair-13.png',	'w'	);
insert into item values (	'f_hair_02','진주머리',	30000	,'hair','rare','resource/img/avatar/head/hair-01.png',	'w'	);
insert into item values (	'f_hair_03','상큼레몬머리',	30000	,'hair','rare','resource/img/avatar/head/hair-06.png',	'w'	);
insert into item values (	'f_hair_04','밀크머리',	60000	,'hair','uniq','resource/img/avatar/head/hair-08.png',	'w'	);
insert into item values (	'f_hair_05','인어머리',	70000	,'hair','uniq','resource/img/avatar/head/hair-11.png',	'w'	);
insert into item values (	'f_hair_06','오션머리',	65000	,'hair','uniq','resource/img/avatar/head/hair-14.png',	'w'	);

insert into item values (	'f_cloth_00','기본옷',	0	,'clothes','default','resources/img/avatar/body/clothes-08.png',	'w'	);
insert into item values (	'f_cloth_01','회색브이넥',	10000	,'clothes	','default','resources/img/avatar/body/clothes-09.png',	'w'	);
insert into item values (	'f_cloth_02','기본정장',	20000	,'clothes	','default','resources/img/avatar/body/clothes-10.png',	'w'	);
insert into item values (	'f_cloth_03','청멜빵',	25000	,'clothes','default','resources/img/avatar/body/clothes-07.png',	'w'	);
insert into item values (	'm_cloth_00','기본옷',	0	,'clothes','default','resources/img/avatar/body/clothes-02.png',	'm'	);
insert into item values (	'a_cloth_01','박스옷',	10000	,'clothes','common','resources/img/avatar/body/clothes-05.png',	'a'	);
insert into item values (	'm_cloth_02','회색브이넥',	10000	,'clothes','common','resources/img/avatar/body/clothes-11.png',	'm'	);
insert into item values (	'm_cloth_03','기본정장',	20000	,'clothes','common','resources/img/avatar/body/clothes-06.png',	'm'	);
insert into item values (	'm_cloth_04','청멜빵',	25000	,'clothes','common','resources/img/avatar/body/clothes-01.png',	'm'	);
insert into item values (	'm_cloth_05','샤프정장',	30000	,'clothes','common','resources/img/avatar/body/clothes-03.png',	'm'	);
insert into item values (	'm_cloth_06','와이셔츠',	30000	,'clothes','common','resources/img/avatar/body/clothes-04.png',	'm'	);

insert into item values (	'm_mouse_00','기본입',	0	,'mouse','default','resources/img/avatar/mouse/mouse-02.png',	'a'	);
insert into item values (	'm_mouse_01','미소입',	5000	,'mouse','common','resources/img/avatar/mouse/mouse-03.png',	'a'	);
insert into item values (	'm_mouse_02','하하하입',	5000	,'mouse','common','resources/img/avatar/mouse/mouse-01.png',	'a'	);
insert into item values (	'm_mouse_03','스마일입',	6000	,'mouse','common','resources/img/avatar/mouse/mouse-05.png',	'a'	);
insert into item values (	'm_mouse_04','뽀뽀입',	6000	,'mouse','common','resources/img/avatar/mouse/mouse-04.png',	'a'	);
insert into item values (	'm_mouse_05','졸리입',	3000	,'mouse','common','resources/img/avatar/mouse/mouse-01.png',	'a'	);

insert into item values (	'm_acc_01','반창고',	2000	,'acc','common','resources/img/avatar/acc/acc-01.png',	'a'	);
insert into item values (	'm_acc_02','브라운안경',	3000	,'acc','common','resources/img/avatar/acc/acc-02.png',	'a'	);


ALTER TABLE ITEM
	ADD
		CONSTRAINT PK_ITEM
		PRIMARY KEY (
			ITEM_CODE
		);
    
    insert into item values ('randombox','랜덤박스',1000,'기타','일반','randombox.jpg');
    delete item where item_code='randombox';
ALTER TABLE ITEM
  ADD ITEM_GENDER VARCHAR2(1) NOT NULL;
  select * from item;
/* 친구 */
CREATE TABLE FRIEND (
	FRIEND_SQ INTEGER NOT NULL, /* 시퀀스 */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* 닉네임 */
	PLAYER_NICKNAME2 VARCHAR2(30) NOT NULL, /* 닉네임2 */
	FRIEND_ISACEPTED VARCHAR(1) NOT NULL, /* 수락여부 */
	FRIEND_DATE DATE NOT NULL /* 친구맺은날짜 */
);

COMMENT ON TABLE FRIEND IS '친구';

COMMENT ON COLUMN FRIEND.FRIEND_SQ IS '시퀀스';

COMMENT ON COLUMN FRIEND.PLAYER_NICKNAME IS '닉네임';

COMMENT ON COLUMN FRIEND.PLAYER_NICKNAME2 IS '닉네임2';

COMMENT ON COLUMN FRIEND.FRIEND_ISACEPTED IS '수락여부';

COMMENT ON COLUMN FRIEND.FRIEND_DATE IS '친구맺은날짜';

ALTER TABLE FRIEND
	ADD
		CONSTRAINT PK_FRIEND
		PRIMARY KEY (
			FRIEND_SQ
		);

/* 게시글 */
CREATE TABLE BOARD (
	BOARD_NO INTEGER NOT NULL, /* 게시글 번호 */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* 닉네임  */
	BOARD_TITLE VARCHAR2(255) NOT NULL, /* 게시글 제목 */
	BOARD_LIKE INTEGER NOT NULL, /* 좋아요 */
	BOARD_CONTENT VARCHAR2(255) NOT NULL, /* 게시글 내용 */
	BOARD_TIME DATE NOT NULL, /* 게시 시간 */
	BOARD_HITS INTEGER NOT NULL /* 조회수 */
);
CREATE SEQUENCE board_no NOCACHE;

COMMENT ON TABLE BOARD IS '게시글';

COMMENT ON COLUMN BOARD.BOARD_NO IS '게시글 번호';

COMMENT ON COLUMN BOARD.PLAYER_NICKNAME IS '닉네임';

COMMENT ON COLUMN BOARD.BOARD_TITLE IS '게시글 제목';

COMMENT ON COLUMN BOARD.BOARD_LIKE IS '좋아요';

COMMENT ON COLUMN BOARD.BOARD_CONTENT IS '게시글 내용';

COMMENT ON COLUMN BOARD.BOARD_TIME IS '게시 시간';

COMMENT ON COLUMN BOARD.BOARD_HITS IS '조회수';

ALTER TABLE BOARD
	ADD
		CONSTRAINT PK_BOARD
		PRIMARY KEY (
			BOARD_NO
		);

/* 댓글 */
CREATE TABLE BOARD_COMMENT (
	BC_SQ INTEGER NOT NULL, /* 시퀀스 */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* 닉네임 */
	BOARD_NO INTEGER NOT NULL, /* 게시글 번호 */
	BC_COMMENT_TIME DATE NOT NULL, /* 게시 시간 */
	BC_COMMENT_CONTENT VARCHAR2(255) NOT NULL /* 댓글 내용 */
);

COMMENT ON TABLE BOARD_COMMENT IS '댓글';

COMMENT ON COLUMN BOARD_COMMENT.BC_SQ IS '시퀀스';

COMMENT ON COLUMN BOARD_COMMENT.PLAYER_NICKNAME IS '닉네임';

COMMENT ON COLUMN BOARD_COMMENT.BOARD_NO IS '게시글 번호';

COMMENT ON COLUMN BOARD_COMMENT.BC_COMMENT_TIME IS '게시 시간';

COMMENT ON COLUMN BOARD_COMMENT.BC_COMMENT_CONTENT IS '댓글 내용';

ALTER TABLE BOARD_COMMENT
	ADD
		CONSTRAINT PK_BOARD_COMMENT
		PRIMARY KEY (
			BC_SQ
		);

create sequence bc_sq nocache;

/* 퀘스트 */
CREATE TABLE QUEST (
	QUEST_CODE VARCHAR(10) NOT NULL, /* 퀘스트 코드 */
	QUEST_CLASS VARCHAR2(15) NOT NULL, /* 퀘스트 분류 */
	QUEST_TITLE VARCHAR2(255) NOT NULL, /* 퀘스트 명 */
	QUEST_CONTENT VARCHAR2(255) NOT NULL, /* 퀘스트 내용 */
	QUEST_GOAL INTEGER NOT NULL, /* 목표수 */
	QUEST_REWARD INTEGER NOT NULL, /* 퀘스트 보상금 혹은 수량 */
	ITEM_CODE VARCHAR(10) NOT NULL /* 아이템 코드 */
);

COMMENT ON TABLE QUEST IS '퀘스트';

COMMENT ON COLUMN QUEST.QUEST_CODE IS '퀘스트 코드';

COMMENT ON COLUMN QUEST.QUEST_CLASS IS '퀘스트 분류';

COMMENT ON COLUMN QUEST.QUEST_TITLE IS '퀘스트 명';

COMMENT ON COLUMN QUEST.QUEST_CONTENT IS '퀘스트 내용';

COMMENT ON COLUMN QUEST.QUEST_GOAL IS '목표수';

COMMENT ON COLUMN QUEST.QUEST_REWARD IS '퀘스트 보상금 혹은 수량';

COMMENT ON COLUMN QUEST.ITEM_CODE IS '아이템 코드';

ALTER TABLE QUEST
	ADD
		CONSTRAINT PK_QUEST
		PRIMARY KEY (
			QUEST_CODE
		);


insert into quest values('tutorial01', '튜토리얼','내정보 확인하기','우측 하단 정보창에서 내정보 들어가기',1,1,'randombox');
insert into quest values('tutorial02', '튜토리얼','주식정보 확인하기','우측 하단 정보창에서 주식정보 들어가기',1,1,'randombox');
insert into quest values('tutorial03', '튜토리얼','경매장 확인하기','우측 하단 정보창에서 경매장 들어가기',1,1,'randombox');
insert into quest values('tutorial04', '튜토리얼','상점 확인하기','우측 하단 정보창에서 상점 들어가기',1,1,'randombox');
insert into quest values('tutorial05', '튜토리얼','게시판 확인하기','우측 하단 정보창에서 게시판 들어가기',1,1,'randombox');
insert into quest values('tutorial06', '튜토리얼','친구목록 확인하기','우측 하단 정보창에서 친구목록 들어가기',1,1,'randombox');
delete quest where item_code='randombox';

select * from quest;

/* 플레이어_퀘스트 */
CREATE TABLE PLAYER_QUEST (
	PQ_SQ INTEGER NOT NULL, /* 시퀀스 */
	QUEST_CODE VARCHAR(10) NOT NULL, /* 퀘스트 코드 */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* 닉네임 */
	PQ_DONE INTEGER NOT NULL /* 수행횟수 */
);

COMMENT ON TABLE PLAYER_QUEST IS '플레이어_퀘스트';

COMMENT ON COLUMN PLAYER_QUEST.PQ_SQ IS '시퀀스';

COMMENT ON COLUMN PLAYER_QUEST.QUEST_CODE IS '퀘스트 코드';

COMMENT ON COLUMN PLAYER_QUEST.PLAYER_NICKNAME IS '닉네임';

COMMENT ON COLUMN PLAYER_QUEST.PQ_DONE IS '수행횟수';

ALTER TABLE PLAYER_QUEST
	ADD
		CONSTRAINT PK_PLAYER_QUEST
		PRIMARY KEY (
			PQ_SQ
		);
CREATE SEQUENCE pq_sq NOCACHE;


/* 상장목록 */
CREATE TABLE LISTS (
	isuCd VARCHAR2(100) NOT NULL, /* 종목코드 */
	isuSrtCd VARCHAR2(255) NOT NULL, /* 종목단축코드 */
	mktTpCd VARCHAR2(255), /* 시장구분코드 */
	isuKorNm VARCHAR2(255), /* 종목한글명 */
	isuKorAbbrv VARCHAR2(255) /* 종목한글약명 */
);

COMMENT ON TABLE LISTS IS '상장목록';

COMMENT ON COLUMN LISTS.isuCd IS '종목코드';

COMMENT ON COLUMN LISTS.isuSrtCd IS '종목단축코드';

COMMENT ON COLUMN LISTS.mktTpCd IS '시장구분코드';

COMMENT ON COLUMN LISTS.isuKorNm IS '종목한글명';

COMMENT ON COLUMN LISTS.isuKorAbbrv IS '종목한글약명';

ALTER TABLE LISTS
	ADD
		CONSTRAINT PK_LISTS
		PRIMARY KEY (
			isuCd
		);

/* 마스터 */
CREATE TABLE MASTER (
	isuCd VARCHAR2(100) NOT NULL, /* 종목코드 */
	isuSrtCd VARCHAR2(100), /* 종목단축코드 */
	isuKorAbbrv VARCHAR2(100), /* 종목한글약명 */
	govncExcelYn VARCHAR2(100), /* 지배구조우량여부 */
	haltYn VARCHAR2(100), /* 거래정지여부 */
	mktcapScaleCd VARCHAR2(100), /* 시가총액규모코드 */
	mfindYn VARCHAR2(100), /* 제조업여부 */
	krxAutosSectidxYn VARCHAR2(100), /* KRX자동차섹터지수여부 */
	krxSemiconSectidxYn VARCHAR2(100), /* KRX반도체섹터지수여부 */
	krxBioSectidxYn VARCHAR2(100), /* KRX바이오섹터지수여부 */
	krxFncSectidxYn VARCHAR2(100), /* KRX금융섹터지수여부 */
	krxInfoCommSectidxYn VARCHAR2(100), /* KRX정보통신섹터지수여부 */
	krxEnergyChemSectidxYn VARCHAR2(100), /* KRX에너지화학섹터지수여부 */
	krxSteelSectidxYn VARCHAR2(100), /* KRX철강섹터지수여부 */
	krxConsgoodSectidxYn VARCHAR2(100), /* KRX소비재섹터지수여부 */
	krxMediaCommSectidxYn VARCHAR2(100), /* KRX미디어통신섹터지수여부 */
	krxConstrSectidxYn VARCHAR2(100), /* KRX건설섹터지수여부 */
	krxFncSvcSectidxYn VARCHAR2(100), /* KRX금융서비스섹터지수여부 */
	krxSecuSectidxYn VARCHAR2(100), /* KRX섹터지수증권여부 */
	krxShipSectidxYn VARCHAR2(100), /* KRX섹터지수선박여부 */
	prevddAccTrdvol INTEGER, /* 전일누적체결수량 */
	prevddAccTrdval INTEGER, /* 전일누적거래대금 */
	uplmtprc INTEGER, /* 상한가 */
	lwlmtprc INTEGER, /* 하한가 */
	parval INTEGER, /* 액면가 */
	listShrs INTEGER, /* 상장주식수 */
	krxInsuSectidxYn VARCHAR2(100), /* KRX섹터지수보험여부 */
	krxTransSectidxYn VARCHAR2(100), /* KRX섹터지수운송여부 */
	krxRetailSectidxYn VARCHAR2(100), /* KRX섹터지수소비자유통여부 */
	krxLeisureSectidxYn VARCHAR2(100) /* KRX섹터지수레저엔터테인먼트여부 */
);

COMMENT ON TABLE MASTER IS '마스터';

COMMENT ON COLUMN MASTER.isuCd IS '종목코드';

COMMENT ON COLUMN MASTER.isuSrtCd IS '종목단축코드';

COMMENT ON COLUMN MASTER.isuKorAbbrv IS '종목한글약명';

COMMENT ON COLUMN MASTER.govncExcelYn IS '지배구조우량여부';

COMMENT ON COLUMN MASTER.haltYn IS '거래정지여부';

COMMENT ON COLUMN MASTER.mktcapScaleCd IS '시가총액규모코드';

COMMENT ON COLUMN MASTER.mfindYn IS '제조업여부';

COMMENT ON COLUMN MASTER.krxAutosSectidxYn IS 'KRX자동차섹터지수여부';

COMMENT ON COLUMN MASTER.krxSemiconSectidxYn IS 'KRX반도체섹터지수여부';

COMMENT ON COLUMN MASTER.krxBioSectidxYn IS 'KRX바이오섹터지수여부';

COMMENT ON COLUMN MASTER.krxFncSectidxYn IS 'KRX금융섹터지수여부';

COMMENT ON COLUMN MASTER.krxInfoCommSectidxYn IS 'KRX정보통신섹터지수여부';

COMMENT ON COLUMN MASTER.krxEnergyChemSectidxYn IS 'KRX에너지화학섹터지수여부';

COMMENT ON COLUMN MASTER.krxSteelSectidxYn IS 'KRX철강섹터지수여부';

COMMENT ON COLUMN MASTER.krxConsgoodSectidxYn IS 'KRX소비재섹터지수여부';

COMMENT ON COLUMN MASTER.krxMediaCommSectidxYn IS 'KRX미디어통신섹터지수여부';

COMMENT ON COLUMN MASTER.krxConstrSectidxYn IS 'KRX건설섹터지수여부';

COMMENT ON COLUMN MASTER.krxFncSvcSectidxYn IS 'KRX금융서비스섹터지수여부';

COMMENT ON COLUMN MASTER.krxSecuSectidxYn IS 'KRX섹터지수증권여부';

COMMENT ON COLUMN MASTER.krxShipSectidxYn IS 'KRX섹터지수선박여부';

COMMENT ON COLUMN MASTER.prevddAccTrdvol IS '전일누적체결수량';

COMMENT ON COLUMN MASTER.prevddAccTrdval IS '전일누적거래대금';

COMMENT ON COLUMN MASTER.uplmtprc IS '상한가';

COMMENT ON COLUMN MASTER.lwlmtprc IS '하한가';

COMMENT ON COLUMN MASTER.parval IS '액면가';

COMMENT ON COLUMN MASTER.listShrs IS '상장주식수';

COMMENT ON COLUMN MASTER.krxInsuSectidxYn IS 'KRX섹터지수보험여부';

COMMENT ON COLUMN MASTER.krxTransSectidxYn IS 'KRX섹터지수운송여부';

COMMENT ON COLUMN MASTER.krxRetailSectidxYn IS 'KRX섹터지수소비자유통여부';

COMMENT ON COLUMN MASTER.krxLeisureSectidxYn IS 'KRX섹터지수레저엔터테인먼트여부';

ALTER TABLE MASTER
	ADD
		CONSTRAINT PK_MASTER
		PRIMARY KEY (
			isuCd
		);

/* 가격 */
CREATE TABLE PRICE (
	isuCd VARCHAR2(100) NOT NULL, /* 종목코드 */
	cmpprevddTpCd VARCHAR2(100), /* 전일대비구분코드 */
	cmpprevddPrc INTEGER, /* 전일대비가격 */
	trdPrc INTEGER, /* 체결가격 */
	trdvol INTEGER, /* 체결수량,거래량 */
	opnprc INTEGER, /* 시가 */
	hgprc INTEGER, /* 고가 */
	lwprc INTEGER, /* 저가 */
	trdTm VARCHAR2(100), /* 체결시각,거래시각 */
	mkStatTpCd VARCHAR(10) /* 장상태구분코드 */
);

COMMENT ON TABLE PRICE IS '가격';

COMMENT ON COLUMN PRICE.isuCd IS '종목코드';

COMMENT ON COLUMN PRICE.cmpprevddTpCd IS '전일대비구분코드';

COMMENT ON COLUMN PRICE.cmpprevddPrc IS '전일대비가격';

COMMENT ON COLUMN PRICE.trdPrc IS '체결가격';

COMMENT ON COLUMN PRICE.trdvol IS '체결수량,거래량';

COMMENT ON COLUMN PRICE.opnprc IS '시가';

COMMENT ON COLUMN PRICE.hgprc IS '고가';

COMMENT ON COLUMN PRICE.lwprc IS '저가';

COMMENT ON COLUMN PRICE.trdTm IS '체결시각,거래시각';

COMMENT ON COLUMN PRICE.mkStatTpCd IS '장상태구분코드';

ALTER TABLE PRICE
	ADD
		CONSTRAINT PK_PRICE
		PRIMARY KEY (
			isuCd
		);

/* 실시간주식체결가 */
CREATE TABLE REALTIME_PRICE (
	RP_SQ INTEGER NOT NULL, /* 시퀀스 */
	isuCd VARCHAR2(100) NOT NULL, /* 종목코드 */
	RP_trdPrc INTEGER NOT NULL, /* 체결가격 */
	RP_trdTm VARCHAR2(100) NOT NULL /* 체결시각,거래시각 */
);

COMMENT ON TABLE REALTIME_PRICE IS '실시간주식체결가';

COMMENT ON COLUMN REALTIME_PRICE.RP_SQ IS '시퀀스';

COMMENT ON COLUMN REALTIME_PRICE.isuCd IS '종목코드';

COMMENT ON COLUMN REALTIME_PRICE.RP_trdPrc IS '체결가격';

COMMENT ON COLUMN REALTIME_PRICE.RP_trdTm IS '체결시각,거래시각';

ALTER TABLE REALTIME_PRICE
	ADD
		CONSTRAINT PK_REALTIME_PRICE
		PRIMARY KEY (
			RP_SQ
		);

/* 일별주식종가 */
CREATE TABLE DAILY_PRICE (
	DP_SQ INTEGER NOT NULL, /* 시퀀스 */
	isuCd VARCHAR2(100) NOT NULL, /* 종목코드 */
	DP_clsprc INTEGER NOT NULL, /* 종가 */
	DP_DATE DATE NOT NULL /* 일자 */
);

COMMENT ON TABLE DAILY_PRICE IS '일별주식종가';

COMMENT ON COLUMN DAILY_PRICE.DP_SQ IS '시퀀스';

COMMENT ON COLUMN DAILY_PRICE.isuCd IS '종목코드';

COMMENT ON COLUMN DAILY_PRICE.DP_clsprc IS '종가';

COMMENT ON COLUMN DAILY_PRICE.DP_DATE IS '일자';

ALTER TABLE DAILY_PRICE
	ADD
		CONSTRAINT PK_DAILY_PRICE
		PRIMARY KEY (
			DP_SQ
		);

/* 플레이어_주식 */
CREATE TABLE PLAYER_LISTS (
	PL_SQ INTEGER NOT NULL, /* 시퀀스 */
	isuCd VARCHAR2(100) NOT NULL, /* 종목코드 */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* 닉네임 */
	PL_QUANTITY INTEGER NOT NULL /* 수량 */
);

COMMENT ON TABLE PLAYER_LISTS IS '플레이어_주식';

COMMENT ON COLUMN PLAYER_LISTS.PL_SQ IS '시퀀스';

COMMENT ON COLUMN PLAYER_LISTS.isuCd IS '종목코드';

COMMENT ON COLUMN PLAYER_LISTS.PLAYER_NICKNAME IS '닉네임';

COMMENT ON COLUMN PLAYER_LISTS.PL_QUANTITY IS '수량';

ALTER TABLE PLAYER_LISTS
	ADD
		CONSTRAINT PK_PLAYER_LISTS
		PRIMARY KEY (
			PL_SQ
		);

/* 주식거래 기록 */
CREATE TABLE STOCK_DEAL_HISTORY (
	SDH_SQ INTEGER NOT NULL, /* 시퀀스 */
	isuCd VARCHAR2(100) NOT NULL, /* 종목코드 */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* 닉네임 */
	SDH_DEAL_PRICE INTEGER NOT NULL, /* 거래가 */
	SDH_DEAL_TIME DATE NOT NULL, /* 거래 시각 */
	SDH_QUANTITY INTEGER NOT NULL, /* 수량 */
	SDH_BUY_SELL VARCHAR(1) NOT NULL /* B/S */
);

COMMENT ON TABLE STOCK_DEAL_HISTORY IS '주식거래 기록';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.SDH_SQ IS '시퀀스';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.isuCd IS '종목코드';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.PLAYER_NICKNAME IS '닉네임';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.SDH_DEAL_PRICE IS '거래가';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.SDH_DEAL_TIME IS '거래 시각';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.SDH_QUANTITY IS '수량';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.SDH_BUY_SELL IS 'B/S';

ALTER TABLE STOCK_DEAL_HISTORY
	ADD
		CONSTRAINT PK_STOCK_DEAL_HISTORY
		PRIMARY KEY (
			SDH_SQ
		);

/* 주가수익률_기록 */
CREATE TABLE PRICE_EARNING_HISTORY (
	PEH_SQ INTEGER NOT NULL, /* 시퀀스 */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* 닉네임 */
	PEH_PE NUMBER(4,3) NOT NULL, /* 수익률 */
	PEH_DATE DATE NOT NULL /* 일자 */
);

COMMENT ON TABLE PRICE_EARNING_HISTORY IS '주가수익률_기록';

COMMENT ON COLUMN PRICE_EARNING_HISTORY.PEH_SQ IS '시퀀스';

COMMENT ON COLUMN PRICE_EARNING_HISTORY.PLAYER_NICKNAME IS '닉네임';

COMMENT ON COLUMN PRICE_EARNING_HISTORY.PEH_PE IS '수익률';

COMMENT ON COLUMN PRICE_EARNING_HISTORY.PEH_DATE IS '일자';

ALTER TABLE PRICE_EARNING_HISTORY
	ADD
		CONSTRAINT PK_PRICE_EARNING_HISTORY
		PRIMARY KEY (
			PEH_SQ
		);

/* 아이템 경매장 */
CREATE TABLE ITEM_MARKET (
	IM_SQ INTEGER NOT NULL, /* 시퀀스 */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* 닉네임 */
	ITEM_CODE VARCHAR(10) NOT NULL, /* 아이템 코드 */
	IM_PURCHASE_PRICE INTEGER NOT NULL, /* 판매가 */
	IM_BID_TIME DATE NOT NULL /* 입찰 시각 */
);

COMMENT ON TABLE ITEM_MARKET IS '아이템 경매장';

COMMENT ON COLUMN ITEM_MARKET.IM_SQ IS '시퀀스';

COMMENT ON COLUMN ITEM_MARKET.PLAYER_NICKNAME IS '닉네임';

COMMENT ON COLUMN ITEM_MARKET.ITEM_CODE IS '아이템 코드';

COMMENT ON COLUMN ITEM_MARKET.IM_PURCHASE_PRICE IS '판매가';

COMMENT ON COLUMN ITEM_MARKET.IM_BID_TIME IS '입찰 시각';

ALTER TABLE ITEM_MARKET
	ADD
		CONSTRAINT PK_ITEM_MARKET
		PRIMARY KEY (
			IM_SQ
		);

/* 게시글 이미지 */
CREATE TABLE BOARD_IMAGE (
	BI_SQ INTEGER NOT NULL, /* 시퀀스 */
	BOARD_NO INTEGER NOT NULL, /* 게시글 번호 */
	BI_ORIGIN VARCHAR2(255) NOT NULL, /* 이미지파일명 */
	BI_NAME VARCHAR2(255) /* 게시글이미지 */
);

COMMENT ON TABLE BOARD_IMAGE IS '게시글 이미지';

COMMENT ON COLUMN BOARD_IMAGE.BI_SQ IS '시퀀스';

COMMENT ON COLUMN BOARD_IMAGE.BOARD_NO IS '게시글 번호';

COMMENT ON COLUMN BOARD_IMAGE.BI_ORIGIN IS '이미지파일명';

COMMENT ON COLUMN BOARD_IMAGE.BI_NAME IS '게시글이미지';

ALTER TABLE BOARD_IMAGE
	ADD
		CONSTRAINT PK_BOARD_IMAGE
		PRIMARY KEY (
			BI_SQ
		);

ALTER TABLE PLAYER_ITEM
	ADD
		CONSTRAINT FK_PLAYER_TO_PLAYER_ITEM
		FOREIGN KEY (
			PLAYER_NICKNAME
		)
		REFERENCES PLAYER (
			PLAYER_NICKNAME
		);

ALTER TABLE PLAYER_ITEM
	ADD
		CONSTRAINT FK_ITEM_TO_PLAYER_ITEM
		FOREIGN KEY (
			ITEM_CODE
		)
		REFERENCES ITEM (
			ITEM_CODE
		);

ALTER TABLE FRIEND
	ADD
		CONSTRAINT FK_PLAYER_TO_FRIEND
		FOREIGN KEY (
			PLAYER_NICKNAME
		)
		REFERENCES PLAYER (
			PLAYER_NICKNAME
		);

ALTER TABLE FRIEND
	ADD
		CONSTRAINT FK_PLAYER_TO_FRIEND2
		FOREIGN KEY (
			PLAYER_NICKNAME2
		)
		REFERENCES PLAYER (
			PLAYER_NICKNAME
		);

ALTER TABLE BOARD
	ADD
		CONSTRAINT FK_PLAYER_TO_BOARD
		FOREIGN KEY (
			PLAYER_NICKNAME
		)
		REFERENCES PLAYER (
			PLAYER_NICKNAME
		);

ALTER TABLE BOARD_COMMENT
	ADD
		CONSTRAINT FK_BOARD_TO_BOARD_COMMENT
		FOREIGN KEY (
			BOARD_NO
		)
		REFERENCES BOARD (
			BOARD_NO
		);

ALTER TABLE BOARD_COMMENT
	ADD
		CONSTRAINT FK_PLAYER_TO_BOARD_COMMENT
		FOREIGN KEY (
			PLAYER_NICKNAME
		)
		REFERENCES PLAYER (
			PLAYER_NICKNAME
		);

ALTER TABLE QUEST
	ADD
		CONSTRAINT FK_ITEM_TO_QUEST
		FOREIGN KEY (
			ITEM_CODE
		)
		REFERENCES ITEM (
			ITEM_CODE
		);

ALTER TABLE PLAYER_QUEST
	ADD
		CONSTRAINT FK_QUEST_TO_PLAYER_QUEST
		FOREIGN KEY (
			QUEST_CODE
		)
		REFERENCES QUEST (
			QUEST_CODE
		);

ALTER TABLE PLAYER_QUEST
	ADD
		CONSTRAINT FK_PLAYER_TO_PLAYER_QUEST
		FOREIGN KEY (
			PLAYER_NICKNAME
		)
		REFERENCES PLAYER (
			PLAYER_NICKNAME
		);

ALTER TABLE MASTER
	ADD
		CONSTRAINT FK_LISTS_TO_MASTER
		FOREIGN KEY (
			isuCd
		)
		REFERENCES LISTS (
			isuCd
		);

ALTER TABLE PRICE
	ADD
		CONSTRAINT FK_MASTER_TO_PRICE
		FOREIGN KEY (
			isuCd
		)
		REFERENCES MASTER (
			isuCd
		);

ALTER TABLE REALTIME_PRICE
	ADD
		CONSTRAINT FK_PRICE_TO_REALTIME_PRICE
		FOREIGN KEY (
			isuCd
		)
		REFERENCES PRICE (
			isuCd
		);

ALTER TABLE DAILY_PRICE
	ADD
		CONSTRAINT FK_PRICE_TO_DAILY_PRICE
		FOREIGN KEY (
			isuCd
		)
		REFERENCES PRICE (
			isuCd
		);

ALTER TABLE PLAYER_LISTS
	ADD
		CONSTRAINT FK_LISTS_TO_PLAYER_LISTS
		FOREIGN KEY (
			isuCd
		)
		REFERENCES LISTS (
			isuCd
		);

ALTER TABLE PLAYER_LISTS
	ADD
		CONSTRAINT FK_PLAYER_TO_PLAYER_LISTS
		FOREIGN KEY (
			PLAYER_NICKNAME
		)
		REFERENCES PLAYER (
			PLAYER_NICKNAME
		);

ALTER TABLE STOCK_DEAL_HISTORY
	ADD
		CONSTRAINT FK_LISTS_TO_STOCK_DEAL_HISTORY
		FOREIGN KEY (
			isuCd
		)
		REFERENCES LISTS (
			isuCd
		);

ALTER TABLE STOCK_DEAL_HISTORY
	ADD
		CONSTRAINT FK_PLAYER_TO_SDH
		FOREIGN KEY (
			PLAYER_NICKNAME
		)
		REFERENCES PLAYER (
			PLAYER_NICKNAME
		);

ALTER TABLE PRICE_EARNING_HISTORY
	ADD
		CONSTRAINT FK_PLAYER_TO_PEH
		FOREIGN KEY (
			PLAYER_NICKNAME
		)
		REFERENCES PLAYER (
			PLAYER_NICKNAME
		);

ALTER TABLE ITEM_MARKET
	ADD
		CONSTRAINT FK_PLAYER_TO_ITEM_MARKET
		FOREIGN KEY (
			PLAYER_NICKNAME
		)
		REFERENCES PLAYER (
			PLAYER_NICKNAME
		);

ALTER TABLE ITEM_MARKET
	ADD
		CONSTRAINT FK_ITEM_TO_ITEM_MARKET
		FOREIGN KEY (
			ITEM_CODE
		)
		REFERENCES ITEM (
			ITEM_CODE
		);

ALTER TABLE BOARD_IMAGE
	ADD
		CONSTRAINT FK_BOARD_TO_BOARD_IMAGE
		FOREIGN KEY (
			BOARD_NO
		)
		REFERENCES BOARD (
			BOARD_NO
		);
    
    commit;
    select * from player;