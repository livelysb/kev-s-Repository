create user zuplay identified by 1234;
alter user zuplay account unlock;
grant connect, resource to zuplay;
conn zuplay/1234;

conn system/admin;

drop user zuplay cascade; -- ���� ����

drop table player;
/* �÷��̾� */
CREATE TABLE PLAYER (
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* �г��� */
	PLAYER_NAVER_ID VARCHAR2(30) NOT NULL, /* ���̹����̵� */
	PLAYER_GENDER VARCHAR2(2) NOT NULL, /* ���� */
	PLAYER_AGE VARCHAR2(30), /* ���ɴ� */
	PLAYER_LIKE INTEGER, /* ���ƿ� */
	PLAYER_MONEY INTEGER DEFAULT 0 NOT NULL, /* ���̹��Ӵ� */
	PLAYER_RUBY INTEGER DEFAULT 0 NOT NULL, /* ��� */
	PLAYER_GRADE VARCHAR2(30) NOT NULL, /* ��� */
	PLAYER_DAILY_RANK INTEGER NOT NULL, /* �ϰ� ��ũ */
	PLAYER_WEEKLY_RANK INTEGER NOT NULL, /* �ְ� ��ũ */
	PLAYER_SEASON_RANK INTEGER NOT NULL, /* ���� ��ũ */
	PLAYER_TOTAL_RANK INTEGER NOT NULL /* ��Ż ��ũ */
);

COMMENT ON TABLE PLAYER IS '�÷��̾�';

COMMENT ON COLUMN PLAYER.PLAYER_NICKNAME IS '�г���';

COMMENT ON COLUMN PLAYER.PLAYER_NAVER_ID IS '���̹����̵�';

COMMENT ON COLUMN PLAYER.PLAYER_GENDER IS '����';

COMMENT ON COLUMN PLAYER.PLAYER_AGE IS '���ɴ�';

COMMENT ON COLUMN PLAYER.PLAYER_LIKE IS '���ƿ�';

COMMENT ON COLUMN PLAYER.PLAYER_MONEY IS '���̹��Ӵ�';

COMMENT ON COLUMN PLAYER.PLAYER_RUBY IS '���';

COMMENT ON COLUMN PLAYER.PLAYER_GRADE IS '���';

COMMENT ON COLUMN PLAYER.PLAYER_DAILY_RANK IS '�ϰ� ��ũ';

COMMENT ON COLUMN PLAYER.PLAYER_WEEKLY_RANK IS '�ְ� ��ũ';

COMMENT ON COLUMN PLAYER.PLAYER_SEASON_RANK IS '���� ��ũ';

COMMENT ON COLUMN PLAYER.PLAYER_TOTAL_RANK IS '��Ż ��ũ';

ALTER TABLE PLAYER
	ADD
		CONSTRAINT PK_PLAYER
		PRIMARY KEY (
			PLAYER_NICKNAME
		);

delete 
/* �÷��̾�_������ */
CREATE TABLE PLAYER_ITEM (
	PI_SQ INTEGER NOT NULL, /* ������ */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* �г��� */
	ITEM_CODE VARCHAR(10) NOT NULL, /* ������ �ڵ� */
	PI_ISUSED VARCHAR(1) NOT NULL, /* ���뿩�� */
	PI_INDEX INTEGER /* �ε��� */
);

COMMENT ON TABLE PLAYER_ITEM IS '�÷��̾�_������';

COMMENT ON COLUMN PLAYER_ITEM.PI_SQ IS '������';

COMMENT ON COLUMN PLAYER_ITEM.PLAYER_NICKNAME IS '�г���';

COMMENT ON COLUMN PLAYER_ITEM.ITEM_CODE IS '������ �ڵ�';

COMMENT ON COLUMN PLAYER_ITEM.PI_ISUSED IS '���뿩��';

COMMENT ON COLUMN PLAYER_ITEM.PI_INDEX IS '�ε���';

ALTER TABLE PLAYER_ITEM
	ADD
		CONSTRAINT PK_PLAYER_ITEM
		PRIMARY KEY (
			PI_SQ
		);

/* ������ */
CREATE TABLE ITEM (
	ITEM_CODE VARCHAR(10) NOT NULL, /* ������ �ڵ� */
	ITEM_NAME VARCHAR2(30) NOT NULL, /* ������ �̸� */
	ITEM_PRICE INTEGER, /* ������ ���� */
	ITEM_CLASS VARCHAR2(15) NOT NULL, /* ������ ���� */
	ITEM_GRADE VARCHAR(10) NOT NULL, /* ������ ��� */
	ITEM_IMG VARCHAR2(255) NOT NULL /* �̹��� ���� */
);

