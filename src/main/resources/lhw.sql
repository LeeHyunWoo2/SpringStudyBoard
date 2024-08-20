create sequence seq_board; -- 자동 번호 객체 생성

create table tbl_board(
	bno number(10,0),
	title varchar2 (200) not null,
	content varchar2 (2000) not null,
	writer varchar2 (50) not null,
	regdate date default sysdate,
	updatedate date default sysdate
) -- tbl_board 테이블 생성(번호, 제목, 내용, 작성자, 작성일, 수정일)

alter table tbl_board add constraint pk_board primary key (bno);

select * from TBL_BOARD;

insert into TBL_BOARD (bno, title, content, writer) values(seq_board.nextval, '테스트 제1목', '테스트 내1용', '1kkw');
insert into TBL_BOARD (bno, title, content, writer) values(seq_board.nextval, '테스트 제2목', '테스트 내2용', '2kkw');
insert into TBL_BOARD (bno, title, content, writer) values(seq_board.nextval, '테스트 제3목', '테스트 내3용', '3kkw');
insert into TBL_BOARD (bno, title, content, writer) values(seq_board.nextval, '테스트 제4목', '테스트 내4용', '4kkw');
insert into TBL_BOARD (bno, title, content, writer) values(seq_board.nextval, '테스트 제5목', '테스트 내5용', '5kkw');

drop table TBL_BOARD;
drop sequence seq_board;