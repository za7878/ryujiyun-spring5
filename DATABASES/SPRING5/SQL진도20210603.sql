--DESC: Description 테이블구조를 설명
DESC dept;
--select: 테이블내용 조회, where 조회조건, as(Alias)별칭으로 필드명이 길때
--concat오라클내장함수는 레포트 작성시 
SELECT 
concat(deptno,' 번') as "부서번호"
, dname as "부서명" 
, concat(loc, ' 시') as "위치"
FROM dept 
WHERE loc = 'NEW YORK';
-- DUAL 가상테이블이름. 테이블이 없는 내용을 select할 때
SELECT 3+5 as "3더하기8은" from dual;
--래코드(row) : 컬럼(필드field)들로 구성
SELECT concat (count(*),'명') as "연봉이 2000인 직원"  FROM emp WHERE sal > 2000;
-- 큰따옴표(필드명), 작은따옴표(문자처리=비교,결합)
SELECT  sal+1000 FROM emp WHERE ename != 'KING';
SELECT * FROM emp WHERE hiredate >='1982/01/01';
-- OR는 +(합집합), AND는 X(교집합)
SELECT * FROM emp
WHERE deptno = 10 OR job = 'MANAGER';
SELECT * FROM emp WHERE sal
NOT BETWEEN 2000 AND 3000;
SELECT * FROM emp WHERE hiredate
BETWEEN '1980/01/01' AND '1980.12.31';
SELECT * FROM emp WHERE comm
not IN (300,500,1400);
-- LIKE 조회, 와일드카드=% 조회
-- 키워드에 괄호가 있으면 함수upper(), concat(), count()
SELECT * FROM emp WHERE ename LIKE upper('k%');
SELECT * FROM emp WHERE ename LIKE '%N';
--null널이 중요한 이유 : null값이 있으면 검색X
-- 그러면, null값이 필드에 있을 때, 검색은?
SELECT * FROM emp WHERE comm IS NULL;
-- NVL(Null VaLue) 널값을 대치하는 함수
-- 사원중에 커미션을 0원 받은 사람은? null사람도 구하려면 
SELECT nvl2(comm,100,0), E.* FROM emp E WHERE NVL(comm,0) = 0;
-- 오라클은 표준쿼리x, ANSI쿼리 표준이다.
SELECT DECODE(comm,null,0,100),NVL2(comm,100,0), E.* FROM emp E WHERE NVL(comm,0) = 0;
--연봉 기준으로 정렬 sort = 순서 order by 필드명 오름차순[초기값]|내림차순
-- (중요)서브쿼리? (select쿼리가 중복되어 있는...) 이다.
SELECT ROWNUM, E.* FROM (--테이블명
SELECT * FROM emp ORDER BY sal DESC --내림차순
)E WHERE ROWNUM = 1;
-- 위 서브쿼리문자를 해석 할때는 안쪽부터 해석
-- 위 정렬에서 1등만 구할 linit는 Mysql(마리아DB)의 명령어. 오라클X
-- Mysql(마리아DB) 있는 AI(AutoIncrement)자동증가값 명령 오라클X
-- 중복레코드(row)를 제거하는 명령어 distinct
SELECT deptno as "부서번호" FROM emp; --사원수대로 부서번호가 
SELECT DISTINCT deptno as "부서번호" FROM emp;
-- (중요)문자열을 연결할 때 concat 함수외에 || 파이프라인 2개를 겹쳐서 구현
SELECT ename||'is a'|| job AS"문자열 연결" FROM emp;
-- 여기까지 select 마무리 Read
-- 이후에는 CRUD중에 Insert, Update, Delete 3개의 쿼리로 끝
-- 함수용어 ABS(Absolute절대값),Floor(바닥함수1.5=1)<->Ceil(천정함수)
-- ROUND(반올림), TRUNC(Truncate버리는 함수), Mod(나머지 구하는 함수
-- Upper(대문자)Lower,length(길이구하는함수)
-- Instr(문자의 위치를 구하는 함수), sybstr(매개변수로 입력한 숫자위치만 )
-- Lpad(LeftPadding왼쪽여백), Rpad(오른쪽여백), 레포트 프로그램에서 사용.
-- Trim(왼쪽,오른쪽 잘라내는 함수)
-- 날짜 Sysdate 로 오라클전용 함수로서 게시물 입력시간, 회원등록시간.