COMMENT ON TABLE ITEM IS '������';

COMMENT ON COLUMN ITEM.ITEM_CODE IS '������ �ڵ�';

COMMENT ON COLUMN ITEM.ITEM_NAME IS '������ �̸�';

COMMENT ON COLUMN ITEM.ITEM_PRICE IS '������ ����';

COMMENT ON COLUMN ITEM.ITEM_CLASS IS '������ ����';

COMMENT ON COLUMN ITEM.ITEM_GRADE IS '������ ���';

COMMENT ON COLUMN ITEM.ITEM_IMG IS '�̹��� ����';

select count(*) from item;
delete item ;
insert into item values ('m_hair_00','�⺻���ڸӸ�',0,'hair','default','resource/img/avatar/head/hair-04.png','M');
insert into item values ('m_hair_01','���̸Ӹ�',20000,'hair','common','resource/img/avatar/head/hair-15.png','M');
insert into item values ('m_hair_02','Ű���Ӹ�',20000,'hair','common','resource/img/avatar/head/hair-03.png','M');
insert into item values ('m_hair_03','�����Ӹ�',25000,'hair','common','resource/img/avatar/head/hair-05.png','M');
insert into item values ('m_hair_04','�μ��Ӹ�',35000,'hair','rare','resource/img/avatar/head/hair-07.png','M');
insert into item values ('m_hair_05','���̸Ӹ�',40000,'hair','rare','resource/img/avatar/head/hair-09.png','M');
insert into item values ('m_hair_06','��ǥ�Ӹ�',45000,'hair','rare','resource/img/avatar/head/hair-10.png','M');
insert into item values ('m_hair_07','���̸Ӹ�',45000,'hair','rare','resource/img/avatar/head/hair-16.png','M');
insert into item values ('m_hair_08','�����Ӹ�',45000,'hair','rare','resource/img/avatar/head/hair-02.png','M');
insert into item values ('m_hair_09','����ڸӸ�',60000,'hair','uniq','resource/img/avatar/head/hair-12.png','M');

insert into item values ('m_eyes_00',	'�⺻��',	0,	'eyes',	'default',	'resource/img/avatar/eyes/eyes-08.png',	'a'	);
insert into item values ('m_eyes_01','�ʷմ�',	15000,	'eyes',	'common','resource/img/avatar/eyes/eyes-01.png',	'a'	);
insert into item values ('m_eyes_02',	'�밨��',	25000,	'eyes',	'common','resource/img/avatar/eyes/eyes-06.png',	'm'	);
insert into item values ('m_eyes_03','���´�',	10000,	'eyes',	'common',	'resource/img/avatar/eyes/eyes-06.png',	'a'	);
insert into item values ('m_eyes_04',	'������',	10000,	'eyes',	'common',	'resource/img/avatar/eyes/eyes-07.png',	'a'	);
insert into item values ('m_eyes_05','���ɽŶ���',	15000,	'eyes',	'common',	'resource/img/avatar/eyes/eyes-02.png',	'a'	);
insert into item values ('f_eyes_01',	'�ʷմ�	',	15000,	'eyes',	'common','resource/img/avatar/eyes/eyes-03.png',	'f'	);

insert into item values (	'f_hair_00','�⺻�Ӹ�',	0	,'hair','default','resource/img/avatar/head/hair-17.png',	'w'	);
insert into item values (	'f_hair_01','�ټظӸ�',	20000	,'hair','common','resource/img/avatar/head/hair-13.png',	'w'	);
insert into item values (	'f_hair_02','���ָӸ�',	30000	,'hair','rare','resource/img/avatar/head/hair-01.png',	'w'	);
insert into item values (	'f_hair_03','��ŭ����Ӹ�',	30000	,'hair','rare','resource/img/avatar/head/hair-06.png',	'w'	);
insert into item values (	'f_hair_04','��ũ�Ӹ�',	60000	,'hair','uniq','resource/img/avatar/head/hair-08.png',	'w'	);
insert into item values (	'f_hair_05','�ξ�Ӹ�',	70000	,'hair','uniq','resource/img/avatar/head/hair-11.png',	'w'	);
insert into item values (	'f_hair_06','���ǸӸ�',	65000	,'hair','uniq','resource/img/avatar/head/hair-14.png',	'w'	);

