--8장 함수(count, upper, lower,to_char,round...) 그룹함수
--부서별 연봉의 합계를 구해서 제일 급여가 많이 지출 되는 부서(아래)
--자바코딩에서는 소문자로 통일함 select
--DB셋팅에서 대소문자 구분해서 사용할지, 구분하지 않을 지 셋팅
SELECT * FROM(
SELECT deptno, Sum(sal) AS"dept_sal"
FROM emp GROUP BY deptno
) ORDER BY dept_sal DESC;--R의 역할은 Alisas별명.
SELECT deptno, SUM(sal) FROM emp
GROUP BY deptno
ORDER BY sum(sal) desc;

--라운드함수(반올림) 소수점기중. round(10.05,2)소수점2째반올림
SELECT ename, round(sal,-3) FROM emp;--레코드가 여러개
SELECT SUM(sal) FROM emp;--1개의 레코드만 그룹함수라고 말함.
SELECT round(AVG(sal),2) FROM emp;--평균 1개의 레코드로 출력
SELECT COUNT(*) FROM emp WHERE sal >=
(SELECT round(AVG(sal)) FROM emp);
--위 쿼리는 사원 중에 평균 연봉보다 많이 받는 사람의 숫자. error
--위 AVG함수를 where조건에 사용못할 때 서브쿼리를 이용함.
SELECT MAX(sal),
MIN(sal),
MAX(sal)-MIN(sal) AS "대표와사원의연봉차"
FROM emp;
--DDL문(create; alter; drop;), DCL문(commit; rollback;)
--DML문 (Date Manufacture Language) insert,update, delete
--insert문:테이블에 새로운 레코드(row)를 추가
CREATE TABLE dept02 AS SELECT * FROM dept WHERE 1=0;
--위 쿼리는 테이블을 복제하는 명령
-- 위처럼 쿼리를 실행 dept테이블과 구조와 내용이 똑같은 테이블 생성
--where 조건이 붙으면, 구조는 같으나 내용은 빈 테이블이 생성
INSERT INTO dept02 (
--필드명
deptno, dname, loc
)VALUES(
10, '개발부서','천안'
--바인딩값
);
insert into dept02 values(20,'디자인부','경기도');
--DCL명령어 인 커밋이 필수
COMMIT;--데이터 베이스쿼리 직접 입력한 결과는 반드시 커밋을 해줘야지만
--실제 저장이 됨. 커밋을 하지 않으면, 여기만 보이고, 다른 곳은x
SELECT * FROM dept02;
--DELETE는 레코드1줄을 지우는 명령
DELETE FROM dept02; --이렇게 사용X
DELETE * FROM WHERE deptno >= 0; --모두 삭제 where 반드시 표기
--DROP 테이블명은 테이블 자체를 물리적으로 없애는 명령
DROP TABLE dept02;--드롭 테이블 커밋없이 바로 적용됨.
CREATE TABLE emp01 AS SELECT * FROM emp;--테이블 복제 명령
SELECT * FROM emp01;
--UPDATA 테이블명 SET 필드명 = '바꿀값' where empno='특정ID'
UPDATE emp01 SET ename='정예원' where empno = 7839;
ROLLBACK;--DCL문 롤백은 마지막 커밋 바로 전 까지 되돌림.
UPDATE emp01 SET sal = sal*1.1;--모든 직원의 연봉을 10% 인상
UPDATE emp01 SET hiredate = sysdate;
--머지 표준쿼리(ANSI)가 아니라서 건너뜀