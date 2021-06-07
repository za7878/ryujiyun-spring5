####작업예정
- 한 달(프론트엔드) 간은 주로 UI 계속 진행(vs code) 하면서,
- 피곤할 때, 자바|스프링 - (이클립스-egov전자정부프레임워크 개발환경)기본언어 실습진행.
- 피곤할 때, 오라클DB(SQL디벨러퍼 개발환경)Ansi-SQL(표준SQL)기본언어 실습진행 CRUD
- 2달 째 부터(백엔드), 주로 스프링 실습이 진행 (납품용-이력서 포트폴리오용)
- VS code에서 만든 UI를 JSP로 변경 한 후 스프링웹프로젝트를 진행
- 작업비중 (시간) = 관리단프로그램(70%)+사용자단(30%)

####20210607(월) 작업내용
- 마이바티스 추가 순서: 1번+2번 끝
- 1. pom.xml에 의존성 추가.
- 2. 마이바티스 설정 파일 추가.(쿼리를 저장할 위치지정, 파일명지정)
- 스프링에서는 마이바티스를 이용해서 쿼리를 관리함.
-이전에는 자바코드(jsp코드)안에 쿼리를 만들어서 프로그램을 제작함 -> 문제점 발견(스파게티 코딩)
- MVC로 분리: Model부분의 SQL쿼리를 분리하도록 기능을 추가한 것이 마이바티스임
- 마이바티스(mybatis): 형은 아이바티스(ibatis) 지금X -> 마이바티스(현재)
- 마이바티스 나오기 전에는 쿼리를 모아서 작업을 프로시저로 만들어서 작업을진행
- 스프링이(마이바티스) 나오기 전에는 쿼리를 모아서 작업을 프로시저로 만들어서 작업
- 지금은 스프링으로 옮기지 못한 프로그램만 제외하고는 대부분 마이바티스로 쿼리를 관리
- 그래서, 마이바티스 셋팅을 함.
-책 스프링 웹프로젝트는 개발STS(스프링툴슈트) 툴 -> 기반은 이클립스 기반을 확장
- 우리가 하는 책 스프링 웹프로젝트는 개발 전자정부표준 프레임워크 개발 툴 -> 기반은 이클립스 기반을 확장. -> 전자정부 표준 프레임워크를 커스터마이징.(제외)
- 오라클의 DB관리 로그인 정보:(Application Express웹프로그램이름) admin/apmsetup -> 암호변경요청: Apmsetup1234% (대문자 + 숫자 + 특수문자 추가)

```
--SQL디벨리퍼 에서 system으로 로그인후 아래 쿼리로 XE2사용을 완벽하게 지우기(아래)
SELECT * FROM all_users;
-- all_users는 테이블x, 시노님(동의어)
delete from all_users where username='XE2';
--시노님=테이블명이 길어서 사용하기 힘들 정도로 길거나
--오라클은 스프링과 같은 방식 패키지명안에 함수,
--프로시저(오라클 프로그램)를 만들 수 있습니다.
--패키지명이 길어서 사용시 계발자에게 부담이 됨.
--위 상황을 해결하도록 만든 것이 시노님(동의어)임.
DROP USER XE2 CASCADE; --XE2사용자를 지울 때,
--XE사용자가 생성한 테이블까지 모두 지우는 명령
--CSS(Cascade계층형 Style Sheet)
```
- 현업에서는 오픈소스인 Mysql(마리아DB)를 사용할 기회가 더 많음. -개발자가 많은 편.
- 오라클은 납품시, SW비용이 선정이 되어서 사용하는 기회가 적지만, 개발자가 상대적으로 적은 편.
- 보통은 학교, 시청, 기업체 전산실 에 부탁을 해서 XE(기타)사용자를 추가해 달라고 요청해서, 발급받은 계정으로 개발.
- 오라클 기초이론 마무리 : 시퀸스(스프링에서 사용- AutoIncreament자동증가기능)
- 만약 시퀸스(AI)기능이 없다면, 게시물 작성시 첫번째, 두번째 게시물 등등을 구별하는
- 숫자를 계속 추가 하려면, 현재까지 저장된 게시물의 최고번호 값(Max)을 구해서 +1 해야 함. (개발자가 insert시)
- DB에서 기본으로 Max값을 구해서 +1하는 로직을 만들게 됨. 이 기능이 시퀸스(AI)임.
- 우리 스프링 프로젝트에서는 2개 시퀸스를 만듬. (게시물 시퀸스, 댓글 시퀸스)
- 시노님(긴 객체를 개발자가 타이핑하기 어려워서 만든 단축 이름) 예, sys.dual -> dual 단축이름으로 사용간능.