insert into item values (	'f_cloth_00','�⺻��',	0	,'clothes','default','resources/img/avatar/body/clothes-08.png',	'w'	);
insert into item values (	'f_cloth_01','ȸ�����̳�',	10000	,'clothes	','default','resources/img/avatar/body/clothes-09.png',	'w'	);
insert into item values (	'f_cloth_02','�⺻����',	20000	,'clothes	','default','resources/img/avatar/body/clothes-10.png',	'w'	);
insert into item values (	'f_cloth_03','û�ủ',	25000	,'clothes','default','resources/img/avatar/body/clothes-07.png',	'w'	);
insert into item values (	'm_cloth_00','�⺻��',	0	,'clothes','default','resources/img/avatar/body/clothes-02.png',	'm'	);
insert into item values (	'a_cloth_01','�ڽ���',	10000	,'clothes','common','resources/img/avatar/body/clothes-05.png',	'a'	);
insert into item values (	'm_cloth_02','ȸ�����̳�',	10000	,'clothes','common','resources/img/avatar/body/clothes-11.png',	'm'	);
insert into item values (	'm_cloth_03','�⺻����',	20000	,'clothes','common','resources/img/avatar/body/clothes-06.png',	'm'	);
insert into item values (	'm_cloth_04','û�ủ',	25000	,'clothes','common','resources/img/avatar/body/clothes-01.png',	'm'	);
insert into item values (	'm_cloth_05','��������',	30000	,'clothes','common','resources/img/avatar/body/clothes-03.png',	'm'	);
insert into item values (	'm_cloth_06','���̼���',	30000	,'clothes','common','resources/img/avatar/body/clothes-04.png',	'm'	);

insert into item values (	'm_mouse_00','�⺻��',	0	,'mouse','default','resources/img/avatar/mouse/mouse-02.png',	'a'	);
insert into item values (	'm_mouse_01','�̼���',	5000	,'mouse','common','resources/img/avatar/mouse/mouse-03.png',	'a'	);
insert into item values (	'm_mouse_02','��������',	5000	,'mouse','common','resources/img/avatar/mouse/mouse-01.png',	'a'	);
insert into item values (	'm_mouse_03','��������',	6000	,'mouse','common','resources/img/avatar/mouse/mouse-05.png',	'a'	);
insert into item values (	'm_mouse_04','�ǻ���',	6000	,'mouse','common','resources/img/avatar/mouse/mouse-04.png',	'a'	);
insert into item values (	'm_mouse_05','������',	3000	,'mouse','common','resources/img/avatar/mouse/mouse-01.png',	'a'	);

insert into item values (	'm_acc_01','��â��',	2000	,'acc','common','resources/img/avatar/acc/acc-01.png',	'a'	);
insert into item values (	'm_acc_02','����Ȱ�',	3000	,'acc','common','resources/img/avatar/acc/acc-02.png',	'a'	);


ALTER TABLE ITEM
	ADD
		CONSTRAINT PK_ITEM
		PRIMARY KEY (
			ITEM_CODE
		);
    
    insert into item values ('randombox','�����ڽ�',1000,'��Ÿ','�Ϲ�','randombox.jpg');
    delete item where item_code='randombox';
ALTER TABLE ITEM
  ADD ITEM_GENDER VARCHAR2(1) NOT NULL;
  select * from item;
/* ģ�� */
CREATE TABLE FRIEND (
	FRIEND_SQ INTEGER NOT NULL, /* ������ */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* �г��� */
	PLAYER_NICKNAME2 VARCHAR2(30) NOT NULL, /* �г���2 */
	FRIEND_ISACEPTED VARCHAR(1) NOT NULL, /* �������� */
	FRIEND_DATE DATE NOT NULL /* ģ��������¥ */
);

COMMENT ON TABLE FRIEND IS 'ģ��';

COMMENT ON COLUMN FRIEND.FRIEND_SQ IS '������';

COMMENT ON COLUMN FRIEND.PLAYER_NICKNAME IS '�г���';

COMMENT ON COLUMN FRIEND.PLAYER_NICKNAME2 IS '�г���2';

COMMENT ON COLUMN FRIEND.FRIEND_ISACEPTED IS '��������';

COMMENT ON COLUMN FRIEND.FRIEND_DATE IS 'ģ��������¥';

ALTER TABLE FRIEND
	ADD
		CONSTRAINT PK_FRIEND
		PRIMARY KEY (
			FRIEND_SQ
		);

/* �Խñ� */
CREATE TABLE BOARD (
	BOARD_NO INTEGER NOT NULL, /* �Խñ� ��ȣ */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* �г���  */
	BOARD_TITLE VARCHAR2(255) NOT NULL, /* �Խñ� ���� */
	BOARD_LIKE INTEGER NOT NULL, /* ���ƿ� */
	BOARD_CONTENT VARCHAR2(255) NOT NULL, /* �Խñ� ���� */
	BOARD_TIME DATE NOT NULL, /* �Խ� �ð� */
	BOARD_HITS INTEGER NOT NULL /* ��ȸ�� */
);
CREATE SEQUENCE board_no NOCACHE;

