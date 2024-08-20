create user lhw identified by 1234 default tablespace users temporary tablespace temp;

grant connect, dba to lhw;

select dbms_xdb.gethttpport() from dual;

exec dbms_xdb.sethttpport(9090);
-- 이 vm웨어는 톰캣이 8000 쓰고 있어서 9090으로 안바꿔도 됨