- 오라클: 더미데이터 일광등록 예정. 회원관리(100명),
게시판관리(공지사항50개,갤러리50개)

-위 더미데이터는 프로시저(함수)라는 DB프로그램방식으로 추가 함.
- 오라클 : 댓글은 수동등록 후 마무리.
- JUnit로 CRUD 실습

####20210604(금) 작업내용
- 오라클일 때 : localhost:1521/xe 접속 URL끝의 XE 서비스 ID명 1개 > XE, XE2 스키마2개(DB2개) 존재함.
- 오라클은 사용자명(XE)이 스키마명(DB명)이다. 사용자(XE2)워크스페이스를 추가하면 스키마(DB)가 새로 만들어 짐.(구버전은 테스크탑 프로그램 -> 지금은 웹프로그램 사용자 추가/DB(테이블 스페이스)추가
- Mysql(마이라DB): localhost:3306/XE URL끝의 XE 스키마명 (DB명)
- junit테스트시 SQL에러를 디버그 하는 방법은 jdbc드라이버 -> log4jdbc드라이버 바꾸면, SQL에러가 나오게 됨.
- junit에서는 SQL에러가 보임. 콘솔창에서는 보이지 않음. 콘솔창에서는 SQL로그상황이 나와게 하는 드라이버 추가 pom.xml에 추가함.

#### 20210603(목) 작업내용.
- 1. jdbc(Java DabaBase Connection) 확장 모듈 pom에 추가
- 2. 오라클 접속 드라이버 확장 모듈을 pom에 추가X 직접 jar 파일을 추가.
- root-context.xml 파일에 오라클 커넥션 빈(스프링클래스)을 추가.
- 스프링이 관리하는 클래스를 추가하는 방법 2-1 :@Cntroller, @Repository, @Service, @Component
- 스프링이 관리하는 클래스를 추가하는 방법 2-2 : -context.xml 에서 빈(bean태그)을 추가하면, 스프링 클래스가 됨.
- Junit테스트 : 오라클 연동후

#### 20210602(수) 작업예정.
- views/home/index.jsp 엑박처리 후 분해(header.jsp,footer.jsp, index.jsp)
- admin 폴더 만든이후 분해처리 예정.
- admin 부터 프로그램 작업 시작예정.
- 4장 패키지와 예외처리 실습

#### 20210601 (화) 작업내용.
- 에러 : 404(file not found 경로이상일 때), 500(자바 프로그램에러)
- 프로젝트의 버전을 올린다. -> 외부 라이브러리 부분의 버전을 올림 - 메이븐 컴파일러 -> pom.xml 버전을 관리 메이븐은
- Controller클래스에서 생성한 변수 사용(자바)model 객체전송(스프링) return home
->(전송은 스프링) home.jsp 출력
- 출력하는 방식1: 25년 전부터 방식 EL(Express Language)방식출력= ${변수} , <%= ${변수} %>
- 많이사용하는 방식 JSTL방식(*표준) 사용. 출력,반복,조건 등등 다양한 방식
- 언어를 배우는 순서 : 주석 -> 디버그 하는 방법 -> 변수 -> 메서드 (함수)-> 클래스 -> MVC모델
- 디버그하는 방법: 자바(System.out 이용해서 콘솔창에 출력)
- 스프링에서는 logger(로거)를 사용해서 디버그 내용 출력.
- 스프링이 관리하는 클래스 (빈)의 종류 3가지. (사용법은 개발자가 만드는 클래스 상단에 입력)
- 1.@Controller 클래스빈 : 라우터역할 메서드를 만든다.(라우터역할의 클래스기능)
- 2.@Service 클래스빈 : 비지니스로직 메서드를 만든다.(개발자위주의 외부클래스기능)
- 3.@Repository 클래스빈 : Model처리 메서드를 만든다.(DB핸들링 외부클래스사용)
- 위 3가지의 @를 사용하는 클래스는 DI(객체생성-의존성주입), AOP, IoC(제어의 역전-자동 메모리관리)
- 프런트 개발자가 작업한 결과 백엔드 개발자가 확인.
- 일괄바꾸기 1."/home -> "/resources/home
- 일괄바꾸기 2.'/home -> '/resources/home
- Vs code 작업한 html를 이클립스에 배치를 한다. (프런트 개발자가 작업결과를 백엔드 개발자가 받아서 배치한다.)
- resourecs 폴더에 static컨텐츠(html, css, js, font) 배치한다.
- ERD기준으로 게시판UI 마무리. bored_write.html 부터 시작
- 관리자단 AdminLTE적용-스프링시간 선택 후, 아래 작업진행예정
- (회원관리CRUD-jsp, 게시판생성관리CRUD-jsp)
- html을 분해(1개의 페이지를 2개로 분해, 1. header.jsp(메뉴를 공통), 2. footer.jsp(공통))
- 이클립스로 작업한 html 내용을 -> resources 폴더(admin,home,root파일까지)로 배치
- 스프링 작업의 시작.


#### 20210531(월) 작업내용.
- 토드(sql디벨러퍼와 비슷한 상용)에서는 버튼으로 포워드엔지니어링이 처리.
- 무료sql 디벨러퍼에서는 버튼X. DDL문을 실행해서 포워드엔지니어링을 처리.
- 참고, mysql용 워크벤치는 무료이지만, 버튼으로 포워드엔지니어링이 가능.
- sql쿼리 3가지 분류
- 초기 DDL문: Data Definition Language. 데이터 정의언어 Create table문
- 유지 DCL문: Data Control Language. 	     데이터 제어언어 Commit, rollback
- 개발 DML문: Data Manufacture Language.데이터 조작언어 CRUD 쿼리.
- ERD 그림 만든것을 물리 테이블 만드는 것: forward 엔지니어링
- 데이터 딕셔너리를 모델과 동기화 : 자료사전(데이터의 데이터)을 DB테이블과 동기화
- 데이터 딕셔너리는 메타 데이터와 동일합니다.:콘텐츠X, 구조, 구성 정보만 존재.
- 물리테이블을 ERD 그리므로 만드는 것  : reverse 엔지니어링
- 스프링시큐리티는 2단계 : 구현예정.
1. 로그인인증(ENABLED): AUTHENTICATION (로그인X, 글쓰기 가능X, 관리자X)
2. 권한체크(LEVELS):AUTHORITY(관리자-admin URL 접근가능, 일반회원은 사용자 홈페이지에서 공지사항X, 갤러리만 사용가능)
1:N관계? 게시물1개에 댓글 N개 달릴 수 있음.
- E-R다이어그램은 그림일 뿐, 물리DB(테이블)은 ERD기술로 생성가능.
- PK를 AI(Auto Increment 자동증가)로 만들기: 오라클(시퀸스객체로기능구현). Mysql(AI라는 필드속성으로 처리)
- ERD에서 Relation 생성 : 게시판타입테이블(부)과 게시물관리테이블(자)의 관계를 생성
- 부자의 관계는 부모의 PK를 기준, 자식에게는 FK(Foreign Key)로 관계를 맺음.
- 게시판타입 : notice, gallery 2가지가 존재한다면,
- 게시물은 : 공지사항이나 갤러리 게시판이 아니면, 개발자가 예외처를 하지 않아도 다른 게시판으로 등록하는 상황이 발생 하지 않게 됨.
- 데이터 무결성을 유지하는 역할. (외래키 관계)
- 필드 데이터형3 : Timestamp(시간도장) 년월일시분초밀리초, Date(년월일까지)
- 필드 데이터형2 : CLOB(CHAR LOGN BYTE)글 내용이 많을 때 사용하는 데이터형태
- 필드 데이터형: VARCHAR2 (2바이트를 1글자-한글), VARCHAR(1바이트 1글자-영문전용)
- 테이블구성 : 필드(컬럼,열)=테이블의 멤버변수(자바VO클래스의 멤버변수)
- 필드구성: PrimayKey(주키, 기본키, 고유키)= 테이블영역에서 고유한 ID를 말함(중복되지 않는 값)
- PK예(개인을 식별할 수 있는 값) : 로그인 id, 개인 이메일주소 ,주민번호 등등, 게시판타입
- RDBMS : RelastionDataBaseMangementSystem(관계형 데이타 베이스 관리 시스템)
- 오라클 : 테이블스페이스 (TableSpace) = 스키마(Scheme:Mysql) = 데이터베이스(DB-MS-SQL)
- 지난주 금요일날, 오라클 웹용 관리프로그램에서 XE라는 테이블스페이스를 XE사용자로 추가했음.
- EntityRelRelationDiagram(ERD-객체관계그림):Entity는 테이블을 명시
- 데이터 모델: Model Object를 형상화 시킨 것을 모델이라고 함. 데이터 베이스를 말함.
- MVC 스프링 프로젝트에서 M이 Model이고, 스프링 프로젝트 구성 중에 DB를 가리킴.
- 참고로, V는 View고, jsp를 말함.
- 참고로, C는 Controller고 클래스를 말함.

#### 20210528(금)작업내용.
- 추상클래스 실습은 오늘, 인터페이스 실습은 스프링에 엄청 하게 됨.
- extends관계클래스에서 this. (현재클래스), super(부모클래스)
- 다형성? 메서드 @오버라이드(상속), 메서드 오버로드(동일함수명의 파라미터만 갯수와, 종류가 틀린 메서드) 구현됨.
- 오버라이드 : 상속받아서 재정의 메서드 @오버라이드 = 다형성구현함.
- 오라클11g ExpressEditon 설치예정 OracleXE112_win64.zip
SQL디벨리퍼를 다운받아서 압축푼다. - ERD제작할 예정.
- StructuredQuery Language: 구조화된 질의 언어 (오라클 서버) -> 답변
- QueryString : URL에서 전송하는 값(서버에 질의문)을 문장으로 저장한 내용. [?]로 시작
- CommendLineInterface : SQL*Plus 터미널화면으로 SQL쿼리 실행X
- GrapicUserInter: SQL디벨리퍼 윈도우화면 에디엍에서 SQL쿼리 실행O
- SQL Developer 프로그램으로 ERD 다이어그램 그림으로 프로젝트 구조 제작예정.
- EntityRelationDiagram: 테이블관계도형(아래)
- 프로젝트 진행: 발주(클라이언트) -> 수주(개발사) -> 디자인UI(Client-Dev) -> ERD(이사,팀장) -> 코딩시작(UI보면서+ERD소스에 프로그램 입히기)
-ERD에서 SQL쿼리가 생성 -> 물리테이블을 생성.
 첨부파일(자식)->게시판테이블(부모)<-댓글테이블(자식)-
- 자바앱에서는 객체를 생성 후 사용이 끝나면, 메모리에서 삭제하는 명령이 필수.
- 객체를 메모리에서 삭제: Object = null; Object.close();
- 스프링에서는 내장된 가비지컬렉터(쓰레기수집가)가 자동실행. 사용하지 않는 객체를 자동으로 지워줌.
- 위와 같이 개발자가 하던 메모리관리를 스프링이 대신 = IoC(Inversion Of Control)제어의 역전. 스프링 특징3 가지 중 1개(IoC, AOP, DI)

####20210527(목) 작업내용.
- 3장 객체와 클래스 부터 시작.
- 스프링에서는 클래스 extends(상속)보다는 인터페이스 implements를 더 많이 사용함.
- abstract클래스(추상클래스): 구현내용이 없이, 메서드명만 있는 클래스.
- 메서드명만 있기(구현내용이 없기) 때문에, *객체로 만들 수 없는 클래스
- 부모로서 자식에게 상속만(메서드이름만)해서 재사용만 가능하게 됨.
- 객체로 만들 수 없다? 실행가능한 클래스로 사용 못하지만, 프로그램을 구조화 시킬 때 필요.
-Step1 aaa = new Step1();//이렇게 생성자로 객체를 만들지 못함.
- final클래스: 부모가 될 수 없는 클래스(상속해서 재사용이 불가능한 클래스)
- 접근제어자: public(패키지위치에 상관없이 접근-제일 많이 사용),
- public 클래스 안에서 멤버변수는 private을 제일 많이 사용(private보안성 갖춤)
- public 클래스 안에서 멤버메서드는 public을 많이 사용.(외부 다른 클래스의 메서드를 실행가능)대신, 변수가 private이기 때문에, 직접 수정 안됨, 실행만 가능.
- 인스턴스 (클라우드 주로사용)=오브젝트(자바)=객체=실행중인클래스
- 클래스 자료형(int, long, String처럼)는 멤버변수, 멤버메서드, 서브클래스 등등 포함할 수 있음. = C언어 구조체 자료형
- 이클립스에서 커밋, 푸쉬가 안될 때 커밋 팝업창 하단에 Force 항목을 체크 후, 진행.

####20210526(수) 작업
- 붕어빵 : 붕어빵틀(클래스) -> 붕어빵 구워져서 나오면 오브젝트(객체)
- 객체 = 오브젝트 = 인스턴스 = 실행가능한 클래스
- OOP:자바를 객체지향(클래스를 실행 가능하게 하는) 프로그래밍.
- 객체(Object) 와 클래스? 클래스형 자료를 실행 가능하게 만든 것을 오브젝트라(객체)라고 함.
- 추상화(Abstract): 오프라인 업무 -> 대표 업무만 뽑아낸 클래스 = 추상클래스
- 클래스는 멤버변수 + 멤버메서드(실행) + 서브클래스
- 변하지 않는 변수=상수=변수명을 대문자(원주율)PI=3.141569...
- 보통 상수변수는 클래스의 제일 상단 위치함.
- 기본형(정수자료형-소문자시작): byte<short<int<long 10L(롱타입변수)
- 기본형(실수자료형-소수점,소문자시작):float<double , 123.4f(실수형숫자)
- 기본형(문자형-'a'단따옴표 값을 입력해야 에러x): char, 1개 문자만 입력.
- 문자형에서 유니코드 \u숫자 임.
- 기본형(불린형-참,거짓):boolean, (0|1)
-참조형(대문자로 시작): 참조형 변수의 대표적인 변수 클래스. 클래스변수(저장된 상태) -> 실행가능 하게 되었을 때, 인스턴스 변수.(메모리로드된 상태)
- String(문자열 클래스 변수): 대문자로 시작.
- 인스턴스라는 말보다는 오브젝트라는 말을 더 많이 사용함.
- 상수변수를 명시적으로 만들 때: final int MAX = 100;
- for-each 반복문전까지 실습


####20210525(화) 작업
- 스프링 MVC프로젝트 : ModelviewController? 약자 MVC구조(웹프로그램구조)
- src/text/java 폴더 만들어짐 : 테스트 작업은 이 폴더에서 하라는 약속.
- src/main/java 폴더가 진짜 프로그램 작업을 하는 폴더.
- javac HelloWorld.jacv -encodeing UTF-8 (한글 내용도 컨파일 됨)
- 위 java컴파일러로 실행한 결과 -> HelloWorld.class 실행파일이 생성됨.
- 주의점), 클래스파일은 실행 패키지의 루트(최상위)에서 실행해야 함.
- kr.or.test패키지 root폴더 src/test/java 폴더에 실행을 해야 함.
- java kr.or.test.HelloWorld 클래스를 실행하게 됨.
- 이클립스 나오기 전, 20~25년전 javac 컴파일에서 class프로그램을 만듬.
- 지금은 터미널에서 javac 사용하지 않고, 이클립스에서 바로 실행.
- javac? 자바앱 컴파일러 -> 클래스 실행파일을 만듬. class (바이트코드)(자바환경JVN실행)
- 메이븐 이란? Maven : 웹프로그램 컴파일러 -> 웹프로그램(앱) 실행파일을 만듬.war(톰캣에서 실행)
- 메이븐이 컴파일 할 때, 자바소스만 컴파일 하는 것이 아니고, 외부 라이브러리도 가져와서 바인딩(묶어줌)하게 됨. = 패키징 이라고 함. = war(와르)파일로 패키징 됨.
- 메이븐이 관리하는 외부 라이브러리 파일(lib) 목록을 저장하는 파일 pom.xml 임.
- pox.xml 에서 자바버전을 1.6 -> 1.8로 변경 후, 메이븐을 업데이트 함.
-외부 라이브러리 파일을 참조하는 방식을 영어로 = dependency 디펜던시
- jar파일이란? javaARchive = jar 자바클래스를 패키징한 파일.
 