COMMENT ON TABLE BOARD IS '�Խñ�';

COMMENT ON COLUMN BOARD.BOARD_NO IS '�Խñ� ��ȣ';

COMMENT ON COLUMN BOARD.PLAYER_NICKNAME IS '�г���';

COMMENT ON COLUMN BOARD.BOARD_TITLE IS '�Խñ� ����';

COMMENT ON COLUMN BOARD.BOARD_LIKE IS '���ƿ�';

COMMENT ON COLUMN BOARD.BOARD_CONTENT IS '�Խñ� ����';

COMMENT ON COLUMN BOARD.BOARD_TIME IS '�Խ� �ð�';

COMMENT ON COLUMN BOARD.BOARD_HITS IS '��ȸ��';

ALTER TABLE BOARD
	ADD
		CONSTRAINT PK_BOARD
		PRIMARY KEY (
			BOARD_NO
		);

/* ��� */
CREATE TABLE BOARD_COMMENT (
	BC_SQ INTEGER NOT NULL, /* ������ */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* �г��� */
	BOARD_NO INTEGER NOT NULL, /* �Խñ� ��ȣ */
	BC_COMMENT_TIME DATE NOT NULL, /* �Խ� �ð� */
	BC_COMMENT_CONTENT VARCHAR2(255) NOT NULL /* ��� ���� */
);

COMMENT ON TABLE BOARD_COMMENT IS '���';

COMMENT ON COLUMN BOARD_COMMENT.BC_SQ IS '������';

COMMENT ON COLUMN BOARD_COMMENT.PLAYER_NICKNAME IS '�г���';

COMMENT ON COLUMN BOARD_COMMENT.BOARD_NO IS '�Խñ� ��ȣ';

COMMENT ON COLUMN BOARD_COMMENT.BC_COMMENT_TIME IS '�Խ� �ð�';

COMMENT ON COLUMN BOARD_COMMENT.BC_COMMENT_CONTENT IS '��� ����';

ALTER TABLE BOARD_COMMENT
	ADD
		CONSTRAINT PK_BOARD_COMMENT
		PRIMARY KEY (
			BC_SQ
		);

create sequence bc_sq nocache;

/* ����Ʈ */
CREATE TABLE QUEST (
	QUEST_CODE VARCHAR(10) NOT NULL, /* ����Ʈ �ڵ� */
	QUEST_CLASS VARCHAR2(15) NOT NULL, /* ����Ʈ �з� */
	QUEST_TITLE VARCHAR2(255) NOT NULL, /* ����Ʈ �� */
	QUEST_CONTENT VARCHAR2(255) NOT NULL, /* ����Ʈ ���� */
	QUEST_GOAL INTEGER NOT NULL, /* ��ǥ�� */
	QUEST_REWARD INTEGER NOT NULL, /* ����Ʈ ����� Ȥ�� ���� */
	ITEM_CODE VARCHAR(10) NOT NULL /* ������ �ڵ� */
);

COMMENT ON TABLE QUEST IS '����Ʈ';

COMMENT ON COLUMN QUEST.QUEST_CODE IS '����Ʈ �ڵ�';

COMMENT ON COLUMN QUEST.QUEST_CLASS IS '����Ʈ �з�';

COMMENT ON COLUMN QUEST.QUEST_TITLE IS '����Ʈ ��';

COMMENT ON COLUMN QUEST.QUEST_CONTENT IS '����Ʈ ����';

COMMENT ON COLUMN QUEST.QUEST_GOAL IS '��ǥ��';

COMMENT ON COLUMN QUEST.QUEST_REWARD IS '����Ʈ ����� Ȥ�� ����';

COMMENT ON COLUMN QUEST.ITEM_CODE IS '������ �ڵ�';

ALTER TABLE QUEST
	ADD
		CONSTRAINT PK_QUEST
		PRIMARY KEY (
			QUEST_CODE
		);


insert into quest values('tutorial01', 'Ʃ�丮��','������ Ȯ���ϱ�','���� �ϴ� ����â���� ������ ����',1,1,'randombox');
insert into quest values('tutorial02', 'Ʃ�丮��','�ֽ����� Ȯ���ϱ�','���� �ϴ� ����â���� �ֽ����� ����',1,1,'randombox');
insert into quest values('tutorial03', 'Ʃ�丮��','����� Ȯ���ϱ�','���� �ϴ� ����â���� ����� ����',1,1,'randombox');
insert into quest values('tutorial04', 'Ʃ�丮��','���� Ȯ���ϱ�','���� �ϴ� ����â���� ���� ����',1,1,'randombox');
insert into quest values('tutorial05', 'Ʃ�丮��','�Խ��� Ȯ���ϱ�','���� �ϴ� ����â���� �Խ��� ����',1,1,'randombox');
insert into quest values('tutorial06', 'Ʃ�丮��','ģ����� Ȯ���ϱ�','���� �ϴ� ����â���� ģ����� ����',1,1,'randombox');
delete quest where item_code='randombox';