####20210525
- 자바 기초 실습
- 자바 .java 클래스파일을  컴파일해서  .class파일 실행하는 구조.
- 파이썬/자바스크립트는 인터프리터 방식을 실행.
- 자바스크립트는 함수구조의 프로그래밍 = Function(함수)
- 자바는 객체지향 프로그래밍(Object Oriented Program)
- 오브젝트 (객체) = 실행가능한 Class(클래스)
- 아스키,유니코드(UnicodeTypeFormat-8)
- 아스키코드 IOT에서 데이터 전송시 필수로 확인해야 함. 0,1을 전송 -> 48,49값을 받음.
- IoT프로그램시 하드웨어 값을 주고 받을 때 if(var1 == 48) {구현내용}<br>
- 공유기가 하위로 다질 수 있는 사설IP는 공유기 자신의 IP + 255개 (여유분) = 256개 인터넷이 가능.
- 영어 인코딩은 아스키코드로 다 표현 가능.
- 단, 한글 인코딩, 중국어/일본어 인코 딩등등은 아스키 코드로 다 표현 못함. 유니코드 등장. UTF-8
- Hex(16진수),Dec (10진수),Cher(문자) = 127개 = 아스키문자의 크기(컴문자-사람문자 1:1매칭)
- Oct(8진수), bit(2진수)
- 아스키코드 7비트코드(127글자)->ANSI코드 -> 8비트(256글자) -> UniCode(655536글자)-UTF-8
- UTF8mb4(이모지 포함:이모티콘+이미지).
- 자바언어를 한다는 것은 컴파일 후 실행이 된다. -> 실습예정
- 자바스크립트는(파이썬)그냥 실행해서 프로그램을 만들어 짐. -> 실습예정.