select * from quest;

/* �÷��̾�_����Ʈ */
CREATE TABLE PLAYER_QUEST (
	PQ_SQ INTEGER NOT NULL, /* ������ */
	QUEST_CODE VARCHAR(10) NOT NULL, /* ����Ʈ �ڵ� */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* �г��� */
	PQ_DONE INTEGER NOT NULL /* ����Ƚ�� */
);

COMMENT ON TABLE PLAYER_QUEST IS '�÷��̾�_����Ʈ';

COMMENT ON COLUMN PLAYER_QUEST.PQ_SQ IS '������';

COMMENT ON COLUMN PLAYER_QUEST.QUEST_CODE IS '����Ʈ �ڵ�';

COMMENT ON COLUMN PLAYER_QUEST.PLAYER_NICKNAME IS '�г���';

COMMENT ON COLUMN PLAYER_QUEST.PQ_DONE IS '����Ƚ��';

ALTER TABLE PLAYER_QUEST
	ADD
		CONSTRAINT PK_PLAYER_QUEST
		PRIMARY KEY (
			PQ_SQ
		);
CREATE SEQUENCE pq_sq NOCACHE;


/* ������ */
CREATE TABLE LISTS (
	isuCd VARCHAR2(100) NOT NULL, /* �����ڵ� */
	isuSrtCd VARCHAR2(255) NOT NULL, /* ��������ڵ� */
	mktTpCd VARCHAR2(255), /* ���屸���ڵ� */
	isuKorNm VARCHAR2(255), /* �����ѱ۸� */
	isuKorAbbrv VARCHAR2(255) /* �����ѱ۾�� */
);

COMMENT ON TABLE LISTS IS '������';

COMMENT ON COLUMN LISTS.isuCd IS '�����ڵ�';

COMMENT ON COLUMN LISTS.isuSrtCd IS '��������ڵ�';

COMMENT ON COLUMN LISTS.mktTpCd IS '���屸���ڵ�';

COMMENT ON COLUMN LISTS.isuKorNm IS '�����ѱ۸�';

COMMENT ON COLUMN LISTS.isuKorAbbrv IS '�����ѱ۾��';

ALTER TABLE LISTS
	ADD
		CONSTRAINT PK_LISTS
		PRIMARY KEY (
			isuCd
		);

/* ������ */
CREATE TABLE MASTER (
	isuCd VARCHAR2(100) NOT NULL, /* �����ڵ� */
	isuSrtCd VARCHAR2(100), /* ��������ڵ� */
	isuKorAbbrv VARCHAR2(100), /* �����ѱ۾�� */
	govncExcelYn VARCHAR2(100), /* ���豸���췮���� */
	haltYn VARCHAR2(100), /* �ŷ��������� */
	mktcapScaleCd VARCHAR2(100), /* �ð��ѾױԸ��ڵ� */
	mfindYn VARCHAR2(100), /* ���������� */
	krxAutosSectidxYn VARCHAR2(100), /* KRX�ڵ��������������� */
	krxSemiconSectidxYn VARCHAR2(100), /* KRX�ݵ�ü������������ */
	krxBioSectidxYn VARCHAR2(100), /* KRX���̿������������� */
	krxFncSectidxYn VARCHAR2(100), /* KRX���������������� */
	krxInfoCommSectidxYn VARCHAR2(100), /* KRX������ż����������� */
	krxEnergyChemSectidxYn VARCHAR2(100), /* KRX������ȭ�м����������� */
	krxSteelSectidxYn VARCHAR2(100), /* KRXö�������������� */
	krxConsgoodSectidxYn VARCHAR2(100), /* KRX�Һ��缽���������� */
	krxMediaCommSectidxYn VARCHAR2(100), /* KRX�̵����ż����������� */
	krxConstrSectidxYn VARCHAR2(100), /* KRX�Ǽ������������� */
	krxFncSvcSectidxYn VARCHAR2(100), /* KRX�������񽺼����������� */
	krxSecuSectidxYn VARCHAR2(100), /* KRX�����������ǿ��� */
	krxShipSectidxYn VARCHAR2(100), /* KRX�����������ڿ��� */
	prevddAccTrdvol INTEGER, /* ���ϴ���ü����� */
	prevddAccTrdval INTEGER, /* ���ϴ����ŷ���� */
	uplmtprc INTEGER, /* ���Ѱ� */
	lwlmtprc INTEGER, /* ���Ѱ� */
	parval INTEGER, /* �׸鰡 */
	listShrs INTEGER, /* �����ֽļ� */
	krxInsuSectidxYn VARCHAR2(100), /* KRX�����������迩�� */
	krxTransSectidxYn VARCHAR2(100), /* KRX����������ۿ��� */
	krxRetailSectidxYn VARCHAR2(100), /* KRX���������Һ������뿩�� */
	krxLeisureSectidxYn VARCHAR2(100) /* KRX�������������������θ�Ʈ���� */
);

COMMENT ON TABLE MASTER IS '������';

COMMENT ON COLUMN MASTER.isuCd IS '�����ڵ�';

COMMENT ON COLUMN MASTER.isuSrtCd IS '��������ڵ�';

COMMENT ON COLUMN MASTER.isuKorAbbrv IS '�����ѱ۾��';

COMMENT ON COLUMN MASTER.govncExcelYn IS '���豸���췮����';

COMMENT ON COLUMN MASTER.haltYn IS '�ŷ���������';

COMMENT ON COLUMN MASTER.mktcapScaleCd IS '�ð��ѾױԸ��ڵ�';

COMMENT ON COLUMN MASTER.mfindYn IS '����������';

COMMENT ON COLUMN MASTER.krxAutosSectidxYn IS 'KRX�ڵ���������������';

COMMENT ON COLUMN MASTER.krxSemiconSectidxYn IS 'KRX�ݵ�ü������������';

COMMENT ON COLUMN MASTER.krxBioSectidxYn IS 'KRX���̿�������������';

COMMENT ON COLUMN MASTER.krxFncSectidxYn IS 'KRX����������������';

COMMENT ON COLUMN MASTER.krxInfoCommSectidxYn IS 'KRX������ż�����������';

COMMENT ON COLUMN MASTER.krxEnergyChemSectidxYn IS 'KRX������ȭ�м�����������';

COMMENT ON COLUMN MASTER.krxSteelSectidxYn IS 'KRXö��������������';

COMMENT ON COLUMN MASTER.krxConsgoodSectidxYn IS 'KRX�Һ��缽����������';

COMMENT ON COLUMN MASTER.krxMediaCommSectidxYn IS 'KRX�̵����ż�����������';

COMMENT ON COLUMN MASTER.krxConstrSectidxYn IS 'KRX�Ǽ�������������';

COMMENT ON COLUMN MASTER.krxFncSvcSectidxYn IS 'KRX�������񽺼�����������';

COMMENT ON COLUMN MASTER.krxSecuSectidxYn IS 'KRX�����������ǿ���';

COMMENT ON COLUMN MASTER.krxShipSectidxYn IS 'KRX�����������ڿ���';

COMMENT ON COLUMN MASTER.prevddAccTrdvol IS '���ϴ���ü�����';

COMMENT ON COLUMN MASTER.prevddAccTrdval IS '���ϴ����ŷ����';

COMMENT ON COLUMN MASTER.uplmtprc IS '���Ѱ�';

COMMENT ON COLUMN MASTER.lwlmtprc IS '���Ѱ�';

COMMENT ON COLUMN MASTER.parval IS '�׸鰡';

COMMENT ON COLUMN MASTER.listShrs IS '�����ֽļ�';

COMMENT ON COLUMN MASTER.krxInsuSectidxYn IS 'KRX�����������迩��';

COMMENT ON COLUMN MASTER.krxTransSectidxYn IS 'KRX����������ۿ���';

COMMENT ON COLUMN MASTER.krxRetailSectidxYn IS 'KRX���������Һ������뿩��';

COMMENT ON COLUMN MASTER.krxLeisureSectidxYn IS 'KRX�������������������θ�Ʈ����';

ALTER TABLE MASTER
	ADD
		CONSTRAINT PK_MASTER
		PRIMARY KEY (
			isuCd
		);

/* ���� */
CREATE TABLE PRICE (
	isuCd VARCHAR2(100) NOT NULL, /* �����ڵ� */
	cmpprevddTpCd VARCHAR2(100), /* ���ϴ�񱸺��ڵ� */
	cmpprevddPrc INTEGER, /* ���ϴ�񰡰� */
	trdPrc INTEGER, /* ü�ᰡ�� */
	trdvol INTEGER, /* ü�����,�ŷ��� */
	opnprc INTEGER, /* �ð� */
	hgprc INTEGER, /* �� */
	lwprc INTEGER, /* ���� */
	trdTm VARCHAR2(100), /* ü��ð�,�ŷ��ð� */
	mkStatTpCd VARCHAR(10) /* ����±����ڵ� */
);

COMMENT ON TABLE PRICE IS '����';

COMMENT ON COLUMN PRICE.isuCd IS '�����ڵ�';

COMMENT ON COLUMN PRICE.cmpprevddTpCd IS '���ϴ�񱸺��ڵ�';

COMMENT ON COLUMN PRICE.cmpprevddPrc IS '���ϴ�񰡰�';

COMMENT ON COLUMN PRICE.trdPrc IS 'ü�ᰡ��';

COMMENT ON COLUMN PRICE.trdvol IS 'ü�����,�ŷ���';

COMMENT ON COLUMN PRICE.opnprc IS '�ð�';

COMMENT ON COLUMN PRICE.hgprc IS '��';

COMMENT ON COLUMN PRICE.lwprc IS '����';

COMMENT ON COLUMN PRICE.trdTm IS 'ü��ð�,�ŷ��ð�';

COMMENT ON COLUMN PRICE.mkStatTpCd IS '����±����ڵ�';

ALTER TABLE PRICE
	ADD
		CONSTRAINT PK_PRICE
		PRIMARY KEY (
			isuCd
		);

/* �ǽð��ֽ�ü�ᰡ */
CREATE TABLE REALTIME_PRICE (
	RP_SQ INTEGER NOT NULL, /* ������ */
	isuCd VARCHAR2(100) NOT NULL, /* �����ڵ� */
	RP_trdPrc INTEGER NOT NULL, /* ü�ᰡ�� */
	RP_trdTm VARCHAR2(100) NOT NULL /* ü��ð�,�ŷ��ð� */
);

COMMENT ON TABLE REALTIME_PRICE IS '�ǽð��ֽ�ü�ᰡ';

COMMENT ON COLUMN REALTIME_PRICE.RP_SQ IS '������';

COMMENT ON COLUMN REALTIME_PRICE.isuCd IS '�����ڵ�';

COMMENT ON COLUMN REALTIME_PRICE.RP_trdPrc IS 'ü�ᰡ��';

COMMENT ON COLUMN REALTIME_PRICE.RP_trdTm IS 'ü��ð�,�ŷ��ð�';

ALTER TABLE REALTIME_PRICE
	ADD
		CONSTRAINT PK_REALTIME_PRICE
		PRIMARY KEY (
			RP_SQ
		);

/* �Ϻ��ֽ����� */
CREATE TABLE DAILY_PRICE (
	DP_SQ INTEGER NOT NULL, /* ������ */
	isuCd VARCHAR2(100) NOT NULL, /* �����ڵ� */
	DP_clsprc INTEGER NOT NULL, /* ���� */
	DP_DATE DATE NOT NULL /* ���� */
);

COMMENT ON TABLE DAILY_PRICE IS '�Ϻ��ֽ�����';

COMMENT ON COLUMN DAILY_PRICE.DP_SQ IS '������';

COMMENT ON COLUMN DAILY_PRICE.isuCd IS '�����ڵ�';

COMMENT ON COLUMN DAILY_PRICE.DP_clsprc IS '����';

COMMENT ON COLUMN DAILY_PRICE.DP_DATE IS '����';

ALTER TABLE DAILY_PRICE
	ADD
		CONSTRAINT PK_DAILY_PRICE
		PRIMARY KEY (
			DP_SQ
		);

/* �÷��̾�_�ֽ� */
CREATE TABLE PLAYER_LISTS (
	PL_SQ INTEGER NOT NULL, /* ������ */
	isuCd VARCHAR2(100) NOT NULL, /* �����ڵ� */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* �г��� */
	PL_QUANTITY INTEGER NOT NULL /* ���� */
);

COMMENT ON TABLE PLAYER_LISTS IS '�÷��̾�_�ֽ�';

COMMENT ON COLUMN PLAYER_LISTS.PL_SQ IS '������';

COMMENT ON COLUMN PLAYER_LISTS.isuCd IS '�����ڵ�';

COMMENT ON COLUMN PLAYER_LISTS.PLAYER_NICKNAME IS '�г���';

COMMENT ON COLUMN PLAYER_LISTS.PL_QUANTITY IS '����';

ALTER TABLE PLAYER_LISTS
	ADD
		CONSTRAINT PK_PLAYER_LISTS
		PRIMARY KEY (
			PL_SQ
		);

/* �ֽİŷ� ��� */
CREATE TABLE STOCK_DEAL_HISTORY (
	SDH_SQ INTEGER NOT NULL, /* ������ */
	isuCd VARCHAR2(100) NOT NULL, /* �����ڵ� */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* �г��� */
	SDH_DEAL_PRICE INTEGER NOT NULL, /* �ŷ��� */
	SDH_DEAL_TIME DATE NOT NULL, /* �ŷ� �ð� */
	SDH_QUANTITY INTEGER NOT NULL, /* ���� */
	SDH_BUY_SELL VARCHAR(1) NOT NULL /* B/S */
);

COMMENT ON TABLE STOCK_DEAL_HISTORY IS '�ֽİŷ� ���';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.SDH_SQ IS '������';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.isuCd IS '�����ڵ�';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.PLAYER_NICKNAME IS '�г���';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.SDH_DEAL_PRICE IS '�ŷ���';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.SDH_DEAL_TIME IS '�ŷ� �ð�';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.SDH_QUANTITY IS '����';

COMMENT ON COLUMN STOCK_DEAL_HISTORY.SDH_BUY_SELL IS 'B/S';

ALTER TABLE STOCK_DEAL_HISTORY
	ADD
		CONSTRAINT PK_STOCK_DEAL_HISTORY
		PRIMARY KEY (
			SDH_SQ
		);

/* �ְ����ͷ�_��� */
CREATE TABLE PRICE_EARNING_HISTORY (
	PEH_SQ INTEGER NOT NULL, /* ������ */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* �г��� */
	PEH_PE NUMBER(4,3) NOT NULL, /* ���ͷ� */
	PEH_DATE DATE NOT NULL /* ���� */
);

COMMENT ON TABLE PRICE_EARNING_HISTORY IS '�ְ����ͷ�_���';

COMMENT ON COLUMN PRICE_EARNING_HISTORY.PEH_SQ IS '������';

COMMENT ON COLUMN PRICE_EARNING_HISTORY.PLAYER_NICKNAME IS '�г���';

COMMENT ON COLUMN PRICE_EARNING_HISTORY.PEH_PE IS '���ͷ�';

COMMENT ON COLUMN PRICE_EARNING_HISTORY.PEH_DATE IS '����';

ALTER TABLE PRICE_EARNING_HISTORY
	ADD
		CONSTRAINT PK_PRICE_EARNING_HISTORY
		PRIMARY KEY (
			PEH_SQ
		);

/* ������ ����� */
CREATE TABLE ITEM_MARKET (
	IM_SQ INTEGER NOT NULL, /* ������ */
	PLAYER_NICKNAME VARCHAR2(30) NOT NULL, /* �г��� */
	ITEM_CODE VARCHAR(10) NOT NULL, /* ������ �ڵ� */
	IM_PURCHASE_PRICE INTEGER NOT NULL, /* �ǸŰ� */
	IM_BID_TIME DATE NOT NULL /* ���� �ð� */
);

COMMENT ON TABLE ITEM_MARKET IS '������ �����';

COMMENT ON COLUMN ITEM_MARKET.IM_SQ IS '������';

COMMENT ON COLUMN ITEM_MARKET.PLAYER_NICKNAME IS '�г���';

COMMENT ON COLUMN ITEM_MARKET.ITEM_CODE IS '������ �ڵ�';

COMMENT ON COLUMN ITEM_MARKET.IM_PURCHASE_PRICE IS '�ǸŰ�';

COMMENT ON COLUMN ITEM_MARKET.IM_BID_TIME IS '���� �ð�';

ALTER TABLE ITEM_MARKET
	ADD
		CONSTRAINT PK_ITEM_MARKET
		PRIMARY KEY (
			IM_SQ
		);

/* �Խñ� �̹��� */
CREATE TABLE BOARD_IMAGE (
	BI_SQ INTEGER NOT NULL, /* ������ */
	BOARD_NO INTEGER NOT NULL, /* �Խñ� ��ȣ */
	BI_ORIGIN VARCHAR2(255) NOT NULL, /* �̹������ϸ� */
	BI_NAME VARCHAR2(255) /* �Խñ��̹��� */
);

COMMENT ON TABLE BOARD_IMAGE IS '�Խñ� �̹���';

COMMENT ON COLUMN BOARD_IMAGE.BI_SQ IS '������';

COMMENT ON COLUMN BOARD_IMAGE.BOARD_NO IS '�Խñ� ��ȣ';

COMMENT ON COLUMN BOARD_IMAGE.BI_ORIGIN IS '�̹������ϸ�';

COMMENT ON COLUMN BOARD_IMAGE.BI_NAME IS '�Խñ��̹���';

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